package usecasetesting;

import questionentities.Question;
import org.junit.jupiter.api.Test;

import gateway.*;
import presenter.*;
import userselectquestion.SelectInputBoundary;
import userselectquestion.SelectQuestionInteractor;
import userselectquestion.SelectRequestModel;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;
;

public class UserViewHistoryUseCaseTest {
    @Test
    public void UseCaseTest(){
//        ViewOutputBoundary viewOutputBoundary = new ViewOutputBoundary() {
//            @Override
//            public ViewResponseModel prepareFail(String msg) {
//                fail("Unexpected use case failure.");
//                return null;
//            }
//
//            @Override
//            public ViewResponseModel prepareSuccess(TheQuestionResponseModel response) {
//                assertEquals(123450000, response.getUserId());
//                assertNotNull(response.getCreationTime());
//                // assertEquals(true, repo.existsById(12345678));
//                return null;
//            }
//        };
//
//        QuestionGateway questionGateway = new QuestionRepo();
//        Question question = new Question();
//        question.setQuestionId(123450000);
//        questionGateway.saveQuestion(question);
//
//        UserGatewayFactory userGatewayFactory = new UserGatewayFactory();
//        SelectInputBoundary selectInputBoundary = new SelectQuestionInteractor(questionGateway, theQuestionOutputBoundary, userGatewayFactory);
//
//        SelectRequestModel inputData = new SelectRequestModel(123450000, 12345678);
//
//        selectInputBoundary.selectQuestion(inputData);
    }
}