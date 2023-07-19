package askquestion;

import java.time.LocalDate;

public class QuestionControl {
    final QuestionInputBoundary questionInput;

    public QuestionControl(QuestionInputBoundary questionInputBoundary){
        this.questionInput = questionInputBoundary;
    }

    QuestionResponseModel createQuestion(String questionCategory, int createAt, int askedByClient, LocalDate legalDeadline){
        QuestionRequestModel requestModel = new QuestionRequestModel(questionCategory, createAt, askedByClient, legalDeadline);

        return questionInput.createQuestion(requestModel);
    }
}
