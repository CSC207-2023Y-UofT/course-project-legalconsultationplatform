package askquestion;

public interface QuestionInputBoundary {
    QuestionResponseModel createQuestion(QuestionRequestModel questionRequestModel);
}
