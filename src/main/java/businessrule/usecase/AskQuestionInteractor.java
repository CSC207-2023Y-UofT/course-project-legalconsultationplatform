package businessrule.usecase;

import entity.*;
import businessrule.inputboundary.QuestionInputBoundary;
import businessrule.requestmodel.QuestionRequestModel;
import businessrule.gateway.ClientGateway;
import businessrule.gateway.QuestionGateway;
import businessrule.outputboundary.TheQuestionOutputBoundary;
import businessrule.responsemodel.TheQuestionResponseModel;

import java.time.LocalDate;
import java.util.Map;

public class AskQuestionInteractor implements QuestionInputBoundary {
    final QuestionGateway questionGateway;
    final TheQuestionOutputBoundary theQuestionOutputBoundary;
    final QuestionFactory questionFactory;
    final ClientGateway clientGateway;

    public AskQuestionInteractor(QuestionGateway questionGateway, TheQuestionOutputBoundary theQuestionOutputBoundary,
                             QuestionFactory questionFactory, ClientGateway clientGateway){
        this.questionGateway = questionGateway;
        this.theQuestionOutputBoundary = theQuestionOutputBoundary;
        this.questionFactory = questionFactory;
        this.clientGateway = clientGateway;
    }


    public TheQuestionResponseModel createQuestion(QuestionRequestModel questionRequestModel){
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        if (questionRequestModel.getQuestionCategory() == null){
            return theQuestionOutputBoundary.prepareFail("Please specify your question type.");
        }
        LocalDate now = LocalDate.now();
        int askedByClient = questionRequestModel.getAskedByClient();
        Client askedBy = (Client)clientGateway.getUser(askedByClient);
        Question question = questionFactory.create(randomNumberGenerator.generateQuestionId(9), questionRequestModel.getQuestionCategory(), questionRequestModel.getTitle(), now, askedByClient, questionRequestModel.getLegalDeadline());
        questionGateway.saveQuestion(question);

        clientGateway.updateQuestionList(questionRequestModel.getAskedByClient(), question);
        PostMapConstructor postMapConstructor = new PostMapConstructor(clientGateway);
        Map<Integer, PostDisplayFormatter> postMap = postMapConstructor.constructPostMap(question);

        TheQuestionResponseModel theQuestionResponseModel = new TheQuestionResponseModel(questionRequestModel.getAskedByClient(), question.getQuestionId(), askedBy.getUserName(), question.getTitle(), question.getType(), question.getLegalDeadline(), postMap);
        return theQuestionOutputBoundary.prepareSuccess(theQuestionResponseModel);
    }
}
