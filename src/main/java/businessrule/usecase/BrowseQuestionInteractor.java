package businessrule.usecase;

import businessrule.gateway.AttorneyGateway;
import businessrule.inputboundary.BrowseInputBoundary;
import businessrule.outputboundary.ViewOutputBoundary;
import businessrule.requestmodel.BrowseRequestModel;
import businessrule.responsemodel.ViewResponseModel;
import businessrule.gateway.QuestionGateway;
import entity.Attorney;
import entity.Question;

import java.util.List;
import java.util.Map;

/**
 * This class is responsible for handling the browse question use case.
 *
 * This class facilitates browsing and retrieving a list of not-taken questions from the data source.
 */
public class BrowseQuestionInterator implements BrowseInputBoundary {

    /**
     * The output boundary for preparing view responses.
     */
    final ViewOutputBoundary viewOutputBoundary;

    /**
     * The gateway for accessing question-related operations.
     */
    final QuestionGateway questionGateway;

    /**
     * The gateway for accessing attorney-related operations.
     */
    final AttorneyGateway attorneyGateway;

    /**
     * Constructs an instance of BrowseQuestionInterator with the necessary dependencies.
     *
     * @param viewOutputBoundary The output boundary for preparing view responses.
     * @param questionGateway    The gateway for question-related operations.
     * @param attorneyGateway    The gateway for attorney-related operations.
     */
    public BrowseQuestionInterator(ViewOutputBoundary viewOutputBoundary, QuestionGateway questionGateway, AttorneyGateway attorneyGateway) {
        this.viewOutputBoundary = viewOutputBoundary;
        this.questionGateway = questionGateway;
        this.attorneyGateway = attorneyGateway;
    }

    /**
     * Browses and retrieves a list of not-taken questions based on the provided BrowseRequestModel.
     *
     * @param browseRequestModel The model containing information for browsing questions.
     * @return The response model containing a list of not-taken questions.
     */
    public ViewResponseModel browseQuestion(BrowseRequestModel browseRequestModel) {
        // ... (Method implementation)

        // construct response model
        List<Question> questionsList = questionGateway.getNotTakenQuestion();
        QuestionMapConstructor questionMapConstructor = new QuestionMapConstructor();
        Map<Integer, QuestionDisplayFormatter> questionMap = questionMapConstructor.constructQuestionMap(questionsList);
        Attorney attorney = (Attorney) attorneyGateway.getUser(browseRequestModel.getAttorneyId());
        ViewResponseModel viewResponseModel = new ViewResponseModel(attorney.getUserId(), attorney.getUserName(), questionMap);
        return viewOutputBoundary.prepareSuccess(viewResponseModel);
    }
}