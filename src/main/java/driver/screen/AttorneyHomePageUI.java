package driver.screen;

import javax.swing.*;
import java.awt.*;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * This class represents the user interface for the attorney's home page after logging in.
 */
public class AttorneyHomePageUI extends JPanel{
    public AttorneyHomePageUI() {
        JLabel title = new JLabel("Attorney Home Page");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
    }
}
