package usecases.utils;

import usecases.dto.PostDisplay;
import usecases.dto.QuestionDisplay;
import usecases.responses.TheQuestionResponseModel;
import usecases.responses.UserResponseModel;
import usecases.responses.ViewResponseModel;
import entities.Question;
import java.util.Map;

/**
 * This class provides builder service to construct the question response model and the view response model from base.
 */
public class BuilderService {
    public static final BuilderService INSTANCE = new BuilderService();

    public BuilderService() {
    }

    public static BuilderService getInstance() {
        return INSTANCE;
    }
    public TheQuestionResponseModel constructTheQuestionResponse(Question question, UserResponseModel response, Map<Integer, PostDisplay> postMap) {
        return response.toQuestionResponseBuilder()
                .questionId(question.getQuestionId())
                .questionTitle(question.getTitle())
                .questionType(question.getType())
                .isClose(question.isClose())
                .deadline(question.getLegalDeadline())
                .postMap(postMap)
                .build();
    }

    public ViewResponseModel constructViewResponse(UserResponseModel response, Map<Integer, QuestionDisplay> questionMap) {
        return response.toViewResponseBuilder()
                .questionMap(questionMap)
                .build();
    }
}
