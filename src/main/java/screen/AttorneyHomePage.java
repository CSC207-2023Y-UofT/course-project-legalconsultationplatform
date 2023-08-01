package screen;

import presenter.UserLoginControl;
import userlogin.LoginResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

public class AttorneyHomePage extends JPanel{
    public AttorneyHomePage() {
        JLabel title = new JLabel("Attorney Home Page");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
    }
}
