package businessrule.responsemodel;

import businessrule.usecase.util.QuestionDisplayFormatter;

import java.util.Map;

public class ViewResponseModel extends UserResponseModel{

    private final Map<Integer, QuestionDisplayFormatter> questionMap;

    public ViewResponseModel(Builder builder) {
        super(builder.userId, builder.userName, builder.userType);
        this.questionMap = builder.questionMap;
    }

    public ViewResponseModel(int userId, String userName, String userType, Map<Integer, QuestionDisplayFormatter> questionMap) {
        super(userId, userName, userType);
        this.questionMap = questionMap;
    }

    public static class Builder {
        private int userId;
        private String userName;
        private String userType;
        private Map<Integer, QuestionDisplayFormatter> questionMap;
        public Builder(UserResponseModel userModel) {
            this.userId = userModel.getUserId();
            this.userName = userModel.getUserName();
            this.userType = userModel.getUserType();
        }

        public  Builder questionMap(Map<Integer, QuestionDisplayFormatter> questionMap) {
            this.questionMap = questionMap;
            return this;
        }

        public ViewResponseModel build() {
            return new ViewResponseModel(this);
        }
    }

    public Map<Integer, QuestionDisplayFormatter> getQuestionMap() {
        return questionMap;
    }
}
