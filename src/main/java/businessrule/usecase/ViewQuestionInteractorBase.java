package businessrule.usecase;

import businessrule.gateway.QuestionGateway;
import businessrule.inputboundary.ViewInputBoundary;
import businessrule.outputboundary.ViewOutputBoundary;
import businessrule.requestmodel.ViewRequestModel;
import businessrule.responsemodel.ViewResponseModel;
import entity.Question;
import entity.User;

import java.util.List;
import java.util.Map;

public abstract class ViewQuestionInteractorBase implements ViewInputBoundary {
    protected final ViewOutputBoundary viewOutputBoundary;
    protected final QuestionGateway questionGateway;

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

    protected abstract List<Question> fetchQuestions(ViewRequestModel viewRequestModel);
    protected abstract User fetchUser(ViewRequestModel viewRequestModel);
}
