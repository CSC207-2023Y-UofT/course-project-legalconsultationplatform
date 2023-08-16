package usecases.interactors;

import usecases.outputboundary.ViewOutputBoundary;
import usecases.responses.ViewResponseModel;
import usecases.session.SessionManager;
import usecases.session.UserSession;
import infrastructure.database.UserGatewayFactory;
import usecases.inputboundary.UserLoginInputBoundary;
import usecases.requests.UserLoginRequestModel;
import usecases.gateway.UserGateway;
import usecases.responses.UserResponseModel;
import usecases.utils.BuilderService;
import usecases.dto.QuestionDisplay;
import usecases.utils.QuestionMapConstructor;
import entities.user.User;
import entities.ApplicationException;

import java.util.Map;

/**
 * This class represents the interactor responsible for handling user login.
 * This interactor handles the process of authenticating user login credentials,retrieving user information,
 * and preparing the appropriate response model.
 */
public class UserLoginInteractor implements UserLoginInputBoundary{
    final UserGatewayFactory userGatewayFactory;
    final ViewOutputBoundary outputBoundary;

    /**
     * Constructor for UserLoginInteractor.
     *
     * @param userGatewayFactory The factory for creating user gateways.
     * @param outputBoundary The output boundary for preparing home page response models.
     */
    public UserLoginInteractor(UserGatewayFactory userGatewayFactory, ViewOutputBoundary outputBoundary) {
        this.userGatewayFactory = userGatewayFactory;
        this.outputBoundary = outputBoundary;
    }

    /**
     * Authenticate user login and prepare the response model.
     *
     * @param requestModel The request model containing user login details.
     * @return The response model for the home page.
     */
    @Override
    public ViewResponseModel login(UserLoginRequestModel requestModel) {
        int userId = requestModel.getUserId();
        User user = fetchUser(userId);
        if (user == null) {
            return outputBoundary.prepareFail("User ID does not exist");
        }

        if (!validatePassword(user, requestModel.getPassword())) {
            return outputBoundary.prepareFail("Password is incorrect");
        }
        UserResponseModel responseModel = constructResponseModel(user);
        Map<Integer, QuestionDisplay> questionMap = new QuestionMapConstructor().constructQuestionMap(user.getQuestionsList());
        ViewResponseModel viewResponseModel =  BuilderService.getInstance().constructViewResponse(responseModel, questionMap);
        setUserSession(viewResponseModel);
        return outputBoundary.prepareSuccess(viewResponseModel);
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

    private void setUserSession(ViewResponseModel response) {
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
