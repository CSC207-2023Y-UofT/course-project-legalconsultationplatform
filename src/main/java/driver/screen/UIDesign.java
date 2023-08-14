package driver.screen;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.concurrent.Flow;

public class UIDesign {
    //All colors used in the UI
    public static Color lightGreenColor = new Color(202,216,209);
    public static Color darkGreenColor = new Color(4,84,44);
    public static Color mediumGreenColor = new Color(116,156,132);
    public static Color greyColor = new Color(105,105,105);
    public static Color lightGreyColor = new Color(200, 200, 200);
    public static Color whiteColor = new Color(255, 255, 255);
    public static Color blackColor = new Color(0, 0, 0);

    //All fonts used in the UI
    public static Font regularFont = new Font("Novo Sans", Font.PLAIN, 12);
    public static Font boldFont = new Font("Novo Sans", Font.BOLD, 14);
    public static Font titleFont = new Font("Novo Sans", Font.BOLD | Font.ITALIC, 30);
    public static Font subTitleFont = new Font("Novo Sans", Font.BOLD, 20);
    public static Font italicFont = new Font("Novo Sans", Font.ITALIC, 12);

    //All shape and sizes used in the UI
    public static Dimension frameSize = new Dimension(360, 640);
    public static final int FRAME_WIDTH = 360;

    private UIDesign() {
    }


    public static void setSizeInLayout(JComponent component, Dimension dimension){
        component.setPreferredSize(dimension);
        component.setMinimumSize(dimension);
        component.setMaximumSize(dimension);
    }

    //panel settings
    public static void setBackgroundFrame(JPanel panel){
        panel.setBackground(lightGreenColor);
        panel.setSize(frameSize);
    }

    //font settings
    public static void setTitleFont(JComponent component){
        component.setForeground(darkGreenColor);
        component.setFont(titleFont);
        component.setOpaque(false);
    }

    public static void setPromptFont(JComponent component){
        component.setForeground(greyColor);
        component.setFont(italicFont);
        component.setOpaque(false);
    }

    public static void setTextFont(JComponent component){
        component.setFont(regularFont);
        component.setForeground(blackColor);
        component.setOpaque(false);
    }

    public static void setNameFont(JComponent component){
        component.setFont(boldFont);
        component.setForeground(blackColor);
        component.setOpaque(false);
    }
}
