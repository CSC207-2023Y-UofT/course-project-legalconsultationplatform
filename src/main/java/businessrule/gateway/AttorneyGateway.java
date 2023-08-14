package businessrule.gateway;

import entity.Attorney;
import entity.Question;

public interface AttorneyGateway extends UserGateway<Attorney> {

    @Override
    Attorney get(int id);

    void clearAllRecommendations();

    void addRecommendation (int Userid, Question question);
}
