package businessrule.usecase;

import businessrule.inputboundary.BrowseInputBoundary;
import businessrule.outputboundary.ViewOutputBoundary;
import businessrule.responsemodel.ViewResponseModel;
import driver.database.QuestionGateway;
import entity.Question;

import java.util.List;

public class BrowseQuestionInterator implements BrowseInputBoundary {

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
