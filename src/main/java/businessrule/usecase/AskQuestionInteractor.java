package businessrule.usecase;

import businessrule.gateway.UserGatewayFactory;
import entity.*;
import businessrule.inputboundary.QuestionInputBoundary;
import businessrule.requestmodel.QuestionRequestModel;
import businessrule.gateway.ClientGateway;
import businessrule.gateway.QuestionGateway;
import businessrule.outputboundary.TheQuestionOutputBoundary;
import businessrule.responsemodel.TheQuestionResponseModel;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class AskQuestionInteractor implements QuestionInputBoundary {
    final QuestionGateway questionGateway;
    final TheQuestionOutputBoundary theQuestionOutputBoundary;
    final QuestionFactory questionFactory;
    final ClientGateway clientGateway;

    public AskQuestionInteractor(QuestionGateway questionGateway, TheQuestionOutputBoundary theQuestionOutputBoundary, QuestionFactory questionFactory, ClientGateway clientGateway) {
        this.questionGateway = questionGateway;
        this.theQuestionOutputBoundary = theQuestionOutputBoundary;
        this.questionFactory = questionFactory;
        this.clientGateway = clientGateway;
    }

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
        Client askedBy = (Client) clientGateway.getUser(askedByClient);
        Question question = questionFactory.create(randomQuestionId, questionRequestModel.getQuestionCategory(), questionRequestModel.getTitle(), now, askedByClient, questionRequestModel.getLegalDeadline());
        questionGateway.saveQuestion(question);
        clientGateway.updateQuestionList(questionRequestModel.getAskedByClient(), question);

        // construct response model
        Map<Integer, PostDisplayFormatter> postMap = new HashMap<>();
        TheQuestionResponseModel theQuestionResponseModel = new TheQuestionResponseModel(questionRequestModel.getAskedByClient(), question.getQuestionId(), askedBy.getUserName(), question.getTitle(), question.getType(), question.getLegalDeadline(),false, postMap);
        return theQuestionOutputBoundary.prepareSuccess(theQuestionResponseModel);
    }
}
