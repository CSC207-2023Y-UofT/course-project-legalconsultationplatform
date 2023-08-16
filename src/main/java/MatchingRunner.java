import infrastructure.database.AttorneyRepository;
import infrastructure.database.ClientRepository;
import infrastructure.database.QuestionRepo;
import usecases.gateway.AttorneyGateway;
import usecases.gateway.ClientGateway;
import usecases.gateway.QuestionGateway;
import usecases.utils.MatchingHandler;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MatchingRunner {
    public static void main(String[] args) {
//        uncomment to schedule a timed task
//        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        AttorneyGateway attorneyGateway1 = new AttorneyRepository();
        ClientGateway clientGateway1 = new ClientRepository();
        QuestionGateway questionGateway1 = new QuestionRepo();
        MatchingHandler matchingHandler = new MatchingHandler(attorneyGateway1, clientGateway1, questionGateway1);
        matchingHandler.match();
//        scheduler.scheduleAtFixedRate(matchingHandler::match, 0, 7, TimeUnit.DAYS);
    }
}
