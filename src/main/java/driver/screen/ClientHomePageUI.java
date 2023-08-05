package driver.screen;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents the user interface for the client's home page after logging in.
 */
public class ClientHomePageUI extends JPanel{

    public ClientHomePageUI() {
        JLabel title = new JLabel("Client Home Page");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
    }
}
