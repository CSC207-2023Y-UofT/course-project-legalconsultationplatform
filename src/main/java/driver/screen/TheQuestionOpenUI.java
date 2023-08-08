package driver.screen;

import adapter.controller.CloseQuestionControl;
import adapter.controller.ControlContainer;
import adapter.controller.PostControl;
import adapter.controller.RateControl;
import businessrule.usecase.PostDisplayFormatter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

import static driver.screen.UIDesign.*;

public class TheQuestionOpenUI extends JPanel implements ActionListener {
    ControlContainer controlContainer;
    CardLayout cardLayout;
    JPanel screens;
    int userId;
    String userName;
    int questionId;
    String title;
    String type;
    LocalDate deadline;
    Map<Integer, PostDisplayFormatter> postMap;


    JTextArea inputPostArea = new JTextArea(3, 30);
    String[] rateList = {"Satisfied", "Not satisfied"};
    JComboBox<String> rate = new JComboBox<>(rateList);

    public TheQuestionOpenUI(ControlContainer controlContainer, CardLayout cardLayout,
                               JPanel screens, int userId, String userName, int questionId, String title,
                               String type, LocalDate deadline, Map<Integer, PostDisplayFormatter> postMap) {
        this.controlContainer = controlContainer;
        this.cardLayout = cardLayout;
        this.screens = screens;
        this.userId = userId;
        this.userName = userName;
        this.questionId = questionId;
        this.title = title;
        this.type = type;
        this.deadline = deadline;
        this.postMap = postMap;

        //Top half of the panel
        JPanel topPanel = new TheQuestionTopPanel(userId, userName, questionId, title, type, deadline, postMap);

        //The input post
        inputPostArea.setLineWrap(true);
        inputPostArea.setWrapStyleWord(true);
        PlaceholderText placeholderText = new PlaceholderText(inputPostArea, "Type your content here...");
        inputPostArea.addFocusListener(placeholderText);
        inputPostArea.getDocument().addDocumentListener(placeholderText);

        JScrollPane inputScrollPane = new JScrollPane(inputPostArea);
        inputScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        inputScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        setSizeInLayout(inputScrollPane, new Dimension(270, 50));


        //add the textbox and the reply button together
        JButton postReply = new JButton("Post");
        postReply.setForeground(darkGreenColor);
        Dimension postButtonSize = new Dimension(80, 50);
        setSizeInLayout(postReply, postButtonSize);
        postReply.addActionListener(this);
        JPanel overallInputPost = new JPanel();
        overallInputPost.setLayout(new FlowLayout());
        overallInputPost.add(inputScrollPane);
        overallInputPost.add(postReply);
        overallInputPost.setBackground(lightGreenColor);

        //The spacer
        JPanel bottomSpacer = addSpacer(30);
        JPanel spacer = addSpacer(20);

        //The close function
        JButton closeQuestion = new JButton("Close question");
        closeQuestion.addActionListener(this);
        closeQuestion.setForeground(darkGreenColor);
        setGeneralButton(closeQuestion);
        closeQuestion.setAlignmentX(CENTER_ALIGNMENT);

        //Home Page button
        JButton homePage = new JButton("Home Page");
        UIDesign.setGeneralButton(homePage);
        homePage.setAlignmentX(CENTER_ALIGNMENT);
        homePage.addActionListener(this);

        ///Add everything together
        setBackground(lightGreenColor);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(topPanel);
        add(overallInputPost);
        add(closeQuestion);
        add(spacer);
        add(homePage);
        add(bottomSpacer);
    }

    static class PlaceholderText extends FocusAdapter implements DocumentListener {
        private final JTextArea textArea;
        private final String placeholder;

        public PlaceholderText(JTextArea textArea, String placeholder) {
            this.textArea = textArea;
            this.placeholder = placeholder;
            showPlaceholder();
        }

        private void showPlaceholder() {
            textArea.setText(placeholder);
            textArea.setForeground(Color.GRAY);
        }

        private void hidePlaceholder() {
            textArea.setText("");
            textArea.setForeground(Color.BLACK);
        }

        @Override
        public void focusGained(FocusEvent e) {
            if (textArea.getText().equals(placeholder)) {
                hidePlaceholder();
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (textArea.getText().isEmpty()) {
                showPlaceholder();
            }
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            if (textArea.getText().equals(placeholder)) {
                hidePlaceholder();
            }
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            if (textArea.getText().isEmpty()) {
                showPlaceholder();
            }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if ("Post".equals(actionCommand)){
            System.out.println("The user posts a reply.");
            PostControl postControl = controlContainer.getPostControl();
            postControl.createPost(questionId, userId, inputPostArea.getText());
        } else if ("Close question".equals(actionCommand)){
            System.out.println("Client closes the question, return to client home page");
            CloseQuestionControl closeQuestionControl = controlContainer.getCloseQuestionControl();
            closeQuestionControl.closeQuestion(questionId, userId);
        } else if ("Home Page".equals(actionCommand)){
            ClientHomePageUI homePageUI = new ClientHomePageUI(controlContainer, cardLayout,
                    screens, userId, userName);
            screens.add(homePageUI, "Home Page");
            cardLayout.show(screens, "Home Page");
        }
    }
}
