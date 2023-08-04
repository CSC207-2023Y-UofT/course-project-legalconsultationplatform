package businessrule.gateway;

import entity.Question;

import java.util.List;

public interface ClientGateway extends UserGateway {

    List<Question> getAllQuestionByClient(int clientId);

}
