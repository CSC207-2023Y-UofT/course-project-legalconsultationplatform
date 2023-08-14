package businessrule.requestmodel;

public class PostRequestModel {
    private final int questionId;
    private final String postText;

    public PostRequestModel(int questionId, String postText){
        this.questionId = questionId;
        this.postText = postText;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getPostText() {
        return postText;
    }
}
