package entities.factories;

import entities.Question;

import java.time.LocalDate;

/**
 * This is class represnts for creating instances of the "Question" class.
 */
public class QuestionFactory {
    public Question create(int questionId, String type, String title, LocalDate createAt, int askedByClient, LocalDate legalDeadline){
        return new Question(questionId, type, title, createAt, askedByClient, legalDeadline);
    }
}
