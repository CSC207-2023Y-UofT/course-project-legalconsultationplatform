package businessrule.usecase;

import businessrule.SessionManager;
import businessrule.UserSession;
import businessrule.gateway.UserGatewayFactory;
import businessrule.inputboundary.UserLoginInputBoundary;
import businessrule.outputboundary.HomePageOutputBoundary;
import businessrule.requestmodel.UserLoginRequestModel;
import businessrule.responsemodel.HomePageResponseModel;
import businessrule.gateway.UserGateway;
import entity.User;
import entity.ApplicationException;

public class UserLoginInteractor implements UserLoginInputBoundary{
    final UserGatewayFactory userGatewayFactory;
    final HomePageOutputBoundary outputBoundary;

    public UserLoginInteractor(UserGatewayFactory userGatewayFactory, HomePageOutputBoundary outputBoundary) {
        this.userGatewayFactory = userGatewayFactory;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public HomePageResponseModel login(UserLoginRequestModel requestModel) {
        // get input data
        int inputUserId = requestModel.getUserId();
        String inputPassword = requestModel.getPassword();

        // use user gateway factory to retrieve the correct type of repo
        UserGateway<? extends User> userGateway;
        try {
            userGateway = userGatewayFactory.createUserGateway(inputUserId);
        } catch (ApplicationException e) {
            return outputBoundary.prepareFail("User ID does not exist");
        }

        // handle login logic
        if (!userGateway.existsById(inputUserId)) {
            return outputBoundary.prepareFail("User ID does not exist");
        }
        User filedUser = userGateway.get(inputUserId);
        String filedPassword = filedUser.getPassword();
        if (!inputPassword.equals(filedPassword)) {
            return outputBoundary.prepareFail("Password is incorrect");
        }

        // construct response model
        String userType;
        User user = userGateway.get(inputUserId);
        if (user.isClient()){
            userType = "Client";
        } else{
            userType = "Attorney";
        }
        HomePageResponseModel accountResponseModel = new HomePageResponseModel(inputUserId,
                userGateway.get(inputUserId).getUserName(), userType);
        return outputBoundary.prepareSuccess(accountResponseModel);
    }


}
