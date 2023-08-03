import adapter.controller.*;
import adapter.presenter.HomePageResponseFormatter;
import adapter.presenter.RegisterResponseFormatter;
import adapter.presenter.TheQuestionResponseFormatter;
import adapter.presenter.ViewResponseFormatter;
import businessrule.gateway.AttorneyGateway;
import businessrule.gateway.ClientGateway;
import businessrule.gateway.UserGatewayFactory;
import businessrule.inputboundary.*;
import businessrule.outputboundary.HomePageOutputBoundary;
import businessrule.outputboundary.RegisterOutputBoundary;
import businessrule.outputboundary.TheQuestionOutputBoundary;
import businessrule.outputboundary.ViewOutputBoundary;
import businessrule.usecase.*;
import driver.database.*;
import driver.screen.WelcomeUI;
import entity.*;

import javax.naming.ldap.Control;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        //define repo
        UserGatewayFactory gatewayFactory = new UserGatewayFactory();
        ClientGateway clientGateway = new ClientRepository();
        AttorneyGateway attorneyGateway = new AttorneyRepository();
        ClientFactory clientFactory = new ClientFactory();

        QuestionFactory questionFactory = new QuestionFactory();
        QuestionGateway questionGateway = new QuestionRepo();
        PostFactory postFactory = new PostFactory();
        PostGateway postGateway = new PostRepo();
        System.out.println("finished repo");

        //set up jframe
        JFrame application = new JFrame("Legal Consultation Platform");
        application.setSize(1000, 1000);
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CardLayout cardlayout = new CardLayout();
        JPanel screens = new JPanel(cardlayout);
        application.add(screens);
        System.out.println("Finished JFrame");

        //define outputBoundary
        HomePageOutputBoundary homePageOutputBoundary = new HomePageResponseFormatter(cardlayout, screens);
        RegisterOutputBoundary registerOutputBoundary = new RegisterResponseFormatter(cardlayout, screens);
        TheQuestionOutputBoundary theQuestionOutputBoundary = new TheQuestionResponseFormatter();
        ViewOutputBoundary viewOutputBoundary = new ViewResponseFormatter();
        System.out.println("Finished outputBoundary");

        //define useCase
        UserLoginInputBoundary userLoginInteractor = new UserLoginInteractor(gatewayFactory, homePageOutputBoundary);
        UserLoginControl loginControl = new UserLoginControl(userLoginInteractor);

        ClientRegisterInputBoundary clientRegisterInteractor = new ClientRegisterInteractor(clientGateway, registerOutputBoundary,
                clientFactory);
        ClientRegisterControl registerControl = new ClientRegisterControl(clientRegisterInteractor);

        QuestionInputBoundary questionInteractor = new AskQuestionInteractor(questionGateway, theQuestionOutputBoundary,
                questionFactory, clientGateway);
        QuestionControl questionControl = new QuestionControl(questionInteractor);

        CloseInputBoundary closeQuestionInteractor = new CloseQuestionInteractor(questionGateway, homePageOutputBoundary,
                gatewayFactory);
        CloseQuestionControl closeQuestionControl = new CloseQuestionControl(closeQuestionInteractor);

        BrowseInputBoundary browseQuestionInterator = new BrowseQuestionInterator(viewOutputBoundary, questionGateway,
                attorneyGateway);
        BrowseQuestionControl browseQuestionControl = new BrowseQuestionControl(browseQuestionInterator);

        ViewInputBoundary viewQuestionInteractor = new ViewQuestionInteractor(questionGateway, viewOutputBoundary, gatewayFactory);
        ViewQuestionControl viewQuestionControl = new ViewQuestionControl(viewQuestionInteractor);

        SelectInputBoundary selectQuestionInteractor = new SelectQuestionInteractor(questionGateway, theQuestionOutputBoundary, gatewayFactory);
        SelectQuestionControl selectQuestionControl = new SelectQuestionControl(selectQuestionInteractor);

        PostInputBoundary replyInteractor = new ReplyInteractor(questionGateway, postGateway,
                homePageOutputBoundary, postFactory, gatewayFactory);
        PostControl postControl = new PostControl(replyInteractor);

        RateInputBoundary rateInteractor = new RateInteractor(questionGateway, homePageOutputBoundary,
                gatewayFactory);
        RateControl rateControl = new RateControl(rateInteractor);
        System.out.println("Finished use case");

        //control container
        ControlContainer controlContainer = new ControlContainer(browseQuestionControl,
                registerControl, closeQuestionControl, postControl, questionControl,
                rateControl, selectQuestionControl, loginControl, viewQuestionControl);

        //Initiate the UI
        WelcomeUI welcomeUI = new WelcomeUI(cardlayout, screens, controlContainer);
        screens.add(welcomeUI, "Welcome");
        cardlayout.show(screens, "Welcome");
        application.setVisible(true);
        System.out.println("Finished set up welcome UI");

        Client client = new Client();
        client.setUserId(12345);
        clientGateway.addUser(client);
        System.out.println("dddd");
    }

}
