package usecases.interactors;

import entities.ApplicationException;
import entities.factories.AttorneyFactory;
import entities.factories.UserFactory;
import entities.user.Attorney;
import usecases.gateway.AttorneyGateway;
import usecases.gateway.UserGateway;
import usecases.outputboundary.BaseOutputBoundary;
import usecases.requests.RegistrationData;

import java.util.Set;

public class AttorneyRegisterInteractor extends UserRegisterInteractor<AttorneyGateway, AttorneyFactory, Attorney> {
    public AttorneyRegisterInteractor(AttorneyGateway userGateway, BaseOutputBoundary outputBoundary, AttorneyFactory userFactory) {
        super(userGateway, outputBoundary, userFactory);
    }

    @Override
    protected void checkCredential(RegistrationData requestModel) throws ApplicationException {
        super.checkCredential(requestModel);
        Set<String> professionals = requestModel.professionals;
        if (professionals.isEmpty()) {
            throw new ApplicationException("Please select at least one area of professionals");
        }
    }
}
