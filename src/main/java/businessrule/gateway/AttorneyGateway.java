package businessrule.gateway;

import entity.Attorney;

import java.util.List;

public interface AttorneyGateway extends UserGateway {
    public List<Attorney> getAllAttorney();
    public void clearAllRecommendations();
    public void updateRecommendations(int Userid);
}
