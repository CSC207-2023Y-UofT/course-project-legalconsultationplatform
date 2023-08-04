package businessrule.usecase;

import businessrule.gateway.AttorneyGateway;
import businessrule.inputboundary.BrowseInputBoundary;
import businessrule.outputboundary.ViewOutputBoundary;
import businessrule.requestmodel.BrowseRequestModel;
import businessrule.responsemodel.ViewResponseModel;
import driver.database.QuestionGateway;
import entity.Attorney;
import entity.Question;

import java.util.List;
import java.util.Map;

/**
 * This class is responsible for responsible for handling the browse question use case.
 *
 * This class facilitates browsing and retrieving a list of not-taken questions from the data source through
 */
public class BrowseQuestionInterator implements BrowseInputBoundary {

    final ViewOutputBoundary viewOutputBoundary;
    final QuestionGateway questionGateway;
    final AttorneyGateway attorneyGateway;

    public BrowseQuestionInterator(ViewOutputBoundary viewOutputBoundary, QuestionGateway questionGateway, AttorneyGateway attorneyGateway) {
        this.viewOutputBoundary = viewOutputBoundary;
        this.questionGateway = questionGateway;
        this.attorneyGateway = attorneyGateway;
    }

    public ViewResponseModel browseQuestion(BrowseRequestModel browseRequestModel){
        List<Question> questionsList = questionGateway.getNotTakenQuestion();
        QuestionMapConstructor questionMapConstructor = new QuestionMapConstructor();
        Map<Integer, QuestionDisplayFormatter> questionMap = questionMapConstructor.constructQuestionMap(questionsList);
        Attorney attorney = (Attorney) attorneyGateway.getUser(browseRequestModel.getAttorneyId());
        ViewResponseModel viewResponseModel = new ViewResponseModel(attorney.getUserId(), attorney.getUserName(), questionMap);
        return viewOutputBoundary.prepareSuccess(viewResponseModel);
    }
}
