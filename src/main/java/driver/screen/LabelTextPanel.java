package driver.screen;

import javax.swing.*;

/**
 * This is a custom JPanel class representing a simple panel with a label and a text field.
 */
public class LabelTextPanel extends JPanel {
    public LabelTextPanel(JLabel label, JTextField textField){
        this.add(label);
        this.add(textField);
    }
}

