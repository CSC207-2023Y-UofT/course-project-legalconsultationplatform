package businessrule.usecase.util;

import businessrule.responsemodel.TheQuestionResponseModel;
import businessrule.responsemodel.UserResponseModel;
import businessrule.responsemodel.ViewResponseModel;
import entity.Question;

import java.util.HashMap;
import java.util.Map;

public class BuilderService {
    public static final BuilderService INSTANCE = new BuilderService();

    public BuilderService() {
    }

    public static BuilderService getInstance() {
        return INSTANCE;
    }
    public TheQuestionResponseModel constructTheQuestionResponse(Question question, UserResponseModel response, Map<Integer, PostDisplayFormatter> postMap) {
        return response.toQuestionResponseBuilder()
                .questionId(question.getQuestionId())
                .questionTitle(question.getTitle())
                .questionType(question.getType())
                .isClose(question.isClose())
                .deadline(question.getLegalDeadline())
                .postMap(postMap)
                .build();
    }

    public ViewResponseModel constructViewResponse(UserResponseModel response, Map<Integer, QuestionDisplayFormatter> questionMap) {
        return response.toViewResponseBuilder()
                .questionMap(questionMap)
                .build();
    }
}
