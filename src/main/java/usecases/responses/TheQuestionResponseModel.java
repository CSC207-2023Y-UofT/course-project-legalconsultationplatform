package usecases.responses;

import usecases.dto.PostDisplay;
import java.time.LocalDate;
import java.util.Map;

/**
 * This class represents a question with detailed information.
 * It is including its user, title, type, deadline, closure status, and associated posts.
 */
public class TheQuestionResponseModel extends UserResponseModel{
    private final int questionId;
    private final String title;
    private final String type;
    private final LocalDate deadline;
    private final boolean isClose;
    private final Map<Integer, PostDisplay> postMap;

    private TheQuestionResponseModel(Builder builder) {
        super(builder.userId, builder.userName, builder.userType);
        this.questionId = builder.questionId;
        this.title = builder.questionTitle;
        this.type = builder.questionType;
        this.deadline = builder.deadline;
        this.isClose = builder.isClose;
        this.postMap = builder.postMap;
    }

    public TheQuestionResponseModel(int userId, String userName, String userType, int questionId, String title, String type, LocalDate deadline, boolean isClose, Map<Integer, PostDisplay> postMap) {
        super(userId, userName, userType);
        this.questionId = questionId;
        this.title = title;
        this.type = type;
        this.deadline = deadline;
        this.isClose = isClose;
        this.postMap = postMap;
    }

    public static class Builder {
        private final int userId;
        private final String userName;
        private final String userType;
        private int questionId;
        private String questionTitle;
        private String questionType;
        private LocalDate deadline;
        private boolean isClose;
        private Map<Integer, PostDisplay> postMap;

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

        public Builder postMap(Map<Integer, PostDisplay> postMap) {
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

    public Map<Integer, PostDisplay> getPostMap() {
        return postMap;
    }
}
