package businessrule.gateway;

import entity.Attorney;
import entity.User;
import entity.Attorney;
import entity.Question;
import java.util.List;

public interface AttorneyGateway extends UserGateway<Attorney> {

    @Override
    Attorney get(int id);

    public List<Attorney> getAllAttorney();

    public void clearAllRecommendations();

    public void addRecommendation (int Userid, Question question);
}
