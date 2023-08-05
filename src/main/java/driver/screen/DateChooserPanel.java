package driver.screen;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;

public class DateChooserPanel extends JPanel{
    public DateChooserPanel(JLabel label, JDateChooser dateChooser){
        this.add(label);
        this.add(dateChooser);
    }
}
