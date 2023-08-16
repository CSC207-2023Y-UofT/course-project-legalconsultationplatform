package infrastructure.screens;

import infrastructure.screens.utils.UIManager;

import javax.swing.*;
import java.awt.event.ActionListener;
import static infrastructure.screens.utils.UIDesign.*;

/**
 * This absr=tract class represents the base for all other user interface.
 */
public abstract class BaseUI extends JPanel implements ActionListener {
    infrastructure.screens.utils.UIManager uiManager;
    public BaseUI(UIManager UIManager) {
        setBackgroundFrame(this);
        setBackground(lightGreenColor);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentX(CENTER_ALIGNMENT);
        this.uiManager = UIManager;
    }
}
