package usecases.interactors;

import usecases.session.SessionManager;
import usecases.session.UserSession;
import usecases.gateway.QuestionGateway;
import usecases.inputboundary.ViewInputBoundary;
import usecases.outputboundary.ViewOutputBoundary;
import usecases.responses.UserResponseModel;
import usecases.responses.ViewResponseModel;
import usecases.utils.BuilderService;
import usecases.dto.QuestionDisplay;
import usecases.utils.QuestionMapConstructor;
import entities.Question;
import entities.user.User;

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
     * @param outputBoundary The output boundary for communicating results.
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

        QuestionMapConstructor questionMapConstructor = new QuestionMapConstructor();
        Map<Integer, QuestionDisplay> questionMap = questionMapConstructor.constructQuestionMap(questionList);
        return outputBoundary.prepareSuccess(BuilderService.getInstance().constructViewResponse(response, questionMap));
    }

    /**
     * Fetches a list of questions based on the provided view request model.
     *
     * @return A list of questions based on the provided criteria.
     */
    protected abstract List<Question> fetchQuestions(int userId);

    /**
     * Fetches user information based on the provided view request model.
     *
     * @return The user associated with the provided criteria.
     */
    protected abstract User fetchUser(int userId);
}
