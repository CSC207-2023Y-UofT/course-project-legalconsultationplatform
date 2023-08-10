package usecasetesting;

import adapter.controller.ControlContainer;
import businessrule.gateway.*;
import businessrule.inputboundary.PostInputBoundary;
import businessrule.outputboundary.HomePageOutputBoundary;
import businessrule.requestmodel.RegistrationData;
import businessrule.responsemodel.HomePageResponseModel;
import businessrule.usecase.ReplyInteractor;
import businessrule.usecase.util.MatchingHandler;
import driver.database.AttorneyRepository;
import driver.database.ClientRepository;
import driver.database.PostRepo;
import driver.database.QuestionRepo;
import entity.Attorney;
import entity.Client;
import entity.Question;
import entity.factory.AttorneyFactory;
import entity.factory.ClientFactory;
import entity.factory.PostFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import adapter.controller.ControlContainer;
import businessrule.gateway.*;
import businessrule.inputboundary.PostInputBoundary;
import businessrule.outputboundary.HomePageOutputBoundary;
import businessrule.requestmodel.PostRequestModel;
import businessrule.responsemodel.HomePageResponseModel;
import businessrule.usecase.ReplyInteractor;
import driver.database.*;

import entity.*;

import entity.factory.PostFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
;
public class MatchingHandlerTest {

    final static int CLIENT_ID = 21345678;
    final static int ATTORNEY_ID = 11345678;
    final static int SECOND_ATTORNEY_ID = 12222222;
    final static int QUESTION_ID = 323456789;
    final static int CLOSED_QUESTION_ID = 333333333;
    private QuestionGateway questionGateway;
    private PostGateway postGateway;
    private PostFactory postFactory;
    private UserGatewayFactory userGatewayFactory;
    private ClientGateway clientGateway;
    private AttorneyGateway attorneyGateway;
    public void setUpReplyUseCase(){

        questionGateway = new QuestionRepo();
        postGateway = new PostRepo();
        postFactory = new PostFactory();
        userGatewayFactory = new UserGatewayFactory();
        clientGateway = new ClientRepository();
        attorneyGateway = new AttorneyRepository();;


//        String clientName = "joseph";
//        String clientEmail = "bob.bob@gmail.com";
//        String clientPassword = "bob123321";
//        String clientPassword2 = "bob123321";
//        String clientState = "ON";
//        String clientPostalCode = "M1MA6A";
//        String clientEthnicity = "asian";
//        int clientAge = 20;
//        String clientGender = "Male";
//        String clientMaritalStatus = "Single";
//        int clientNumHouseHold = 1;
//        float clientAnnualIncome = 100;
//
//        RegistrationData registrationData = new RegistrationData(clientName, clientEmail, clientPassword, clientPassword2, clientState, clientPostalCode, clientEthnicity, clientAge, clientGender, clientMaritalStatus, clientNumHouseHold, clientAnnualIncome);
//
//        ClientFactory clientFactory = new ClientFactory();
//        Client c  = clientFactory.createUser(registrationData);
//
//        clientGateway.save(c);
//
//        //Attorney
//        String attorneyUsername = "yao";
//        String attorneyEmail = "yao.yao@gmail.com";
//        String attorneyPassword = "yao123321";
//        String attorneyPassword2 = "yao123321";
//        String attorneyState = "ON";
//        String attorneyPostalCode = "M8MO1P";
//
//        RegistrationData registrationData2 = new RegistrationData(attorneyUsername, attorneyEmail, attorneyPassword, attorneyPassword2, attorneyState, attorneyPostalCode);
//        AttorneyFactory attorneyFactory = new AttorneyFactory();
//        Attorney a = attorneyFactory.createUser(registrationData2);
//
//        attorneyGateway.save(a);
//
//        //Question
//        String type = "Theft";
//        String title = "hi";
//        LocalDate createAt = LocalDate.now();
//        LocalDate legalDeadLine = LocalDate.now();
//
//        Question q = new Question(QUESTION_ID, type, title, createAt, CLIENT_ID, legalDeadLine);
//
//        questionGateway.save(q);
    }

    @Test
    public void GetMatchingTest() throws IOException {
        MatchingHandler matchingHandler = new MatchingHandler(attorneyGateway, clientGateway, questionGateway);
        System.out.println(questionGateway.getNotTakenQuestion());
        System.out.println(matchingHandler.getMatching());
    }
}
