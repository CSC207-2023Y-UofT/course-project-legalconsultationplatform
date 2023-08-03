package driver.screen;

import javax.swing.*;

public class DropDownPanel extends JPanel {
    public DropDownPanel(JLabel label, JComboBox<String> dropDownBox){
        this.add(label);
        this.add(dropDownBox);
    }
}
