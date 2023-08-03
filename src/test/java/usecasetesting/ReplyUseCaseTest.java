package usecasetesting;

import questionentities.Question;
import replytoquestion.*;
import org.junit.jupiter.api.Test;

import gateway.*;
import presenter.*;

import java.time.LocalDate;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;
;

public class ReplyUseCaseTest {
    @Test
    public void UseCaseTest(){
        MessageOutputBoundary messageOutputBoundary = new MessageOutputBoundary() {
            @Override
            public MessageResponseModel prepareFail(String msg) {
                assertEquals("fail", msg);
                return null;
            }

            @Override
            public MessageResponseModel prepareSuccess(String msg) {
                assertEquals("succeed", msg);
                return null;
            }
        };

        QuestionGateway questionGateway = new QuestionRepo();
        Question question = new Question();
        question.setQuestionId(123456789);
        questionGateway.saveQuestion(question);

        PostGateway postGateway = new PostRepo();
        PostFactory postFactory = new PostFactory();
        UserGatewayFactory userGatewayFactory = new UserGatewayFactory();
        PostInputBoundary postInputBoundary = new ReplyInteractor(questionGateway, postGateway, messageOutputBoundary, postFactory, userGatewayFactory);

        PostRequestModel inputData = new PostRequestModel(123456789, 12345678, "Test text");

        postInputBoundary.createPost(inputData);
    }
}
