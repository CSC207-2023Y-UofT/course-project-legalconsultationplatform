package adapter.presenter;

import businessrule.outputboundary.RegisterOutputBoundary;
import businessrule.responsemodel.RegisterResponseModel;
import driver.screen.ApplicationException;

import javax.swing.*;
import java.awt.*;

public class RegisterResponseFormatter implements RegisterOutputBoundary {
    CardLayout cardLayout;
    JPanel screens;

    public RegisterResponseFormatter(CardLayout cardLayout, JPanel screens) {
        this.cardLayout = cardLayout;
        this.screens = screens;
    }

    @Override
    public RegisterResponseModel prepareFail(String msg) {
        throw new ApplicationException(msg);
    }
    @Override
    public RegisterResponseModel prepareSuccess(RegisterResponseModel response) {
        cardLayout.show(screens, "Welcome");
        return response;
    }
}
