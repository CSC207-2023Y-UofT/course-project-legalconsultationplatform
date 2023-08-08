package driver.screen;

import javax.swing.*;
import java.awt.*;

/**
 * This is a custom JPanel class representing a simple panel with a label and a text field.
 */
public class LabelTextPanel extends JPanel {
    public LabelTextPanel(JLabel label, JTextField textField){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        textField.setAlignmentX(Component.LEFT_ALIGNMENT);
        textField.setColumns(25);
        label.setForeground(UIDesign.greyColor);
        add(label);
        add(textField);
        setBackground(UIDesign.lightGreenColor);
    }
}

