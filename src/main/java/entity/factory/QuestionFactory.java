package entity.factory;

import entity.Question;

import java.time.LocalDate;

/**
 * This is a factory class for creating instances of the "Question" class.
 *
 * The QuestionFactory class provides a simple and convenient way to create new "Question" objects with
 * the specified attributes. It encapsulates the instantiation process and centralizes it in a single
 * location, promoting code reusability and maintainability.
 */
public class QuestionFactory {
    public Question create(int questionId, String type, String title, LocalDate createAt, int askedByClient, LocalDate legalDeadline){
        return new Question(questionId, type, title, createAt, askedByClient, legalDeadline);
    }
}
