package driver.screen;

import adapter.controller.ControlContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import static driver.screen.UIDesign.*;
import static driver.screen.UIDrawer.*;

public class WelcomeUI extends BaseUI implements ActionListener {
    UIManager UIManager;
    static final String TITLE_TEXT = "WELCOME";
    static final String REGISTER_BUTTON_NAME = "Register";
    static final String LOGIN_BUTTON_NAME = "Login";

    public WelcomeUI(UIManager UIManager) {
        super(UIManager);

        //Spacers
        JPanel topSpacer = addSpacer(160);
        JPanel middleSpacer = addSpacer(80);

        //Welcome title
        JLabel title = new JLabel(TITLE_TEXT);
        setTitleFont(title);

        //Buttons panel

        List<String> buttonList = new ArrayList<>();
        buttonList.add(REGISTER_BUTTON_NAME);
        buttonList.add(LOGIN_BUTTON_NAME);
        JPanel buttons = setButtonPanel(buttonList, new Dimension(150, 50), 40, this);

        add(topSpacer);
        add(title);
        add(middleSpacer);
        add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        System.out.println("Control container is"+ UIManager.getControlContainer());
        ControlContainer controlContainer = UIManager.getControlContainer();
        JPanel screens = UIManager.getScreens();
        CardLayout cardLayout = UIManager.getCardLayout();
        switch (actionCommand) {
            case REGISTER_BUTTON_NAME:
                RegisterUI registerUI = new RegisterUI(UIManager);
                screens.add(registerUI, "Register");
                cardLayout.show(screens, "Register");
                System.out.println("User chooses register\nRegister screen showed");
                break;

            case LOGIN_BUTTON_NAME:
                LoginUI loginUI = new LoginUI(UIManager);
                screens.add(loginUI, "Login");
                cardLayout.show(screens, "Login");
                System.out.println("User chooses login\nLogin screen showed");
                break;
        }
    }
}
