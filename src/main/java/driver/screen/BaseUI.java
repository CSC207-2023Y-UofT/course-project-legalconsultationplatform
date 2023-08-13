package driver.screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import static driver.screen.UIDesign.*;

public abstract class BaseUI extends JPanel implements ActionListener {
    UIManager UIManager;
    public BaseUI(UIManager UIManager) {
        setBackgroundFrame(this);
        setBackground(lightGreenColor);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.UIManager = UIManager;
    }
}
