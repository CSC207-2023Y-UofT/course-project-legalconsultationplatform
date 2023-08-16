package usecases.interactors;

import usecases.dto.QuestionDisplay;
import usecases.responses.ViewResponseModel;
import usecases.session.SessionManager;
import usecases.session.UserSession;
import usecases.outputboundary.TheQuestionOutputBoundary;
import usecases.responses.TheQuestionResponseModel;
import usecases.utils.BuilderService;
import usecases.dto.PostDisplay;
import usecases.utils.RandomNumberGenerator;
import entities.*;
import usecases.inputboundary.QuestionInputBoundary;
import usecases.requests.QuestionRequestModel;
import usecases.gateway.ClientGateway;
import usecases.gateway.QuestionGateway;
import entities.factories.QuestionFactory;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * This class represents the interactor responsible for asking a new question.
 * This interactor validates input, generates a unique question ID, creates a question entity,
 * updates relevant gateways, and constructs the response model.
 */
public class AskQuestionInteractor implements QuestionInputBoundary {
    final QuestionGateway questionGateway;
    final TheQuestionOutputBoundary outputBoundary;
    final QuestionFactory questionFactory;
    final ClientGateway clientGateway;
    final static String EMPTY_TITLE = "";

    /**
     * Constructor for AskQuestionInteractor.
     *
     * @param questionGateway The gateway for managing question entities.
     * @param outputBoundary The output boundary for preparing question response models.
     * @param questionFactory The factory for creating question entities.
     * @param clientGateway The gateway for managing client entities.
     */
    public AskQuestionInteractor(QuestionGateway questionGateway, TheQuestionOutputBoundary outputBoundary, QuestionFactory questionFactory, ClientGateway clientGateway) {
        this.questionGateway = questionGateway;
        this.outputBoundary = outputBoundary;
        this.questionFactory = questionFactory;
        this.clientGateway = clientGateway;
    }

    /**
     * Create a new question based on the provided request model.
     *
     * @param questionRequestModel The request model containing question details.
     * @return The response model for the newly created question.
     */
    public TheQuestionResponseModel createQuestion(QuestionRequestModel questionRequestModel){
        try {checkValidateInput(questionRequestModel);}
        catch (ApplicationException e) {
            return outputBoundary.prepareFail(e.getMessage());
        }

        // get usr session
        UserSession session = SessionManager.getSession();
        ViewResponseModel response = session.getUserResponseModel();
        int askedByClient = response.getUserId();

        // create question entity
        Question question = createQuestionEntity(questionRequestModel, askedByClient);

        // construct response model
        response.getQuestionMap().put(question.getQuestionId(), new QuestionDisplay(question.getTitle(), question.getType(), question.getLegalDeadline(), question.isClose()));
        Map<Integer, PostDisplay> postMap = new HashMap<>();
        return outputBoundary.prepareSuccess(BuilderService.getInstance().constructTheQuestionResponse(question, response, postMap));
    }

    private void checkValidateInput(QuestionRequestModel questionRequestModel) {
        if (questionRequestModel.getQuestionCategory() == null) {
            throw new ApplicationException("Please specify your question type.");
        } else if (Objects.equals(questionRequestModel.getTitle(), EMPTY_TITLE)) {
            throw new ApplicationException("Please specify your question title.");
        } else if (questionRequestModel.getLegalDeadline() == null) {
            throw new ApplicationException("Please specify your question's deadline");
        }
    }

    private int generateQuestionId(){
        RandomNumberGenerator generator = new RandomNumberGenerator();
        int randomQuestionId = generator.generateQuestionId(9);
        boolean exists = questionGateway.existsById(randomQuestionId);
        while (exists){
            randomQuestionId = generator.generateQuestionId(9);
            exists = questionGateway.existsById(randomQuestionId);
        }
        return randomQuestionId;
    }

    private Question createQuestionEntity(QuestionRequestModel questionRequestModel, int askedByClient){
        // generate question id
        int randomQuestionId = generateQuestionId();

        // create question entity
        LocalDate now = LocalDate.now();
        Question question = questionFactory.create(randomQuestionId, questionRequestModel.getQuestionCategory(), questionRequestModel.getTitle(), now, askedByClient, questionRequestModel.getLegalDeadline());
        questionGateway.save(question);
        clientGateway.updateQuestionList(askedByClient, question);
        return question;
    }
}


