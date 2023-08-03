package attorneybrowsequestion;

import apapter.presenter.ViewOutputBoundary;
import apapter.responsemodel.ViewResponseModel;
import gateway.QuestionGateway;
import questionentities.Question;

import java.util.List;

public class BrowseQuestionInterator implements BrowseInputBoundary{

    final ViewOutputBoundary viewOutputBoundary;
    final QuestionGateway questionGateway;

    public BrowseQuestionInterator(ViewOutputBoundary viewOutputBoundary, QuestionGateway questionGateway) {
        this.questionGateway = questionGateway;
        this.viewOutputBoundary = viewOutputBoundary;
    }

    // TODO: complete prepareSuccess
    public ViewResponseModel browseQuestion(){
        List<Question> questionsList;
        questionsList = questionGateway.getNotTakenQuestion();
        return viewOutputBoundary.prepareSuccess();
    }
}
