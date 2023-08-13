package businessrule.usecase;

import businessrule.SessionManager;
import businessrule.UserSession;
import businessrule.outputboundary.TheQuestionOutputBoundary;
import businessrule.outputboundary.UserOutputBoundary;
import businessrule.responsemodel.TheQuestionResponseModel;
import businessrule.responsemodel.UserResponseModel;
import businessrule.usecase.util.BuilderService;
import businessrule.usecase.util.PostDisplayFormatter;
import businessrule.usecase.util.RandomNumberGenerator;
import entity.*;
import businessrule.inputboundary.QuestionInputBoundary;
import businessrule.requestmodel.QuestionRequestModel;
import businessrule.gateway.ClientGateway;
import businessrule.gateway.QuestionGateway;
import entity.factory.QuestionFactory;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class AskQuestionInteractor implements QuestionInputBoundary {
    final QuestionGateway questionGateway;
    final TheQuestionOutputBoundary outputBoundary;
    final QuestionFactory questionFactory;
    final ClientGateway clientGateway;
    final static String EMPTY_TITLE = "";

    public AskQuestionInteractor(QuestionGateway questionGateway, TheQuestionOutputBoundary outputBoundary, QuestionFactory questionFactory, ClientGateway clientGateway) {
        this.questionGateway = questionGateway;
        this.outputBoundary = outputBoundary;
        this.questionFactory = questionFactory;
        this.clientGateway = clientGateway;
    }

    public UserResponseModel createQuestion(QuestionRequestModel questionRequestModel){
        try {checkValidateInput(questionRequestModel);}
        catch (ApplicationException e) {
            return outputBoundary.prepareFail(e.getMessage());
        }

        // get usr session
        UserSession session = SessionManager.getSession();
        UserResponseModel response = session.getUserResponseModel();
        int askedByClient = response.getUserId();

        // create question entity
        Question question = createQuestionEntity(questionRequestModel, askedByClient);

        // construct response model
        Map<Integer, PostDisplayFormatter> postMap = new HashMap<>();
        return outputBoundary.prepareSuccess(BuilderService.getInstance().constructTheQuestionResponse(question, response, postMap));
    }

    private void checkValidateInput(QuestionRequestModel questionRequestModel) {
        if (questionRequestModel.getQuestionCategory() == null) {
            throw new ApplicationException("Please specify your question type.");
        } else if (questionRequestModel.getTitle() == null) {
            throw new ApplicationException("Please specify your question title.");
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


