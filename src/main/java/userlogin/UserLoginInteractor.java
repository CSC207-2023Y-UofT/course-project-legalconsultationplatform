package userlogin;

import gateway.UserGatewayFactory;
import presenter.LoginOutputBoundary;
import presenter.LoginResponseModel;
import gateway.UserGateway;
import userentities.User;
import screen.ApplicationException;

public class UserLoginInteractor implements UserLoginInputBoundary{
    final UserGatewayFactory userGatewayFactory;
    final LoginOutputBoundary outputBoundary;

    public UserLoginInteractor(UserGatewayFactory userGatewayFactory,
                               LoginOutputBoundary outputBoundary) {
        this.userGatewayFactory = userGatewayFactory;
        this.outputBoundary = outputBoundary;
    }
    @Override
    public LoginResponseModel login(UserLoginRequestModel requestModel) {
        int inputUserId = requestModel.getUserId();
        String inputPassword = requestModel.getPassword();
        UserGateway userGateway;
        try {
            userGateway = userGatewayFactory.createUserGateway(inputUserId);
        } catch (ApplicationException e) {
            return outputBoundary.prepareFail("UserId does not exist");
        }

        if (!userGateway.existsById(inputUserId)) {
            return outputBoundary.prepareFail("UserId does not exist");
        }
        User filedUser = userGateway.getUser(inputUserId);
        String filedPassword = filedUser.getPassword();
        if (!inputPassword.equals(filedPassword)) {
            return outputBoundary.prepareFail("Password is incorrect");
        }
        return outputBoundary.prepareSuccess();
    }
}
