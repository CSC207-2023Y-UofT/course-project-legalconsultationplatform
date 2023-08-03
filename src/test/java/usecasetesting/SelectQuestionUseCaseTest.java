package usecasetesting;

import questionentities.Question;
import replytoquestion.*;
import org.junit.jupiter.api.Test;

import gateway.*;
import presenter.*;
import userselectquestion.SelectInputBoundary;
import userselectquestion.SelectQuestionInteractor;
import userselectquestion.SelectRequestModel;

import java.time.LocalDate;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;
;

public class SelectQuestionUseCaseTest {
    @Test
    public void UseCaseTest(){
        TheQuestionOutputBoundary theQuestionOutputBoundary = new TheQuestionOutputBoundary() {
            @Override
            public TheQuestionResponseModel prepareFail(String msg) {
                fail("Unexpected use case failure.");
                return null;
            }

            @Override
            public TheQuestionResponseModel prepareSuccess(TheQuestionResponseModel response) {
                assertEquals(123450000, response.getUserId());
                assertNotNull(response.getCreationTime());
                // assertEquals(true, repo.existsById(12345678));
                return null;
            }
        };

        QuestionGateway questionGateway = new QuestionRepo();
        Question question = new Question();
        question.setQuestionId(123450000);
        questionGateway.saveQuestion(question);

        UserGatewayFactory userGatewayFactory = new UserGatewayFactory();
        SelectInputBoundary selectInputBoundary = new SelectQuestionInteractor(questionGateway, theQuestionOutputBoundary, userGatewayFactory);

        SelectRequestModel inputData = new SelectRequestModel(123450000, 12345678);

        selectInputBoundary.selectQuestion(inputData);
    }
}