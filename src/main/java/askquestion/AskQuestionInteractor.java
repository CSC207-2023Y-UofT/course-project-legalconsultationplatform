package askquestion;

import presenter.TheQuestionOutputBoundary;
import questionentities.Question;
import questiongateway.QuestionGateway;
import java.util.Random;

public class AskQuestionInteractor implements QuestionInputBoundary{
    final QuestionGateway questionGateway;
    final TheQuestionOutputBoundary theQuestionOutputBoundary;
    final QuestionFactory questionFactory;

    public AskQuestionInteractor(QuestionGateway questionGateway, TheQuestionOutputBoundary theQuestionOutputBoundary,
                             QuestionFactory questionFactory){
        this.questionGateway = questionGateway;
        this.theQuestionOutputBoundary = theQuestionOutputBoundary;
        this.questionFactory = questionFactory;
    }

    public QuestionResponseModel createQuestion(QuestionRequestModel questionRequestModel){
        Random rand = new Random();
        int upperbound = 10000000;
        int int_random = rand.nextInt(upperbound);
        while questionGateway.int_random
        Question question = questionFactory.create();
        return null;
    }
}
