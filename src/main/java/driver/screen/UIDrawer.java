package driver.screen;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import static driver.screen.UIDesign.*;

import java.awt.event.ActionListener;
import java.util.List;

public class UIDrawer {


    //Buttons
    public static void setButton(JButton button, Dimension dimension){
        button.setFont(boldFont);
        button.setForeground(whiteColor);
        button.setOpaque(true);
        button.setBackground(darkGreenColor);
        button.setBorderPainted(false);
        setSizeInLayout(button, dimension);
    }

    public static JPanel setButtonPanel(List<String> buttons, Dimension dimension,
                                        int spacerHeight, ActionListener parent){
        JPanel result = new JPanel();
        result.setOpaque(false);
        result.setLayout(new BoxLayout(result, BoxLayout.Y_AXIS));
        for (String s: buttons){
            JPanel spacer = addSpacer(spacerHeight);
            JButton button = new JButton(s);
            button.addActionListener(parent);
            setButton(button, dimension);
            result.add(button);
            result.add(spacer);
        }
        return result;
    }

    //Spacer
    public static JPanel addSpacer(int height){
        JPanel topSpacer = new JPanel();
        Dimension topSpacerDimension = new Dimension(FRAME_WIDTH, height);
        setSizeInLayout(topSpacer, topSpacerDimension);
        topSpacer.setOpaque(false);
        return topSpacer;
    }

    //Hello Message
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

    //Input fields
    public static JPanel labelTextPanelDrawer(JLabel label, JTextField textField) {
        JPanel result = new JPanel();
        result.setLayout(new BoxLayout(result, BoxLayout.Y_AXIS));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        textField.setAlignmentX(Component.LEFT_ALIGNMENT);
        textField.setColumns(25);
        setPromptFont(label);
        result.add(label);
        result.add(textField);
        result.setOpaque(false);
        return result;
    }

    public static JPanel dropDownPanelDrawer(JLabel label, JComboBox<String> dropDownBox){
        JPanel result = new JPanel();
        result.setLayout(new BoxLayout(result, BoxLayout.Y_AXIS));
        result.add(label);
        result.add(dropDownBox);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        dropDownBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        setPromptFont(label);
        result.add(label);
        result.add(dropDownBox);
        result.setOpaque(false);
        return result;
    }

    public static JPanel datePanelDrawer(JLabel label, JDateChooser dateChooser){
        JPanel result = new JPanel();
        result.setLayout(new BoxLayout(result, BoxLayout.Y_AXIS));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        dateChooser.setAlignmentX(Component.LEFT_ALIGNMENT);
        setPromptFont(label);
        result.add(label);
        result.add(dateChooser);
        result.setOpaque(false);
        return result;
    }

    //Drawer for the question list page
    public static JPanel singleQuestionDrawer(String title, String questionType, String deadline){
        JPanel questionTitle = questionTitleDrawer(questionType, title);
        JPanel singleQuestion = new JPanel();
        setSizeInLayout(singleQuestion, new Dimension(340, 60));
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

    //Drawer for question page
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

    public static String titleManipulation(String input) {
        if (input.length() <= 15) {
            return input; // Return unchanged if length is 15 or less
        } else {
            return input.substring(0, 15) + "..."; // Truncate and add "..."
        }
    }

    public static JPanel questionTitleDrawer(String questionType, String title){
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
        JPanel typePanel = new JPanel();
        typePanel.setLayout(new BoxLayout(typePanel, BoxLayout.PAGE_AXIS));
        JLabel typeText = new JLabel(abb);
        typeText.setAlignmentX(Component.CENTER_ALIGNMENT);
        typeText.setFont(boldFont);
        typeText.setForeground(whiteColor);
        typePanel.setBackground(color);
        typePanel.setPreferredSize(new Dimension(60, 20));
        typePanel.add(Box.createVerticalGlue());
        typePanel.add(typeText);
        typePanel.add(Box.createVerticalGlue());

        String manipulatedTitle = titleManipulation(title);
        JLabel titleText = new JLabel(manipulatedTitle);
        titleText.setFont(subTitleFont);
        titleText.setOpaque(false);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        titlePanel.add(typePanel);
        titlePanel.add(titleText);
        titlePanel.setPreferredSize(new Dimension(300, 30));
        titlePanel.setOpaque(false);
        return titlePanel;
    }

    protected static JPanel replyAreaDrawer(JTextArea inputPostArea, String buttonName, ActionListener parent) {
        inputPostArea.setLineWrap(true);
        inputPostArea.setWrapStyleWord(true);

        JScrollPane inputScrollPane = new JScrollPane(inputPostArea);
        inputScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        inputScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        setSizeInLayout(inputScrollPane, new Dimension(270, 50));

        JButton postReply = new JButton(buttonName);
        postReply.setForeground(darkGreenColor);
        Dimension postButtonSize = new Dimension(80, 50);
        setSizeInLayout(postReply, postButtonSize);
        postReply.addActionListener(parent);
        JPanel inputPost = new JPanel();
        inputPost.setLayout(new FlowLayout());
        inputPost.add(inputScrollPane);
        inputPost.add(postReply);
        inputPost.setOpaque(false);
        return inputPost;
    }
}
