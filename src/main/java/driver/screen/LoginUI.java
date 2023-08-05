package driver.screen;

import adapter.controller.ControlContainer;
import adapter.controller.UserLoginControl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




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

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

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
        add(Box.createVerticalGlue());

        // Add userId panel
        LabelTextPanel userIdInfo = new LabelTextPanel(new JLabel("UserId"), userId);
        add(userIdInfo);

        // Add some vertical glue between userId and password
        add(Box.createVerticalGlue());

        // Add password panel
        LabelTextPanel passwordInfo = new LabelTextPanel(new JLabel("Password"), password);
        add(passwordInfo);

        // Add some vertical glue between password and the login button
        add(Box.createVerticalGlue());

        // Add the login button
        JButton login = new JButton("Login");
        JPanel buttons = new JPanel();
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