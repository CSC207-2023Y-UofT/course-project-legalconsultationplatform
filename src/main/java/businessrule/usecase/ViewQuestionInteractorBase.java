package businessrule.usecase;

import businessrule.SessionManager;
import businessrule.UserSession;
import businessrule.gateway.QuestionGateway;
import businessrule.inputboundary.ViewInputBoundary;
import businessrule.outputboundary.TheQuestionOutputBoundary;
import businessrule.outputboundary.UserOutputBoundary;
import businessrule.outputboundary.ViewOutputBoundary;
import businessrule.responsemodel.UserResponseModel;
import businessrule.responsemodel.ViewResponseModel;
import businessrule.usecase.util.BuilderService;
import businessrule.usecase.util.QuestionDisplayFormatter;
import businessrule.usecase.util.QuestionMapConstructor;
import entity.Question;
import entity.User;

import javax.swing.text.View;
import java.util.List;
import java.util.Map;

/**
 * This class represents the use case to view questions.
 */
public abstract class ViewQuestionInteractorBase implements ViewInputBoundary {
    protected final ViewOutputBoundary outputBoundary;
    protected final QuestionGateway questionGateway;

    /**
     * Constructs a ViewQuestionInteractorBase instance.
     *
     * @param putputBoundary The output boundary for communicating results.
     * @param questionGateway The gateway for accessing question data.
     */
    public ViewQuestionInteractorBase(ViewOutputBoundary outputBoundary, QuestionGateway questionGateway) {
        this.outputBoundary = outputBoundary;
        this.questionGateway = questionGateway;
    }

    @Override
    public ViewResponseModel viewQuestion() {
        // get input
        UserSession session = SessionManager.getSession();
        UserResponseModel response = session.getUserResponseModel();
        int userId = response.getUserId();

        // strategy
        List<Question> questionList = fetchQuestions(userId);
        User user = fetchUser(userId);

        QuestionMapConstructor questionMapConstructor = new QuestionMapConstructor();
        Map<Integer, QuestionDisplayFormatter> questionMap = questionMapConstructor.constructQuestionMap(questionList);
        return outputBoundary.prepareSuccess(BuilderService.getInstance().constructViewResponse(response, questionMap));
    }

    /**
     * Fetches a list of questions based on the provided view request model.
     *
     * @param userId
     * @return A list of questions based on the provided criteria.
     */
    protected abstract List<Question> fetchQuestions(int userId);

    /**
     * Fetches user information based on the provided view request model.
     *
     * @param  userId
     * @return The user associated with the provided criteria.
     */
    protected abstract User fetchUser(int userId);
}
