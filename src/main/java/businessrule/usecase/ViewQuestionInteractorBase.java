package businessrule.usecase;

import businessrule.gateway.QuestionGateway;
import businessrule.inputboundary.ViewInputBoundary;
import businessrule.outputboundary.ViewOutputBoundary;
import businessrule.requestmodel.ViewRequestModel;
import businessrule.responsemodel.ViewResponseModel;
import businessrule.usecase.util.QuestionDisplayFormatter;
import businessrule.usecase.util.QuestionMapConstructor;
import entity.Question;
import entity.User;

import java.util.List;
import java.util.Map;

/**
 * This class represents the use case to view questions.
 */
public abstract class ViewQuestionInteractorBase implements ViewInputBoundary {

    /**
     * The output boundary to communicate the result of the view question use case.
     */
    protected final ViewOutputBoundary viewOutputBoundary;

    /**
     * The gateway to access question-related data.
     */
    protected final QuestionGateway questionGateway;

    /**
     * Constructs a ViewQuestionInteractorBase instance.
     *
     * @param viewOutputBoundary The output boundary for communicating results.
     * @param questionGateway The gateway for accessing question data.
     */
    public ViewQuestionInteractorBase(ViewOutputBoundary viewOutputBoundary, QuestionGateway questionGateway) {
        this.viewOutputBoundary = viewOutputBoundary;
        this.questionGateway = questionGateway;
    }

    @Override
    public ViewResponseModel viewQuestion(ViewRequestModel viewRequestModel) {
        List<Question> questionList = fetchQuestions(viewRequestModel);
        User user = fetchUser(viewRequestModel);

        QuestionMapConstructor questionMapConstructor = new QuestionMapConstructor();
        Map<Integer, QuestionDisplayFormatter> questionMap = questionMapConstructor.constructQuestionMap(questionList);

        ViewResponseModel viewResponseModel = new ViewResponseModel(user.getUserId(), user.getUserName(), questionMap);
        return viewOutputBoundary.prepareSuccess(viewResponseModel);
    }

    /**
     * Fetches a list of questions based on the provided view request model.
     *
     * @param viewRequestModel The request model containing criteria for fetching questions.
     * @return A list of questions based on the provided criteria.
     */
    protected abstract List<Question> fetchQuestions(ViewRequestModel viewRequestModel);

    /**
     * Fetches user information based on the provided view request model.
     *
     * @param viewRequestModel The request model containing criteria for fetching user information.
     * @return The user associated with the provided criteria.
     */
    protected abstract User fetchUser(ViewRequestModel viewRequestModel);
}