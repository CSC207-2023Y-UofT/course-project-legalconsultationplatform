package usecases.interactors;

import entities.ApplicationException;
import entities.factories.AttorneyFactory;
import entities.user.Attorney;
import usecases.gateway.AttorneyGateway;
import usecases.outputboundary.BaseOutputBoundary;
import usecases.requests.RegistrationData;
import usecases.utils.RandomNumberGenerator;

import java.util.Set;

public class AttorneyRegisterInteractor extends UserRegisterInteractor<AttorneyGateway, AttorneyFactory, Attorney> {
    public AttorneyRegisterInteractor(AttorneyGateway userGateway, BaseOutputBoundary outputBoundary, AttorneyFactory userFactory) {
        super(userGateway, outputBoundary, userFactory);
    }

    @Override
    protected void checkCredential(RegistrationData requestModel) throws ApplicationException {
        super.checkCredential(requestModel);
        Set<String> professionals = requestModel.professionals;
        if (professionals == null) {
            throw new ApplicationException("Please select at least one area of professionals");
        }
    }

    @Override
    protected int generateId() {
        RandomNumberGenerator generator = new RandomNumberGenerator();
        int randomUserId = generator.generateAttorneyId(8);
        boolean exists = userGateway.existsById(randomUserId);
        while (exists) {
            randomUserId = generator.generateAttorneyId(8);
            exists = userGateway.existsById(randomUserId);
        } return randomUserId;
    }
}
