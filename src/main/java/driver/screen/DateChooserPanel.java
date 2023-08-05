package driver.screen;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;

public class DateChooserPanel extends JPanel{
    public DateChooserPanel(JLabel label, JDateChooser dateChooser){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        dateChooser.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setForeground(UIDesign.inputPromptColor);
        add(label);
        add(dateChooser);
        setBackground(UIDesign.backgroundColor);
    }
}
