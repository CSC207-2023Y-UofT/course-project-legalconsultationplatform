package businessrule.usecase;

import apapter.LoginOutputBoundary;
import businessrule.inputboundary.UserLoginInputBoundary;
import businessrule.requestmodel.UserLoginRequestModel;
import driver.database.UserGateway;
import apapter.LoginResponseModel;
import entity.User;
import java.time.LocalDateTime;

public class UserLoginInteractor implements UserLoginInputBoundary {
    final UserGateway userGateway;
    final LoginOutputBoundary outputBoundary;

    public UserLoginInteractor(UserGateway userGateway,
                               LoginOutputBoundary outputBoundary) {
        this.userGateway = userGateway;
        this.outputBoundary = outputBoundary;
    }
    @Override
    public LoginResponseModel login(UserLoginRequestModel requestModel){
        int inputUserId = requestModel.getUserId();
        String inputPassword = requestModel.getPassword();
        if (!userGateway.existsById(inputUserId)) {
            return outputBoundary.prepareFail("UserId does not exist");
        }
        User filedUser = userGateway.getUser(inputUserId);
        String filedPassword = filedUser.getPassword();
        if (!inputPassword.equals(filedPassword)) {
            return outputBoundary.prepareFail("Password is incorrect");
        }
        LocalDateTime now = LocalDateTime.now();
        LoginResponseModel accountResponseModel = new LoginResponseModel(inputUserId, now.toString());
        return outputBoundary.prepareSuccess(accountResponseModel);
    }
}
