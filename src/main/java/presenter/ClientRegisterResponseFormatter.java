package presenter;
import screen.FailMessage;
import clientregister.ClientRegisterResponseModel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClientRegisterResponseFormatter implements ClientRegisterOutputBoundary {
    CardLayout cardLayout;
    JPanel screens;

    public ClientRegisterResponseFormatter(CardLayout cardLayout, JPanel screens) {
        this.cardLayout = cardLayout;
        this.screens = screens;
    }

    @Override
    public ClientRegisterResponseModel prepareFail(String msg) {
        throw new FailMessage(msg);
    }

    @Override
    public ClientRegisterResponseModel prepareSuccess(ClientRegisterResponseModel response) {
        LocalDateTime responseTime = LocalDateTime.parse(response.getCreationTime());
        response.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));
        cardLayout.show(screens, "Welcome");
        return response;
    }
}