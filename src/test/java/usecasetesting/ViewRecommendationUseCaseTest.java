package usecasetesting;

import entities.user.Attorney;
import entities.user.Client;
import usecases.session.SessionManager;
import usecases.session.UserSession;
import usecases.gateway.AttorneyGateway;
import usecases.gateway.ClientGateway;
import usecases.gateway.QuestionGateway;
import usecases.inputboundary.ViewInputBoundary;
import usecases.outputboundary.ViewOutputBoundary;
import usecases.responses.ViewResponseModel;
import usecases.interactors.AttorneyRecommendInteractor;
import infrastructure.database.*;
import entities.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class ViewRecommendationUseCaseTest {
    final static int CLIENT_ID = 21345678;
    final static int ATTORNEY_ID = 11345678;
    final static String ATTORNEY_USERNAME = "test attorney";
    final static String ATTORNEY_TYPE = "Attorney";
    final static int SECOND_ATTORNEY_ID = 12222222;
    final static int QUESTION_ID = 323456789;
    final static int TAKEN_QUESTION_ID = 333333333;
    final static int QUESTION_ID_4 = 312142133;
    final static int POST_ID = 455555555;
    private QuestionGateway questionGateway;
    private ClientGateway clientGateway;
    private AttorneyGateway attorneyGateway;
    private final ViewOutputBoundary viewOutputBoundary = new ViewOutputBoundary() {

        @Override
        public ViewResponseModel prepareFail(String msg) {
            return null;
        }

        @Override
        public ViewResponseModel prepareSuccess(ViewResponseModel response) {
            assertEquals(0, response.getQuestionMap().size(), "The Question Map is not correct.");
            return null;
        }

    };
    private ViewInputBoundary viewInputBoundary;

    public void setUpBrowseUseCase() {
        questionGateway = new QuestionRepo();
        clientGateway = new ClientRepository();
        attorneyGateway = new AttorneyRepository();
        clientGateway.deleteAll();
        questionGateway.deleteAll();
        attorneyGateway.deleteAll();
        viewInputBoundary = new AttorneyRecommendInteractor(viewOutputBoundary, questionGateway, attorneyGateway);

        Attorney attorney = new Attorney();
        attorney.setUserId(ATTORNEY_ID);
        Question question4 = new Question(QUESTION_ID_4, "temp type", "temp title", LocalDate.now(), CLIENT_ID, LocalDate.now());
        questionGateway.save(question4);
        attorney.addRecommendation(question4);
        attorneyGateway.save(attorney);


        Attorney secondAttorney = new Attorney();
        attorney.setUserId(SECOND_ATTORNEY_ID);
        attorneyGateway.save(secondAttorney);

        Post post = new Post();
        post.setPostId(POST_ID);

        Question question2 = new Question();
        question2.setQuestionId(TAKEN_QUESTION_ID);
        question2.setTaken(true);
        question2.setTakenByAttorney(ATTORNEY_ID);
        question2.setAskedByClient(CLIENT_ID);
        question2.addPosts(post);
        questionGateway.save(question2);



        Client client = new Client();
        client.setUserId(CLIENT_ID);
        client.addQuestion(question2);
        clientGateway.save(client);

        ViewResponseModel userResponseModel = new ViewResponseModel(ATTORNEY_ID, ATTORNEY_USERNAME, ATTORNEY_TYPE, null);
        UserSession session = new UserSession(userResponseModel);
        SessionManager.setSession(session);
    }

    @Test
    public void TestAttorneyViewRecommendationUseCase() {
        setUpBrowseUseCase();
        Post post = new Post();
        post.setPostId(POST_ID);
        Question question3 = new Question();
        question3.setQuestionId(QUESTION_ID);
        question3.setTaken(false);
        question3.setAskedByClient(CLIENT_ID);
        question3.addPosts(post);
        questionGateway.save(question3);
        ViewOutputBoundary viewOutputBoundary = new ViewOutputBoundary() {

            @Override
            public ViewResponseModel prepareFail(String msg) {
                return null;
            }

            @Override
            public ViewResponseModel prepareSuccess(ViewResponseModel response) {
                int expectedSize = 1;
                assertEquals(expectedSize, response.getQuestionMap().size(), "The Question Map is not correct.");
                assertEquals("temp type", response.getQuestionMap().get(QUESTION_ID_4).getType(), "The Question Map is not correct.");
                return null;
            }
        };
        viewInputBoundary = new AttorneyRecommendInteractor(viewOutputBoundary, questionGateway, attorneyGateway);
        viewInputBoundary.viewQuestion();
        ClearAllRepository();
    }

    public void ClearAllRepository() {
        questionGateway = new QuestionRepo();
        clientGateway = new ClientRepository();
        attorneyGateway = new AttorneyRepository();
        clientGateway.deleteAll();
        questionGateway.deleteAll();
        attorneyGateway.deleteAll();
    }
}