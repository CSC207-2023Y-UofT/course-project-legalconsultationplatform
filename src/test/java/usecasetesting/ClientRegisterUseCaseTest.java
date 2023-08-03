package usecasetesting;

import questionentities.Question;
import replytoquestion.*;
import org.junit.jupiter.api.Test;

import gateway.*;
import presenter.*;
import userclosequestion.CloseInputBoundary;
import userclosequestion.CloseQuestionInteractor;
import userclosequestion.CloseRequestModel;
import userrateanswer.RateInputBoundary;
import userrateanswer.RateInteractor;
import userrateanswer.RateRequestModel;

import java.time.LocalDate;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;
;

public class ClientRegisterUseCaseTest {
//    @Test
//    public void UseCaseTest(){
//        MessageOutputBoundary messageOutputBoundary = new MessageOutputBoundary() {
//            @Override
//            public MessageResponseModel prepareFail(String msg) {
//                return null;
//            }
//
//            @Override
//            public MessageResponseModel prepareSuccess(String msg) {
//                assertEquals("Client successfully registered", msg);
//                return null;
//            }
//        };
//
//        // UserGateway userGateway = new UserGatewayFactory();
//        QuestionGateway questionGateway = new QuestionRepo();
//        Question question = new Question();
//        question.setQuestionId(12346669);
//        question.setClose(true);
//        questionGateway.saveQuestion(question);
//        UserGatewayFactory userGatewayFactory = new UserGatewayFactory();
//
//        CloseInputBoundary closeInputBoundary = new CloseQuestionInteractor(questionGateway, messageOutputBoundary, userGatewayFactory);
//
//        CloseRequestModel inputData = new CloseRequestModel(12346669, 12345678);
//
//        closeInputBoundary.closeQuestion(inputData);
// }
}
