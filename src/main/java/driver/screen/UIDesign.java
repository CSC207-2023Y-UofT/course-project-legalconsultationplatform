package driver.screen;

import javax.swing.*;
import java.awt.*;

public class UIDesign {
    public static Color backgroundColor = new Color(202,216,209);
    public static Color titleColor = new Color(4,84,44);
    public static Color inputPromptColor = new Color(105,105,105);
    public static Color textFieldColor = new Color(255, 255, 255);
    public static Color buttonTextColor = new Color(0, 0, 0);
    public static Font regularFont = new Font("Novo Sans", Font.PLAIN, 12);
    public static Font boldFont = new Font("Novo Sans", Font.BOLD, 14);
    public static Font titleFont = new Font("Novo Sans", Font.BOLD | Font.ITALIC, 30);
    public static Font subTitleFont = new Font("Novo Sans", Font.BOLD | Font.ITALIC, 20);
    public static Font italicFont = new Font("Novo Sans", Font.ITALIC, 12);

    public static Dimension buttonSize = new Dimension(100, 50);

    private UIDesign() {
    }

    public static void setButton(JButton button){
        button.setMaximumSize(buttonSize);
        button.setMinimumSize(buttonSize);
        button.setPreferredSize(buttonSize);
        button.setFont(boldFont);
        button.setForeground(titleColor);
        button.setBackground(Color.RED);
    }

    public static void setTitle(JLabel title){
        title.setForeground(titleColor);
        title.setFont(titleFont);
    }

    public static void setHomePageButton(JButton button){
        Dimension dimension = new Dimension(300, 50);
        button.setMaximumSize(dimension);
        button.setMinimumSize(dimension);
        button.setPreferredSize(dimension);
        button.setFont(boldFont);
        button.setForeground(titleColor);
        button.setBackground(Color.RED);
    }
}
