package presenter;
import static java.time.LocalTime.*;

public class LoginPresenter implements LoginOutputBoundary{
    @Override
    public LoginResponseModel prepareFail(String msg) {
        return new LoginResponseModel(msg);
    }

    @Override
    public LoginResponseModel prepareSuccess(int inputUserId) {
        return new LoginResponseModel(inputUserId, now());
    }
}
