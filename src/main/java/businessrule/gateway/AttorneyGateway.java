package businessrule.gateway;

import entity.Attorney;
import entity.Question;

import java.util.List;

public interface AttorneyGateway extends UserGateway {
    public List<Attorney> getAllAttorney();
    public void clearAllRecommendations();
    public void addRecommendation (int Userid, Question question);
}
