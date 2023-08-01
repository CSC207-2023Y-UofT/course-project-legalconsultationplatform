package presenter;
import screen.UserCreationFailed;
import userlogin.LoginResponseModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoginResponseFormatter implements LoginOutputBoundary{
    @Override
    public LoginResponseModel prepareFail(String msg) {
        throw new UserCreationFailed(msg);
    }

    @Override
    public LoginResponseModel prepareSuccess(LoginResponseModel response) {
        LocalDateTime responseTime = LocalDateTime.parse(response.getCreationTime());
        response.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));
        return response;
    }
}
