import askquestion.AskQuestionInteractor;
import askquestion.QuestionControl;
import askquestion.QuestionFactory;
import askquestion.QuestionInputBoundary;
import clientregister.ClientFactory;
import clientregister.ClientRegisterInputBoundary;
import clientregister.ClientRegisterResponseModel;
import clientregister.ClientRegisterInteractor;
import gateway.UserGatewayFactory;
import screen.*;
import userentities.Client;
import userentities.User;
import userlogin.*;
import gateway.*;
import presenter.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
public class Main {
    public static void main(String[] args) {

        //User Repo
        UserGatewayFactory gatewayFactory = new UserGatewayFactory();
        UserGateway clientGateway = new ClientRepository();
        ClientFactory clientFactory = new ClientFactory();

        //Question Repo
        QuestionGateway questionGateway = new QuestionRepo();
        QuestionFactory questionFactory = new QuestionFactory();
        Client client = new Client();
        client.setUserId(12345678);
        client.setPassword("abcdefgh");

        JFrame application = new JFrame("Legal Consultation Platform");
        CardLayout cardlayout = new CardLayout();
        JPanel screens = new JPanel(cardlayout);
        application.add(screens);


        LoginOutputBoundary loginOutputBoundary = new LoginResponseFormatter(cardlayout, screens);
        UserLoginInputBoundary userLoginInteractor = new UserLoginInteractor(userGateway, loginOutputBoundary);
        UserLoginControl loginControl = new UserLoginControl(userLoginInteractor);


        TheQuestionOutputBoundary theQuestionOutputBoundary = new AskQuestionResponseFormatter(cardlayout, screens);
        QuestionInputBoundary questionInputBoundary = new AskQuestionInteractor(questionGateway, theQuestionOutputBoundary, questionFactory, (ClientGateway) userGateway);
        QuestionControl questionControl = new QuestionControl(questionInputBoundary);

        LoginUI loginUI = new LoginUI(loginControl);
        screens.add(loginUI, "Login");
        cardlayout.show(screens, "Login");
        application.pack();
        application.setVisible(true);
    }
}
