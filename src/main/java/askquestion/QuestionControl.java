package askquestion;

import presenter.TheQuestionResponseModel;

import java.time.LocalDate;

public class QuestionControl {
    final QuestionInputBoundary questionInput;

    public QuestionControl(QuestionInputBoundary questionInput){
        this.questionInput = questionInput;
    }

    public TheQuestionResponseModel createQuestion(String questionCategory, String title, LocalDate createAt, int askedByClient, LocalDate legalDeadline){
        QuestionRequestModel requestModel = new QuestionRequestModel(questionCategory, title, createAt, askedByClient, legalDeadline);

        return questionInput.createQuestion(requestModel);
    }
}
