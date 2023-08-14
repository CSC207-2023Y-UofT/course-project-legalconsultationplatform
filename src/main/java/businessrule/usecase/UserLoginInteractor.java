package businessrule.usecase;

import businessrule.SessionManager;
import businessrule.UserSession;
import businessrule.gateway.UserGatewayFactory;
import businessrule.inputboundary.UserLoginInputBoundary;
import businessrule.outputboundary.UserOutputBoundary;
import businessrule.requestmodel.UserLoginRequestModel;
import businessrule.gateway.UserGateway;
import businessrule.responsemodel.UserResponseModel;
import businessrule.responsemodel.ViewResponseModel;
import businessrule.usecase.util.BuilderService;
import businessrule.usecase.util.QuestionDisplayFormatter;
import businessrule.usecase.util.QuestionMapConstructor;
import entity.User;
import entity.ApplicationException;

import javax.swing.text.View;
import java.util.Map;

public class UserLoginInteractor implements UserLoginInputBoundary{
    final UserGatewayFactory userGatewayFactory;
    final UserOutputBoundary outputBoundary;

    public UserLoginInteractor(UserGatewayFactory userGatewayFactory, UserOutputBoundary outputBoundary) {
        this.userGatewayFactory = userGatewayFactory;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public UserResponseModel login(UserLoginRequestModel requestModel) {
        int userId = requestModel.getUserId();
        User user = fetchUser(userId);
        if (user == null) {
            return outputBoundary.prepareFail("User ID does not exist");
        }

        if (!validatePassword(user, requestModel.getPassword())) {
            return outputBoundary.prepareFail("Password is incorrect");
        }
        UserResponseModel responseModel = constructResponseModel(user);
        setUserSession(responseModel);


        Map<Integer, QuestionDisplayFormatter> questionMap = new QuestionMapConstructor().constructQuestionMap(user.getQuestionsList());
        BuilderService.getInstance().constructViewResponse(responseModel, questionMap);
        return outputBoundary.prepareSuccess(responseModel);
    }

    private User fetchUser(int userId) {
        try {
            UserGateway<? extends User> userGateway = userGatewayFactory.createUserGateway(userId);
            if (userGateway.existsById(userId)) {
                return userGateway.get(userId);
            }
        } catch (ApplicationException e) {
            return null;
        }
        return null;
    }

    private void setUserSession(UserResponseModel response) {
        UserSession userSession = new UserSession(response);
        SessionManager.setSession(userSession);
    }

    private boolean validatePassword(User user, String inputPassword) {
        return user.getPassword().equals(inputPassword);
    }

    private UserResponseModel constructResponseModel(User user) {
        return new UserResponseModel(user.getUserId(), user.getUserName(), user.getUserType());
    }



}
