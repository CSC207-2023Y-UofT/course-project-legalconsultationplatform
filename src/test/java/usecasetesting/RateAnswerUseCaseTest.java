//package usecasetesting;
//
//
//import org.junit.jupiter.api.Test;
//
//
//import static org.junit.jupiter.api.Assertions.*;
//;
//
//public class RateAnswerUseCaseTest {
//    @Test
//    public void UseCaseTest(){
//        MessageOutputBoundary messageOutputBoundary = new MessageOutputBoundary() {
//            @Override
//            public MessageResponseModel prepareFail(String msg) {
//                assertEquals("This question is not closed.", msg);
//                return null;
//            }
//
//            @Override
//            public MessageResponseModel prepareSuccess(String msg) {
//                assertEquals("Rating successful!", msg);
//                return null;
//            }
//        };
//
//        QuestionGateway questionGateway = new QuestionRepo();
//        Question question = new Question();
//        question.setQuestionId(12345669);
//        question.setClose(true);
//        questionGateway.saveQuestion(question);
//
//        RateInputBoundary rateInputBoundary = new RateInteractor(questionGateway, messageOutputBoundary);
//
//        RateRequestModel inputData = new RateRequestModel(10, 12345669);
//
//        rateInputBoundary.rateAnswer(inputData);
//    }
//}
