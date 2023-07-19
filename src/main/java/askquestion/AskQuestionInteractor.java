package askquestion;

import questionentities.Question;
import questiongateway.QuestionGateway;
import screenpresenter.ScreenOutputBoundary;

public class AskQuestionInteractor implements QuestionInputBoundary{
    final QuestionGateway questionGateway;
    final ScreenOutputBoundary screenOutputBoundary;
    final QuestionFactory questionFactory;

    public AskQuestionInteractor(QuestionGateway questionGateway, ScreenOutputBoundary screenOutputBoundary,
                             QuestionFactory questionFactory){
        this.questionGateway = questionGateway;
        this.screenOutputBoundary = screenOutputBoundary;
        this.questionFactory = questionFactory;
    }

    public QuestionResponseModel createQuestion(QuestionRequestModel questionRequestModel){
        Question question = questionFactory.create(questionRequestModel);
    }
}
