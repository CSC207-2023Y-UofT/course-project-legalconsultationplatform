package businessrule.responsemodel;

import businessrule.usecase.util.PostDisplayFormatter;
import java.time.LocalDate;
import java.util.Map;

public class TheQuestionResponseModel extends UserResponseModel{
    private final int questionId;
    private final String title;
    private final String type;
    private final LocalDate deadline;
    private final boolean isClose;
    private final Map<Integer, PostDisplayFormatter> postMap;

    private TheQuestionResponseModel(Builder builder) {
        super(builder.userId, builder.userName, builder.userType);
        this.questionId = builder.questionId;
        this.title = builder.questionTitle;
        this.type = builder.questionType;
        this.deadline = builder.deadline;
        this.isClose = builder.isClose;
        this.postMap = builder.postMap;
    }

    public static class Builder {
        private int userId;
        private String userName;
        private String userType;
        private int questionId;
        private String questionTitle;
        private String questionType;
        private LocalDate deadline;
        private boolean isClose;
        private Map<Integer, PostDisplayFormatter> postMap;

        public Builder(UserResponseModel userModel) {
            this.userId = userModel.getUserId();
            this.userName = userModel.getUserName();
            this.userType = userModel.getUserType();
        }

        public Builder questionId(int questionId) {
            this.questionId = questionId;
            return this;
        }

        public Builder questionTitle(String title) {
            this.questionTitle = title;
            return this;
        }

        public Builder questionType(String type) {
            this.questionType = type;
            return this;
        }

        public Builder deadline(LocalDate deadline) {
            this.deadline = deadline;
            return this;
        }

        public Builder isClose(boolean isClose) {
            this.isClose = isClose;
            return this;
        }

        public Builder postMap(Map<Integer, PostDisplayFormatter> postMap) {
            this.postMap = postMap;
            return this;
        }

        public TheQuestionResponseModel build() {
            return new TheQuestionResponseModel(this);
        }
    }

    public int getQuestionId() {return questionId;}

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public boolean isClose() {return isClose;}

    public Map<Integer, PostDisplayFormatter> getPostMap() {
        return postMap;
    }
}
