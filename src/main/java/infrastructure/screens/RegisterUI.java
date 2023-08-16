package infrastructure.screens;

import adapters.controllers.RegisterControl;
import adapters.controllers.ControlContainer;
import infrastructure.screens.utils.UIDesign;
import infrastructure.screens.utils.UIManager;
import usecases.requests.ClientRegistrationData;
import usecases.requests.RegistrationData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import static infrastructure.screens.utils.UIDrawer.*;
import static infrastructure.screens.utils.UIDesign.*;

/**
 * This class represents a JPanel for the user registration interface.
 */
public abstract class RegisterUI extends BaseUI{
    JTextField userName = new JTextField(15);
    JPasswordField password1 = new JPasswordField(15);
    JPasswordField password2 = new JPasswordField(15);
    JTextField email = new JTextField(15);
    JTextField postalCode = new JTextField(15);
    JTextField stateAbb = new JTextField(15);
    static JLabel title;

    static JPanel inputSpacer = addSpacer(5);
    JPanel registerButtons;
    static JPanel userNamePanel;
    static JPanel passwordPanel;
    static JPanel repeatPasswordPanel;
    static JPanel emailPanel;
    static JPanel postalCodePanel;
    static JPanel stateAbbPanel;

    static final String USER_NAME_PROMPT = "User Name";
    static final String PASSWORD_PROMPT = "Password";
    static final String REPEAT_PASSWORD_PROMPT = "Repeat password";
    static final String EMAIL_PROMPT = "Email Address";
    static final String POSTAL_CODE_PROMPT = "Zip Code";
    static final String REGISTER_BUTTON_NAME = "Register";
    static final String BACK_BUTTON_NAME = "Back";
    static final String STATE_ABB_PROMPT = "State Abbreviation";

    /**
     * Constructs a new RegisterUI instance.
     *
     * @param UIManager UIManager class contains screens, cadlayouts, and control container
     */
    public RegisterUI(UIManager UIManager) {
        super(UIManager);

        // Create the title label with 30 pixels space on top
        title = new JLabel("Create your profile");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        setTitleFont(title);

        //Input panel components
        userNamePanel = labelTextPanelDrawer(new JLabel(USER_NAME_PROMPT), userName);
        passwordPanel = labelTextPanelDrawer(new JLabel(PASSWORD_PROMPT), password1);
        repeatPasswordPanel = labelTextPanelDrawer(new JLabel(REPEAT_PASSWORD_PROMPT), password2);
        emailPanel = labelTextPanelDrawer(new JLabel(EMAIL_PROMPT), email);
        postalCodePanel = labelTextPanelDrawer(new JLabel(POSTAL_CODE_PROMPT), postalCode);
        stateAbbPanel = labelTextPanelDrawer(new JLabel(STATE_ABB_PROMPT), stateAbb);

        //Register button here
        List<String> buttonList = new ArrayList<>();
        buttonList.add(REGISTER_BUTTON_NAME);
        buttonList.add(BACK_BUTTON_NAME);
        registerButtons = setButtonPanel(buttonList, new Dimension(150, 50), 20, this);
    }
    protected JPanel registrationPanelDrawer(){
        JPanel result = new JPanel();
        result.setLayout(new BoxLayout(result, BoxLayout.Y_AXIS));
        result.setOpaque(false);
        result.add(userNamePanel);
        result.add(inputSpacer);
        result.add(passwordPanel);
        result.add(inputSpacer);
        result.add(repeatPasswordPanel);
        result.add(inputSpacer);
        result.add(emailPanel);
        result.add(inputSpacer);
        result.add(stateAbbPanel);
        result.add(inputSpacer);
        result.add(postalCodePanel);
        result.add(inputSpacer);

        return result;
    }

    protected static JScrollPane registerScrollDrawer(JLabel title, JPanel inputPanel, JPanel buttons, int spacerHeight, int totalHeight){
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.add(Box.createVerticalStrut(20));
        setSizeInLayout(contentPanel, new Dimension(340, totalHeight));

        JPanel spacer = addSpacer(spacerHeight);
        contentPanel.add(spacer);
        contentPanel.add(title);
        contentPanel.add(spacer);
        contentPanel.add(inputPanel);
        contentPanel.add(buttons);
        contentPanel.setBackground(lightGreenColor);

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setOpaque(false);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

        return scrollPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        ControlContainer controlContainer = uiManager.getControlContainer();
        CardLayout cardLayout = uiManager.getCardLayout();
        JPanel screens = uiManager.getScreens();
        switch (actionCommand) {
            case REGISTER_BUTTON_NAME:
                handleRegisterAction(controlContainer, screens, cardLayout);
                break;

            case BACK_BUTTON_NAME:
                cardLayout.show(screens, "Welcome");
                break;
        }
    }
    protected abstract void handleRegisterAction(ControlContainer controlContainer, JPanel screens,
                                        CardLayout cardLayout);
}
