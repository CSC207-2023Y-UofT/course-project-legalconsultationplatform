package entity.factory;
import businessrule.requestmodel.RegistrationData;
import entity.Client;

public class ClientFactory implements UserFactory<Client> {
    @Override
    public Client createUser(RegistrationData data) {
        return new Client.Builder(data).build();
    }
}
