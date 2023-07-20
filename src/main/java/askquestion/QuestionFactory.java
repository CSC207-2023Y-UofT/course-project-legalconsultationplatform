package askquestion;

import questionentities.Question;

import java.time.LocalDate;

public class QuestionFactory {
    public Question create(int questionId, String type, LocalDate createAt, int askedByClient, LocalDate legalDeadline){
        return new Question(questionId, type, createAt, askedByClient, legalDeadline);
    }
}
