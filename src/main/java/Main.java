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
import businessrule.requestmodel.CloseRequestModel;
import businessrule.responsemodel.HomePageResponseModel;
import businessrule.usecase.*;
import driver.database.AttorneyRepository;
import driver.database.ClientRepository;
import driver.database.QuestionGateway;
import driver.database.QuestionRepo;
import driver.screen.AskQuestionUI;
import driver.screen.LoginUI;
import entity.ClientFactory;
import entity.QuestionFactory;
import entity.Client;
import entity.User;

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

        //set up jframe
        JFrame application = new JFrame("Legal Consultation Platform");
        CardLayout cardlayout = new CardLayout();
        JPanel screens = new JPanel(cardlayout);
        application.add(screens);

        //define outputBoundary
        HomePageOutputBoundary homePageOutputBoundary = new HomePageResponseFormatter(cardlayout, screens);
        RegisterOutputBoundary registerOutputBoundary = new RegisterResponseFormatter(cardlayout, screens);
        TheQuestionOutputBoundary theQuestionOutputBoundary = new TheQuestionResponseFormatter(cardlayout, screens);
        ViewOutputBoundary viewOutputBoundary = new ViewResponseFormatter(cardlayout, screens);

        //define useCase
        UserLoginInputBoundary userLoginInteractor = new UserLoginInteractor(gatewayFactory, homePageOutputBoundary);
        UserLoginControl loginControl = new UserLoginControl(userLoginInteractor);

        ClientRegisterInputBoundary clientRegisterInteractor = new ClientRegisterInteractor(clientGateway, registerOutputBoundary,
                clientFactory);
        ClientRegisterControl registerControl = new ClientRegisterControl(clientRegisterInteractor);

        QuestionInputBoundary questionInputBoundary = new AskQuestionInteractor(questionGateway, theQuestionOutputBoundary,
                questionFactory, clientGateway);
        QuestionControl questionControl = new QuestionControl(questionInputBoundary);

        CloseInputBoundary closeInputBoundary = new CloseQuestionInteractor(questionGateway, homePageOutputBoundary,
                gatewayFactory);
        CloseQuestionControl closeQuestionControl = new CloseQuestionControl(closeInputBoundary);

        BrowseInputBoundary browseinputBoundary = new BrowseQuestionInterator(viewOutputBoundary, questionGateway,
                attorneyGateway);
        BrowseQuestionControl browseQuestionControl = new BrowseQuestionControl(browseinputBoundary);

        ViewInputBoundary viewInputBoundary = new ViewQuestionInteractor(questionGateway, viewOutputBoundary, gatewayFactory);
        ViewQuestionControl viewQuestionControl = new ViewQuestionControl(viewInputBoundary);




        //Initiate the UI
        WelcomeUI welcomeUI = new WelcomeUI()
        screens.add(askQuestionUI, "login");
        cardlayout.show(screens, "login");
        application.pack();
        application.setVisible(true);
    }
}
