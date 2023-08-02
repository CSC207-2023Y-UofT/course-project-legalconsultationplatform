import askquestion.AskQuestionInteractor;
import askquestion.QuestionControl;
import askquestion.QuestionFactory;
import askquestion.QuestionInputBoundary;
import screen.*;
import userentities.Client;
import userlogin.*;
import gateway.*;
import presenter.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
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
