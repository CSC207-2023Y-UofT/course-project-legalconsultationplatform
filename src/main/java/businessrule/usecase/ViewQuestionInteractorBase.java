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

public abstract class ViewQuestionInteractorBase implements ViewInputBoundary {
    protected final ViewOutputBoundary outputBoundary;
    protected final QuestionGateway questionGateway;

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

    protected abstract List<Question> fetchQuestions(int userId);
    protected abstract User fetchUser(int userId);
}
