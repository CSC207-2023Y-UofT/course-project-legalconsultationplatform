package usecases.responses;

import usecases.dto.QuestionDisplay;

import java.util.Map;

/**
 * This class represents response data related to the question list shown to the user.
 */
public class ViewResponseModel extends UserResponseModel{

    private final Map<Integer, QuestionDisplay> questionMap;

    public ViewResponseModel(Builder builder) {
        super(builder.userId, builder.userName, builder.userType);
        this.questionMap = builder.questionMap;
    }

    public ViewResponseModel(int userId, String userName, String userType, Map<Integer, QuestionDisplay> questionMap) {
        super(userId, userName, userType);
        this.questionMap = questionMap;
    }

    public static class Builder {
        private final int userId;
        private final String userName;
        private final String userType;
        private Map<Integer, QuestionDisplay> questionMap;
        public Builder(UserResponseModel userModel) {
            this.userId = userModel.getUserId();
            this.userName = userModel.getUserName();
            this.userType = userModel.getUserType();
        }

        public  Builder questionMap(Map<Integer, QuestionDisplay> questionMap) {
            this.questionMap = questionMap;
            return this;
        }

        public ViewResponseModel build() {
            return new ViewResponseModel(this);
        }
    }

    public Map<Integer, QuestionDisplay> getQuestionMap() {
        return questionMap;
    }
}
