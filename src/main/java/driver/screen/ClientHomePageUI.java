package driver.screen;

import javax.swing.*;
import java.awt.*;

public class ClientHomePageUI extends JPanel{

    public ClientHomePageUI() {
        JLabel title = new JLabel("Client Home Page");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
    }
}
