package askquestion;

import presenter.TheQuestionResponseModel;

import java.time.LocalDate;

public class QuestionControl {
    final QuestionInputBoundary questionInput;

    public QuestionControl(QuestionInputBoundary questionInput){
        this.questionInput = questionInput;
    }

    TheQuestionResponseModel createQuestion(String questionCategory, int createAt, int askedByClient, LocalDate legalDeadline){
        QuestionRequestModel requestModel = new QuestionRequestModel(questionCategory, createAt, askedByClient, legalDeadline);

        return questionInput.createQuestion(requestModel);
    }
}
