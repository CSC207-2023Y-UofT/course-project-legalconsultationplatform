package driver.screen;

import adapter.controller.ControlContainer;
import adapter.controller.UserLoginControl;

import javax.swing.*;
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

        JLabel title = new JLabel("Login to your account");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel userIdInfo = new LabelTextPanel(new JLabel("UserId"), userId);
        LabelTextPanel passwordInfo = new LabelTextPanel(new JLabel("Password"), password);
        JButton buttonToSubmit = new JButton("Login");

        JPanel buttons = new JPanel();
        buttons.add(buttonToSubmit);

        buttonToSubmit.addActionListener(this);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(userIdInfo);
        this.add(passwordInfo);
        this.add(buttons);

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