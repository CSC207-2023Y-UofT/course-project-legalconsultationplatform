package driver.screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.rmi.server.UID;

public abstract class BaseUI extends JPanel implements ActionListener {
    public BaseUI() {
        UIDesign.setBackgroundFrame(this);
    }
}
