import adapter.controller.*;
import adapter.presenter.*;
import businessrule.gateway.*;
import businessrule.inputboundary.*;
import businessrule.outputboundary.*;
import businessrule.usecase.*;
import driver.database.*;
import driver.screen.UIDesign;
import driver.screen.UIManager;
import driver.screen.WelcomeUI;
import entity.Attorney;
import entity.factory.ClientFactory;
import entity.factory.PostFactory;
import entity.factory.QuestionFactory;

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
    System.out.println("System - finished set up repo");

    //Test attorney
        AttorneyRepository attorneyRepo = new AttorneyRepository();
        Attorney attorney = new Attorney();
        attorney.setName("Kaxi");
        attorney.setPassword("12345678");
        attorney.setUserId(12345678);
        attorneyRepo.save(attorney);

    //set up jframe
    JFrame application = new JFrame("Legal Consultation Platform");
    application.setSize(UIDesign.frameSize);
    application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    CardLayout cardlayout = new CardLayout();
    JPanel screens = new JPanel(cardlayout);
    application.add(screens);
    System.out.println("System - finished set up frame");

    //define outputBoundary
    HomePageOutputBoundary homePageOutputBoundary = new HomePageResponseFormatter(cardlayout, screens);
    RegisterOutputBoundary registerOutputBoundary = new RegisterResponseFormatter(cardlayout, screens);
    TheQuestionOutputBoundary theQuestionOutputBoundary = new TheQuestionResponseFormatter(cardlayout, screens);
    ViewOutputBoundary viewOutputBoundary = new ViewResponseFormatter(cardlayout, screens);
    System.out.println("System = finished set up output boundary");

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

    ViewInputBoundary browseQuestionInterator = new BrowseQuestionInteractor(viewOutputBoundary, questionGateway,
            attorneyGateway);
    ViewQuestionControl browseQuestionControl = new ViewQuestionControl(browseQuestionInterator);

    ViewInputBoundary viewQuestionInteractor = new ViewQuestionInteractor(questionGateway, viewOutputBoundary, gatewayFactory);
    ViewQuestionControl viewQuestionControl = new ViewQuestionControl(viewQuestionInteractor);

    ViewInputBoundary viewRateableQuestionInteractor = new ViewRateableQuestionInteractor(viewOutputBoundary,
            questionGateway, clientGateway);
    ViewQuestionControl viewRateableQuestionControl = new ViewQuestionControl(viewRateableQuestionInteractor);

    ViewInputBoundary recommendationInteractor = new AttorneyRecommendInteractor(viewOutputBoundary, questionGateway,
            attorneyGateway);
    ViewQuestionControl recommendationControl = new ViewQuestionControl(recommendationInteractor);

    SelectInputBoundary selectQuestionInteractor = new SelectQuestionInteractor(questionGateway, theQuestionOutputBoundary, gatewayFactory);
    SelectQuestionControl selectQuestionControl = new SelectQuestionControl(selectQuestionInteractor);

    PostInputBoundary replyInteractor = new ReplyInteractor(questionGateway, postGateway,
            homePageOutputBoundary, postFactory, gatewayFactory);
    PostControl postControl = new PostControl(replyInteractor);

    RateInputBoundary rateInteractor = new RateInteractor(questionGateway, homePageOutputBoundary,
            clientGateway);
    RateControl rateControl = new RateControl(rateInteractor);
    System.out.println("System - finished set up use case");

    //control container
    ControlContainer controlContainer = new ControlContainer(registerControl, closeQuestionControl,
            postControl, questionControl, rateControl, selectQuestionControl, loginControl, viewQuestionControl,
    browseQuestionControl, viewRateableQuestionControl, recommendationControl);

    //feed control container into the response formatter
    homePageOutputBoundary.setControlContainer(controlContainer);
    registerOutputBoundary.setControlContainer(controlContainer);
    theQuestionOutputBoundary.setControlContainer(controlContainer);
    viewOutputBoundary.setControlContainer(controlContainer);
    System.out.println("System - finished set up control container");

    //Create the UITool for all UI pages
        UIManager UIManager = new UIManager(controlContainer, screens, cardlayout);
    //Initiate the UI
    WelcomeUI welcomeUI = new WelcomeUI(controlContainer, cardlayout, screens);
    screens.add(welcomeUI, "Welcome");
    cardlayout.show(screens, "Welcome");
    application.setVisible(true);
    System.out.println("System - finished set up welcome UI");
    }
}