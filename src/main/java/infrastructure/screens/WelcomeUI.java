package infrastructure.screens;

import infrastructure.screens.utils.UIManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import static infrastructure.screens.utils.UIDesign.*;
import static infrastructure.screens.utils.UIDrawer.*;

/**
 * This class represents the initial screen of the application where users can choose to register or log in.
 */
public class WelcomeUI extends BaseUI implements ActionListener {
    static final String TITLE_TEXT = "WELCOME";
    static final String CLIENT_REGISTER_BUTTON_NAME = "Client Register";
    static final String ATTORNEY_REGISTER_BUTTON_NAME = "Attorney Register";
    static final String LOGIN_BUTTON_NAME = "Login";

    /**
     * Constructs a WelcomeUI instance with the provided control container,
     * card layout, and screens panel.
     *
     * @param UIManager UIManager that contains screens, cardlayouts, and control container
     */
    public WelcomeUI(UIManager UIManager) {
        super(UIManager);

        //Spacers
        JPanel topSpacer = addSpacer(160);
        JPanel middleSpacer = addSpacer(80);

        //Welcome title
        JLabel title = new JLabel(TITLE_TEXT);
        setTitleFont(title);
        title.setAlignmentX(CENTER_ALIGNMENT);

        //Buttons panel

        List<String> buttonList = new ArrayList<>();
        buttonList.add(ATTORNEY_REGISTER_BUTTON_NAME);
        buttonList.add(CLIENT_REGISTER_BUTTON_NAME);
        buttonList.add(LOGIN_BUTTON_NAME);
        JPanel buttons = setButtonPanel(buttonList, new Dimension(200, 50), 40, this);

        add(topSpacer);
        add(title);
        add(middleSpacer);
        add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        JPanel screens = uiManager.getScreens();
        CardLayout cardLayout = uiManager.getCardLayout();
        switch (actionCommand) {
            case ATTORNEY_REGISTER_BUTTON_NAME:
                AttorneyRegisterUI attorneyRegisterUI = new AttorneyRegisterUI(uiManager);
                screens.add(attorneyRegisterUI, "Attorney Register");
                cardLayout.show(screens, "Attorney Register");
                System.out.println("User chooses register as an attorney\nRegister screen showed");
                break;

            case CLIENT_REGISTER_BUTTON_NAME:
                ClientRegisterUI clientRegisterUI = new ClientRegisterUI(uiManager);
                screens.add(clientRegisterUI, "Client Register");
                cardLayout.show(screens, "Client Register");
                System.out.println("User chooses register as a client\nRegister screen showed");
                break;

            case LOGIN_BUTTON_NAME:
                LoginUI loginUI = new LoginUI(uiManager);
                screens.add(loginUI, "Login");
                cardLayout.show(screens, "Login");
                System.out.println("User chooses login\nLogin screen showed");
                break;
        }
    }
}
