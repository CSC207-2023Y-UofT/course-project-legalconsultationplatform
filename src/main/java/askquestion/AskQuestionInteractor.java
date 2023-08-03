package askquestion;

import clientregister.RandomNumberGenerator;
import gateway.ClientGateway;
import gateway.QuestionGateway;
import apapter.presenter.TheQuestionOutputBoundary;
import apapter.responsemodel.TheQuestionResponseModel;
import questionentities.Question;

import java.time.LocalDate;

public class AskQuestionInteractor implements QuestionInputBoundary{
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
            return theQuestionOutputBoundary.prepareFail("fail");
        }
        LocalDate now = LocalDate.now();
        Question question = questionFactory.create(randomNumberGenerator.generateQuestionId(9), questionRequestModel.getQuestionCategory(), questionRequestModel.getTitle(), now, questionRequestModel.getAskedByClient(), questionRequestModel.getLegalDeadline());
        questionGateway.saveQuestion(question);

        clientGateway.updateQuestionList(questionRequestModel.getAskedByClient(), question);

        TheQuestionResponseModel theQuestionResponseModel = new TheQuestionResponseModel(questionRequestModel.getAskedByClient(), now);
        return theQuestionOutputBoundary.prepareSuccess(theQuestionResponseModel);
    }
}
