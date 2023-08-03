//package usecasetesting;
//
//import questionentities.Question;
//import replytoquestion.*;
//import org.junit.jupiter.api.Test;
//
//import gateway.*;
//import presenter.*;
//import userentities.Client;
//import userlogin.LoginResponseModel;
//import userlogin.UserLoginInputBoundary;
//import userlogin.UserLoginInteractor;
//import userlogin.UserLoginRequestModel;
//import userrateanswer.RateInputBoundary;
//import userrateanswer.RateInteractor;
//import userrateanswer.RateRequestModel;
//
//import java.time.LocalDate;
//
//import static org.junit.Assert.fail;
//import static org.junit.jupiter.api.Assertions.*;
//;
//
//public class UserLoginUseCaseTest {
//    @Test
//    public void UseCaseTest(){
//
//        LoginOutputBoundary loginOutputBoundary = new LoginOutputBoundary() {
//            @Override
//            public LoginResponseModel prepareFail(String msg) {
//                assertEquals("This question is not closed.", msg);
//                fail("Unexpected use case failure.");
//                return null;
//            }
//
//            @Override
//            public LoginResponseModel prepareSuccess(LoginResponseModel response) {
//
//                return null;
//            }
//        };
//
//        ClientGateway clientGateway = new ClientRepository();
//        Client client = new Client();
//        client.setUserId(123456701);
//        client.setPassword("abcdefgh");
//        clientGateway.addUser(client);
//
//        UserLoginInputBoundary userLoginInputBoundary = new UserLoginInteractor(clientGateway, loginOutputBoundary);
//
//        UserLoginRequestModel inputData = new UserLoginRequestModel(123456701, "abcdefgh");
//
//        userLoginInputBoundary.login(inputData);
//    }
//}
