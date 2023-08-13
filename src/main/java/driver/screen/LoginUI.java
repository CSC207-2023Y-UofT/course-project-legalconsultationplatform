package driver.screen;

import adapter.controller.ControlContainer;
import adapter.controller.UserLoginControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static driver.screen.UIDesign.*;
import static driver.screen.UIDrawer.*;


/**
 *
 * @author kaxi
 */
public class LoginUI extends BaseUI implements ActionListener {
    UIManager UIManager;
    JTextField userId = new JTextField(15);
    JPasswordField password = new JPasswordField(15);
    static final String LOGIN_BUTTON_NAME = "Login";
    static final String BACK_BUTTON_NAME = "Back";

    /**
     * Creates new form UserLogin
     */
    public LoginUI(UIManager UIManager) {
        super(UIManager);


        //Spacers
        JPanel spacer1 = addSpacer(200);
        JPanel spacer2 = addSpacer(30);
        JPanel spacer3 = addSpacer(20);


        // Create the title label
        JLabel title = new JLabel("Log In");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        setTitleFont(title);

        // Add userId and password input fields
        JPanel inputPanel = new JPanel();
        setSizeInLayout(inputPanel, new Dimension(360, 150));
        JPanel userIdPanel = labelTextPanelDrawer(new JLabel("User Id"), userId);
        JPanel passwordPanel = labelTextPanelDrawer(new JLabel("Password"), password);
        inputPanel.add(userIdPanel);
        inputPanel.add(spacer2);
        inputPanel.add(passwordPanel);

        // Add
        List<String> buttonList = new ArrayList<>();
        buttonList.add(LOGIN_BUTTON_NAME);
        buttonList.add(BACK_BUTTON_NAME);
        JPanel buttons = setButtonPanel(buttonList, new Dimension(150, 50), 20, this);

        add(spacer1);
        add(title);
        add(inputPanel);
        add(spacer3);
        add(buttons);


    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        String actionCommand = evt.getActionCommand();
        ControlContainer controlContainer = UIManager.getControlContainer();
        JPanel screens = UIManager.getScreens();
        CardLayout cardLayout = UIManager.getCardLayout();
        UserLoginControl loginControl = controlContainer.getUserLoginControl();
        switch (actionCommand) {
            case LOGIN_BUTTON_NAME:
                try {
                    loginControl.login(Integer.parseInt(userId.getText()), String.valueOf(password.getPassword()));
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }
                break;

            case BACK_BUTTON_NAME:
                WelcomeUI welcomeUI = new WelcomeUI(UIManager);
                screens.add(welcomeUI, "Welcome");
                cardLayout.show(screens, "Welcome");
                break;
        }
    }
}