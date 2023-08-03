package businessrule.usecase;

import businessrule.gateway.UserGatewayFactory;
import businessrule.inputboundary.UserLoginInputBoundary;
import businessrule.outputboundary.HomePageOutputBoundary;
import businessrule.requestmodel.UserLoginRequestModel;
import businessrule.responsemodel.HomePageResponseModel;
import driver.database.UserGateway;
import entity.User;
import driver.screen.ApplicationException;

import java.time.LocalDateTime;

public class UserLoginInteractor implements UserLoginInputBoundary{
    final UserGatewayFactory userGatewayFactory;
    final HomePageOutputBoundary outputBoundary;

    public UserLoginInteractor(UserGatewayFactory userGatewayFactory, HomePageOutputBoundary outputBoundary) {
        this.userGatewayFactory = userGatewayFactory;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public HomePageResponseModel login(UserLoginRequestModel requestModel) {
        int inputUserId = requestModel.getUserId();
        String inputPassword = requestModel.getPassword();
        UserGateway userGateway;
        try {
            userGateway = userGatewayFactory.createUserGateway(inputUserId);
        } catch (ApplicationException e) {
            return outputBoundary.prepareFail("User ID does not exist");
        }

        if (!userGateway.existsById(inputUserId)) {
            return outputBoundary.prepareFail("User ID does not exist");
        }
        User filedUser = userGateway.getUser(inputUserId);
        String filedPassword = filedUser.getPassword();
        if (!inputPassword.equals(filedPassword)) {
            return outputBoundary.prepareFail("Password is incorrect");
        }
        LocalDateTime now = LocalDateTime.now();
        HomePageResponseModel accountResponseModel = new HomePageResponseModel(inputUserId, userGateway.getUser(inputUserId).getUserName());
        return outputBoundary.prepareSuccess(accountResponseModel);
    }
}
