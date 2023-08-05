package driver.screen;

import adapter.controller.ControlContainer;
import adapter.controller.UserLoginControl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.server.UID;


/**
 *
 * @author kaxi
 */
public class LoginUI extends JPanel implements ActionListener{
    ControlContainer controlContainer;
    JTextField userId = new JTextField(15);
    JPasswordField password = new JPasswordField(15);
    /**
     * Creates new form UserLogin
     */
    public LoginUI(ControlContainer controlContainer) {

        this.controlContainer = controlContainer;
        setBackground(UIDesign.backgroundColor);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Create the title label
        JLabel title = new JLabel("Log In");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        int topMargin = 30;
        int leftMargin = 0;
        int bottomMargin = 0;
        int rightMargin = 0;
        title.setBorder(new EmptyBorder(topMargin, leftMargin, bottomMargin, rightMargin));
        add(title);

        // Add some vertical glue between the title, userId, and password

        gbc.gridx = 0; // Set the gridx to 0 to place components under the title

        LabelTextPanel userIdPanel = new LabelTextPanel(new JLabel("User name"), userId);
        add(userIdPanel, gbc);

        LabelTextPanel passwordPanel = new LabelTextPanel(new JLabel("Password"), password);
        add(passwordPanel, gbc);

        // Add the login button
        JButton login = new JButton("Login");
        JPanel buttons = new JPanel();
        buttons.setBackground(UIDesign.backgroundColor);
        login.setMinimumSize(UIDesign.buttonSize);
        login.setMaximumSize(UIDesign.buttonSize);
        buttons.add(login);
        login.addActionListener(this);
        add(buttons);

    }
    @Override
    public void actionPerformed(ActionEvent evt){
        System.out.println("Click" + evt.getActionCommand());
        UserLoginControl loginControl = controlContainer.getUserLoginControl();

        try {
            loginControl.login(Integer.parseInt(userId.getText()), String.valueOf(password.getPassword()));
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}