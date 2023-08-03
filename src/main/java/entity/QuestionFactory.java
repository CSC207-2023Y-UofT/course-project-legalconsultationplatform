package entity;

import entity.Question;

import java.time.LocalDate;

public class QuestionFactory {
    public Question create(int questionId, String type, String title, LocalDate createAt, int askedByClient, LocalDate legalDeadline){
        return new Question(questionId, type, title, createAt, askedByClient, legalDeadline);
    }
}
