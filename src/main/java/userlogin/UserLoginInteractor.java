package userlogin;

import presenter.LoginOutputBoundary;
import presenter.LoginResponseModel;
import gateway.UserGateway;

public class UserLoginInteractor implements UserLoginInputBoundary{
    final UserGateway userGateway;
    final LoginOutputBoundary outputBoundary;

    public UserLoginInteractor(UserGateway userGateway,
                               LoginOutputBoundary outputBoundary) {
        this.userGateway = userGateway;
        this.outputBoundary = outputBoundary;
    }
    @Override
    public LoginResponseModel login(UserLoginRequestModel requestModel){
        if (!userGateway.existsById(requestModel.getUserId())) {
            return outputBoundary.prepareFail("UserId does not exist");
        }
        else if (!requestModel.getPassword().equals(userGateway.getPassword(requestModel.getUserId()))) {
            return outputBoundary.prepareFail("Password is incorrect");
        }
        return outputBoundary.prepareSuccess();
    }
}
