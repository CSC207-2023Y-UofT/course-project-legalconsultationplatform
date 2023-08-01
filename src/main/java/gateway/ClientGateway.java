package gateway;

public interface ClientGateway extends UserGateway{

    boolean existsByUsername(String username);

}
