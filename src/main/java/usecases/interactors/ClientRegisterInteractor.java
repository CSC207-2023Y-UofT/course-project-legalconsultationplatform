package usecases.interactors;


import entities.ApplicationException;
import usecases.gateway.ClientGateway;
import usecases.outputboundary.BaseOutputBoundary;
import entities.user.Client;
import entities.factories.ClientFactory;
import usecases.requests.RegistrationData;
import usecases.utils.RandomNumberGenerator;

/**
 * This class represents interactor responsible for client registration use case.
 */
public class ClientRegisterInteractor extends UserRegisterInteractor<ClientGateway, ClientFactory, Client> {

    /**
     * Constructs a ClientRegisterInteractor.
     *
     * @param userGateway    The gateway for user-related operations.
     * @param outputBoundary The output boundary for the registration process.
     * @param clientFactory The factory to create client entity
     */
    public ClientRegisterInteractor(ClientGateway userGateway, BaseOutputBoundary outputBoundary, ClientFactory clientFactory) {
        super(userGateway, outputBoundary, clientFactory);
    }

    @Override
    protected void checkCredential(RegistrationData requestModel) throws ApplicationException {
        super.checkCredential(requestModel);
        int inputAge = requestModel.age;
        if (!checker.checkAge(inputAge)) {
            throw new ApplicationException("Age is invalid");
        }
    }

    @Override
    protected int generateId() {
        RandomNumberGenerator generator = new RandomNumberGenerator();
        int randomUserId = generator.generateClientId(8);
        boolean exists = userGateway.existsById(randomUserId);
        while (exists) {
            randomUserId = generator.generateClientId(8);
            exists = userGateway.existsById(randomUserId);
        } return randomUserId;
    }
}