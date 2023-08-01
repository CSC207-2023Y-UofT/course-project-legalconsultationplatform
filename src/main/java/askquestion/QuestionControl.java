package askquestion;

import presenter.TheQuestionResponseModel;

import java.time.LocalDate;

public class QuestionControl {
    final QuestionInputBoundary questionInput;

    public QuestionControl(QuestionInputBoundary questionInput){
        this.questionInput = questionInput;
    }

    public TheQuestionResponseModel createQuestion(String questionCategory, LocalDate createAt, int askedByClient, LocalDate legalDeadline){
        QuestionRequestModel requestModel = new QuestionRequestModel(questionCategory, createAt, askedByClient, legalDeadline);

        return questionInput.createQuestion(requestModel);
    }
}
