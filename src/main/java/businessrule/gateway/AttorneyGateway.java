package businessrule.gateway;

import entity.Question;

import java.util.List;

public interface AttorneyGateway extends UserGateway {

    List<Question> getAllQuestionByAttorney(int attorneyId);

}
