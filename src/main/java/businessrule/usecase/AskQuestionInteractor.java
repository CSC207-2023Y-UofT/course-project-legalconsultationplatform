package businessrule.usecase;

import businessrule.usecase.util.PostDisplayFormatter;
import businessrule.usecase.util.RandomNumberGenerator;
import entity.*;
import businessrule.inputboundary.QuestionInputBoundary;
import businessrule.requestmodel.QuestionRequestModel;
import businessrule.gateway.ClientGateway;
import businessrule.gateway.QuestionGateway;
import businessrule.outputboundary.TheQuestionOutputBoundary;
import businessrule.responsemodel.TheQuestionResponseModel;
import entity.factory.QuestionFactory;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents the interactor responsible for asking a new question.
 *
 * This interactor validates input, generates a unique question ID, creates a question entity,
 * updates relevant gateways, and constructs the response model.
 */
public class AskQuestionInteractor implements QuestionInputBoundary {
    final QuestionGateway questionGateway;
    final TheQuestionOutputBoundary theQuestionOutputBoundary;
    final QuestionFactory questionFactory;
    final ClientGateway clientGateway;

    /**
     * Constructor for AskQuestionInteractor.
     *
     * @param questionGateway The gateway for managing question entities.
     * @param theQuestionOutputBoundary The output boundary for preparing question response models.
     * @param questionFactory The factory for creating question entities.
     * @param clientGateway The gateway for managing client entities.
     */
    public AskQuestionInteractor(QuestionGateway questionGateway, TheQuestionOutputBoundary theQuestionOutputBoundary, QuestionFactory questionFactory, ClientGateway clientGateway) {
        this.questionGateway = questionGateway;
        this.theQuestionOutputBoundary = theQuestionOutputBoundary;
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

        // validate input
        if (questionRequestModel.getQuestionCategory() == null){
            return theQuestionOutputBoundary.prepareFail("Please specify your question type.");
        } else if (questionRequestModel.getTitle() == null){
            return theQuestionOutputBoundary.prepareFail("Please specify your question title.");
        }

        // generate question id
        RandomNumberGenerator generator = new RandomNumberGenerator();
        int randomQuestionId = generator.generateQuestionId(9);
        boolean exists = questionGateway.existsById(randomQuestionId);
        while (exists){
            randomQuestionId = generator.generateQuestionId(9);
            exists = questionGateway.existsById(randomQuestionId);
        }

        // create question entity
        LocalDate now = LocalDate.now();
        int askedByClient = questionRequestModel.getAskedByClient();
        Client askedBy = (Client) clientGateway.get(askedByClient);
        Question question = questionFactory.create(randomQuestionId, questionRequestModel.getQuestionCategory(), questionRequestModel.getTitle(), now, askedByClient, questionRequestModel.getLegalDeadline());
        questionGateway.save(question);
        clientGateway.updateQuestionList(questionRequestModel.getAskedByClient(), question);

        // construct response model
        Map<Integer, PostDisplayFormatter> postMap = new HashMap<>();
        TheQuestionResponseModel theQuestionResponseModel = new TheQuestionResponseModel(questionRequestModel.getAskedByClient(), question.getQuestionId(), askedBy.getUserName(), question.getTitle(), question.getType(), question.getLegalDeadline(),false, postMap);
        return theQuestionOutputBoundary.prepareSuccess(theQuestionResponseModel);
    }
}