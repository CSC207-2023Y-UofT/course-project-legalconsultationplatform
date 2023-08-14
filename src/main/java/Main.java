import adapter.controller.*;
import adapter.presenter.*;
import businessrule.UIFactory;
import businessrule.gateway.*;
import businessrule.inputboundary.*;
import businessrule.outputboundary.*;
import businessrule.responsemodel.BaseResponseModel;
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

    //set up Jframe
    JFrame application = new JFrame("Legal Consultation Platform");
    application.setSize(UIDesign.frameSize);
    application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    CardLayout cardlayout = new CardLayout();
    JPanel screens = new JPanel(cardlayout);
    application.add(screens);
    System.out.println("System - finished set up frame");

    UIManager UIManager = new UIManager(screens, cardlayout);

    //define outputBoundary
    UserOutputBoundary homePageOutputBoundary = new HomePageResponseFormatter(UIManager);
    UserOutputBoundary loginHomePageOutputBoundary = new HomePageResponseFormatter(UIManager);
    BaseOutputBoundary registerOutputBoundary = new RegisterResponseFormatter(UIManager);
    TheQuestionOutputBoundary theQuestionOutputBoundary = new TheQuestionResponseFormatter(UIManager);
    ViewOutputBoundary viewOutputBoundary = new ViewResponseFormatter(UIManager);
    System.out.println("System = finished set up output boundary");

    //define useCase
    UserLoginInputBoundary userLoginInteractor = new UserLoginInteractor(gatewayFactory, loginHomePageOutputBoundary);
    UserLoginControl loginControl = new UserLoginControl(userLoginInteractor);

    UserRegisterInputBoundary clientRegisterInteractor = new ClientRegisterInteractor(clientGateway, clientFactory, registerOutputBoundary);
    ClientRegisterControl registerControl = new ClientRegisterControl(clientRegisterInteractor);

    QuestionInputBoundary questionInteractor = new AskQuestionInteractor(questionGateway, theQuestionOutputBoundary,
            questionFactory, clientGateway);
    QuestionControl questionControl = new QuestionControl(questionInteractor);

    CloseInputBoundary closeQuestionInteractor = new CloseQuestionInteractor(questionGateway, homePageOutputBoundary,
            clientGateway);
    CloseQuestionControl closeQuestionControl = new CloseQuestionControl(closeQuestionInteractor);

    ViewInputBoundary browseQuestionInterator = new BrowseQuestionInteractor(viewOutputBoundary, questionGateway,
            attorneyGateway);
    ViewQuestionControl browseQuestionControl = new ViewQuestionControl(browseQuestionInterator);

    ViewInputBoundary viewQuestionInteractor = new ViewQuestionInteractor(viewOutputBoundary, questionGateway, gatewayFactory);
    ViewQuestionControl viewQuestionControl = new ViewQuestionControl(viewQuestionInteractor);

    ViewInputBoundary recommendationInteractor = new AttorneyRecommendInteractor(viewOutputBoundary, questionGateway,
            attorneyGateway);
    ViewQuestionControl recommendationControl = new ViewQuestionControl(recommendationInteractor);

    SelectInputBoundary selectQuestionInteractor = new SelectQuestionInteractor(questionGateway, theQuestionOutputBoundary, gatewayFactory);
    SelectQuestionControl selectQuestionControl = new SelectQuestionControl(selectQuestionInteractor);

    PostInputBoundary replyInteractor = new ReplyInteractor(questionGateway, postGateway, theQuestionOutputBoundary,
            postFactory, gatewayFactory);
    PostControl postControl = new PostControl(replyInteractor);

    RateInputBoundary rateInteractor = new RateInteractor(questionGateway, homePageOutputBoundary, clientGateway,
            attorneyGateway);
    RateControl rateControl = new RateControl(rateInteractor);
    System.out.println("System - finished set up use case");

    //control container
    ControlContainer controlContainer = new ControlContainer(registerControl, closeQuestionControl,
            postControl, questionControl, rateControl, selectQuestionControl, loginControl, viewQuestionControl,
    browseQuestionControl, recommendationControl);

    //feed control container into the response formatter
    UIManager.setControlContainer(controlContainer);
    System.out.println("System - finished set up control container");
    ControlContainer controlContainer1 = UIManager.getControlContainer();
        System.out.println("Control container: " + controlContainer1);

    //Initiate the UI
    JPanel welcomeUI = UIFactory.getUI(UIFactory.UIType.WELCOME_UI, UIManager, new BaseResponseModel());
    screens.add(welcomeUI, "Welcome");
    cardlayout.show(screens, "Welcome");
    application.setVisible(true);
    System.out.println("System - finished set up welcome UI");
    }
}