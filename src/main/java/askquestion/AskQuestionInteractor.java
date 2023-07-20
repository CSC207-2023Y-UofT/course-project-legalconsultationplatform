package askquestion;

import gateway.ClientGateway;
import gateway.QuestionGateway;
import presenter.TheQuestionOutputBoundary;
import presenter.TheQuestionResponseModel;
import questionentities.Question;

import java.time.LocalDate;
import java.util.Random;

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
        Random rand = new Random();
        int upperbound = 10000000;
        int int_random = rand.nextInt(upperbound);
        boolean ifExists = questionGateway.checkExistsByName(int_random);
        while (ifExists) {
            int_random = rand.nextInt(upperbound);
            ifExists = questionGateway.checkExistsByName(int_random);
        }
        LocalDate now = LocalDate.now();
        Question question = questionFactory.create(int_random, questionRequestModel.getQuestionCategory(), now, questionRequestModel.getAskedByClient(), questionRequestModel.getLegalDeadline());
        questionGateway.saveQuestion(question);

        clientGateway.updateQuestionList(questionRequestModel.getAskedByClient(), question);

        TheQuestionResponseModel theQuestionResponseModel = new TheQuestionResponseModel();
        // 理论上说，response model里要放东西，然后prepareSuccess里面要放这个responseModel.
        return theQuestionOutputBoundary.prepareSuccess();
    }
}
