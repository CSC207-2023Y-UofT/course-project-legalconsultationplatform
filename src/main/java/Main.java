import businessrule.gateway.ClientGateway;
import businessrule.inputboundary.UserLoginInputBoundary;
import apapter.presenter.AskQuestionResponseFormatter;
import apapter.presenter.LoginResponseFormatter;
import businessrule.outputboundary.TheQuestionOutputBoundary;
import businessrule.usecase.AskQuestionInteractor;
import apapter.controller.QuestionControl;
import driver.database.ClientRepository;
import driver.database.QuestionGateway;
import driver.database.QuestionRepo;
import driver.screen.AskQuestionUI;
import driver.screen.LoginUI;
import entity.QuestionFactory;
import businessrule.inputboundary.QuestionInputBoundary;
import apapter.controller.UserLoginControl;
import businessrule.usecase.UserLoginInteractor;
import screen.*;
import entity.Client;
import database.*;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        ClientRepository repo = new ClientRepository();
        Client client = new Client();
        client.setUserId(12345678);
        client.setPassword("abcdefgh");

        JFrame application = new JFrame("Legal Consultation Platform");
        CardLayout cardlayout = new CardLayout();
        JPanel screens = new JPanel(cardlayout);
        application.add(screens);

        LoginOutputBoundary boundary = new LoginResponseFormatter(cardlayout, screens);
        UserLoginInputBoundary userLoginInteractor = new UserLoginInteractor(repo, boundary);
        UserLoginControl control = new UserLoginControl(userLoginInteractor);


        TheQuestionOutputBoundary theQuestionOutputBoundary = new AskQuestionResponseFormatter(cardlayout, screens);
        QuestionGateway questionGateway = new QuestionRepo();
        QuestionFactory questionFactory = new QuestionFactory();
        ClientGateway clientGateway = new ClientRepository(); // It should be the same as the repo above
        QuestionInputBoundary questionInputBoundary = new AskQuestionInteractor(questionGateway, theQuestionOutputBoundary, questionFactory, clientGateway);
        QuestionControl questionControl = new QuestionControl(questionInputBoundary);

        AskQuestionUI askQuestionUI = new AskQuestionUI(questionControl);
        LoginUI loginUI = new LoginUI(control);
        screens.add(askQuestionUI, "login");
        cardlayout.show(screens, "login");
        application.pack();
        application.setVisible(true);
    }
}
