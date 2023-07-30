package gateway;

import userentities.Attorney;
import java.util.List;

public interface AttorneyGateway extends UserGateway{
    public List<Attorney> getAllAttorney();
}
