package driver.screen;

import javax.swing.*;
import java.awt.*;
/**
 * This class represents a JPanel for the drop-down interface.
 */
public class DropDownPanel extends JPanel {
    public DropDownPanel(JLabel label, JComboBox<String> dropDownBox){
        this.add(label);
        this.add(dropDownBox);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        dropDownBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setForeground(UIDesign.greyColor);
        add(label);
        add(dropDownBox);
        setBackground(UIDesign.lightGreenColor);
    }
}
