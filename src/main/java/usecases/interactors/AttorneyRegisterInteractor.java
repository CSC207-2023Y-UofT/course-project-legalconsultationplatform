package usecases.interactors;

import entities.factories.AttorneyFactory;
import entities.factories.UserFactory;
import entities.user.Attorney;
import usecases.gateway.AttorneyGateway;
import usecases.gateway.UserGateway;
import usecases.outputboundary.BaseOutputBoundary;

public class AttorneyRegisterInteractor extends UserRegisterInteractor<AttorneyGateway, AttorneyFactory, Attorney> {
    public AttorneyRegisterInteractor(AttorneyGateway userGateway, BaseOutputBoundary outputBoundary, AttorneyFactory userFactory) {
        super(userGateway, outputBoundary, userFactory);
    }
}
