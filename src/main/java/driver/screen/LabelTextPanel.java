package driver.screen;

import javax.swing.*;
import java.awt.*;

public class LabelTextPanel extends JPanel {
    public LabelTextPanel(JLabel label, JTextField textField){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        textField.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setForeground(UIDesign.inputPromptColor);
        add(label);
        add(textField);
        setBackground(UIDesign.backgroundColor);
    }
}

