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
        UIDesign.setBackgroundFrame(this);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5); // Padding around each component

        // Create the title label
        JLabel title = new JLabel("Log In");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        UIDesign.setTitleFont(title);
        int topMargin = 30;
        int leftMargin = 0;
        int bottomMargin = 0;
        int rightMargin = 0;
        title.setBorder(new EmptyBorder(topMargin, leftMargin, bottomMargin, rightMargin));
        add(title, gbc);

        // Add some vertical glue between the title, userId, and password

        gbc.gridx = 0; // Set the gridx to 0 to place components under the title

        LabelTextPanel userIdPanel = new LabelTextPanel(new JLabel("User Id"), userId);
        add(userIdPanel, gbc);

        LabelTextPanel passwordPanel = new LabelTextPanel(new JLabel("Password"), password);
        add(passwordPanel, gbc);

        JButton buttonToSubmit = new JButton("Login");
        UIDesign.setButton(buttonToSubmit);
        JPanel buttons = new JPanel();
        buttons.setBackground(UIDesign.lightGreenColor);
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS)); // Use X_AXIS for horizontal alignment
        buttons.add(buttonToSubmit);
        buttonToSubmit.addActionListener(this);
        add(buttons, gbc);

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