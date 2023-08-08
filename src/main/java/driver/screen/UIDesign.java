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
    public static Color lightGreyColor = new Color(211, 211, 211);
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
    public static int frameWidth = 360;
    public int frameHeight = 640;

    private UIDesign() {
    }

    //button settings
    public static void setButton(JButton button){
        button.setFont(boldFont);
        button.setForeground(whiteColor);
        button.setOpaque(true);
        button.setBackground(darkGreenColor);
        button.setBorderPainted(false);
    }

    public static void setHomePageButton(JButton button){
        Dimension dimension = new Dimension(300, 50);
        button.setMaximumSize(dimension);
        button.setMinimumSize(dimension);
        button.setPreferredSize(dimension);
        setButton(button);
    }

    public static void setGeneralButton(JButton button){
        Dimension dimension = new Dimension(150, 50);
        button.setMaximumSize(dimension);
        button.setMinimumSize(dimension);
        button.setPreferredSize(dimension);
        setButton(button);
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
    }

    public static void setPromptFont(JComponent component){
        component.setForeground(greyColor);
        component.setFont(italicFont);
    }

    public static void setTextFont(JComponent component){
        component.setFont(regularFont);
        component.setForeground(blackColor);
    }

    public static void setNameFont(JComponent component){
        component.setFont(boldFont);
        component.setForeground(blackColor);
    }

    public static JPanel addSpacer(int height){
        JPanel topSpacer = new JPanel();
        Dimension topSpacerDimension = new Dimension(frameWidth, height);
        setSizeInLayout(topSpacer, topSpacerDimension);
        topSpacer.setOpaque(false);
        return topSpacer;
    }

    //Set up subPanels
    public static JPanel singlePostDrawer(String userName, String postDate,
                                        String postText, String userType){
        JPanel nameAndDate = new JPanel();
        nameAndDate.setBackground(lightGreyColor);
        nameAndDate.setLayout(new FlowLayout());
        JLabel name = new JLabel(userName);
        JLabel date = new JLabel(postDate);
        setNameFont(name);
        setPromptFont(date);
        nameAndDate.add(name);
        nameAndDate.add(date);

        JTextArea text = new JTextArea();
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setEditable(false);
        text.setText(postText);
        JScrollPane textScrollPane = new JScrollPane(text);
        textScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        textScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        setSizeInLayout(textScrollPane, new Dimension(250, 50));
        text.setOpaque(false);
        textScrollPane.setOpaque(false);

        JPanel nakedSinglePost = new JPanel();
        nakedSinglePost.setLayout(new BoxLayout(nakedSinglePost, BoxLayout.Y_AXIS));
        nakedSinglePost.add(nameAndDate);
        nakedSinglePost.add(textScrollPane);
        nakedSinglePost.setOpaque(false);
        nakedSinglePost.setAlignmentX(Component.LEFT_ALIGNMENT);

        Color squareColor = userType.equals("Client") ? mediumGreenColor : darkGreenColor;

        JPanel greenSquare = new JPanel();
        greenSquare.setPreferredSize(new Dimension(20, 20));
        greenSquare.setBackground(squareColor);

        JPanel overallSinglePost = new JPanel();
        overallSinglePost.setLayout(new FlowLayout());
        overallSinglePost.add(greenSquare);
        overallSinglePost.add(nakedSinglePost);
        overallSinglePost.setBackground(lightGreyColor);

        LineBorder lineBorder = new LineBorder(whiteColor, 5);
        overallSinglePost.setBorder(lineBorder);
        return overallSinglePost;
    }

    public static JPanel singleQuestionDrawer(String title, String questionType, String deadline){
        JPanel questionTitle = questionTitleDrawer(questionType, title);
        JPanel singleQuestion = new JPanel();
        setSizeInLayout(singleQuestion, new Dimension(400, 80));
        singleQuestion.setLayout(new BoxLayout(singleQuestion, BoxLayout.Y_AXIS));
        singleQuestion.add(questionTitle);
        JLabel deadlineText = new JLabel(deadline);
        deadlineText.setHorizontalAlignment(SwingConstants.LEFT);
        deadlineText.setOpaque(false);
        setPromptFont(deadlineText);
        singleQuestion.add(deadlineText);
        singleQuestion.setAlignmentX(Component.LEFT_ALIGNMENT);
        return singleQuestion;
    }

    public static JPanel questionTitleDrawer(String questionType, String title){
        JPanel typePanel = new JPanel();
        Color color;
        String abb;
        switch (questionType) {
            case "Family and Children":
                color = Color.RED;
                abb = "F&C";
                break;
            case "Individual Rights":
                color = Color.BLACK;
                abb = "IR";
                break;
            case "Consumer Financial Questions":
                color = Color.blue;
                abb = "CF";
                break;
            case "Housing and Homelessness":
                color = Color.CYAN;
                abb = "H&H";
                break;
            case "Income Maintenance":
                color = Color.pink;
                abb = "IM";
                break;
            case "Health and Disability":
                color = Color.magenta;
                abb = "H&D";
                break;
            case "Work, Employment and Unemployment":
                color = Color.yellow;
                abb = "W,E&U";
                break;
            case "Juvenile":
                color = Color.orange;
                abb = "J";
                break;
            case "Education":
                color = Color.gray;
                abb = "E";
                break;
            default:
                color = Color.darkGray;
                abb = "Other";
                break;
        }
        JLabel typeText = new JLabel(abb);
        typeText.setFont(boldFont);
        typeText.setForeground(whiteColor);
        typePanel.setBackground(color);
        typePanel.setPreferredSize(new Dimension(60, 20));
        typePanel.add(typeText);

        JLabel titleText = new JLabel(title);
        titleText.setFont(subTitleFont);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(lightGreenColor);
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        titlePanel.add(typePanel);
        titlePanel.add(titleText);
        titlePanel.setPreferredSize(new Dimension(300, 30));
        return titlePanel;
    }

    public static JPanel helloMessageConstructor(String userName, int userId){
        JPanel helloMessage = new JPanel();
        helloMessage.setLayout(new FlowLayout(FlowLayout.RIGHT));
        helloMessage.setBackground(lightGreenColor);
        setSizeInLayout(helloMessage, new Dimension(360, 40));

        JLabel messageLabel = new JLabel("Hello, " + userName + " (" + userId + ")");
        setPromptFont(messageLabel);

        messageLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        helloMessage.add(messageLabel);
        return helloMessage;
    }


}
