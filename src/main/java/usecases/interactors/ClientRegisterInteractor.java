package usecases.interactors;


import usecases.gateway.ClientGateway;
import usecases.outputboundary.BaseOutputBoundary;
import entities.user.Client;
import entities.factories.ClientFactory;

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
}