package usecasetesting;

import adapter.controller.ControlContainer;
import businessrule.gateway.*;
import businessrule.inputboundary.PostInputBoundary;
import businessrule.outputboundary.HomePageOutputBoundary;
import businessrule.requestmodel.RegistrationData;
import businessrule.responsemodel.HomePageResponseModel;
import businessrule.usecase.ReplyInteractor;
import businessrule.usecase.util.MatchingHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

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
    public void setUpUseCase(){

        questionGateway = new QuestionRepo();
        postGateway = new PostRepo();
        postFactory = new PostFactory();
        userGatewayFactory = new UserGatewayFactory();
        clientGateway = new ClientRepository();
        attorneyGateway = new AttorneyRepository();;

        String clientName = "joseph";
        String clientEmail = "bob.bob@gmail.com";
        String clientPassword = "bob123321";
        String clientPassword2 = "bob123321";
        String clientState = "ON";
        String clientPostalCode = "67890";
        String clientEthnicity = "asian";
        int clientAge = 20;
        String clientGender = "Male";
        String clientMaritalStatus = "Single";
        int clientNumHouseHold = 1;
        float clientAnnualIncome = 100;

        clientGateway.deleteAll();
        attorneyGateway.deleteAll();
        questionGateway.deleteAll();

        RegistrationData registrationData = new RegistrationData(clientName, clientEmail, clientPassword, clientPassword2, clientState, clientPostalCode, clientEthnicity, clientAge, clientGender, clientMaritalStatus, clientNumHouseHold, clientAnnualIncome);

        ClientFactory clientFactory = new ClientFactory();
        registrationData.setUserId(CLIENT_ID);
        Client c  = clientFactory.createUser(registrationData);

        clientGateway.save(c);

        //Attorney
        String attorneyUsername = "yao";
        String attorneyEmail = "yao.yao@gmail.com";
        String attorneyPassword = "yao123321";
        String attorneyPassword2 = "yao123321";
        String attorneyState = "ON";
        String attorneyPostalCode = "12345";

        RegistrationData registrationData2 = new RegistrationData(attorneyUsername, attorneyEmail, attorneyPassword, attorneyPassword2, attorneyState, attorneyPostalCode);
        AttorneyFactory attorneyFactory = new AttorneyFactory();
        registrationData2.setUserId(ATTORNEY_ID);
        Attorney a = attorneyFactory.createUser(registrationData2);

        attorneyGateway.save(a);

        //Question
        String type = "Theft";
        String title = "hi";
        LocalDate createAt = LocalDate.now();
        LocalDate legalDeadLine = LocalDate.now();

        Question q = new Question(QUESTION_ID, type, title, createAt, CLIENT_ID, legalDeadLine);
        q.setTakenByAttorney(ATTORNEY_ID);

        questionGateway.save(q);
    }

    @Test
    public void GetMatchingTest() throws IOException {
        setUpUseCase();
        MatchingHandler matchingHandler = new MatchingHandler(attorneyGateway, clientGateway, questionGateway);
        System.out.println(matchingHandler.getMatching().getMatchingResult());
        ClearAllRepository();
    }

    @Test
    public void ContructWeightTest() throws IOException {
        setUpUseCase();
        MatchingHandler matchingHandler = new MatchingHandler(attorneyGateway, clientGateway, questionGateway);
        List<Question> questionList = questionGateway.getNotTakenQuestion();
        List<Attorney> attorneyList = attorneyGateway.getAll();
        Map<Integer[], Double> weights = matchingHandler.constructWeight(questionList, attorneyList);
        System.out.println(new ObjectMapper().writeValueAsString(weights));
    }

    public void ClearAllRepository(){
        questionGateway = new QuestionRepo();
        clientGateway = new ClientRepository();
        attorneyGateway = new AttorneyRepository();
        postGateway = new PostRepo();
        clientGateway.deleteAll();
        questionGateway.deleteAll();
        attorneyGateway.deleteAll();
        postGateway.deleteAll();

    }


}
