package driver.screen;

import adapter.controller.CloseQuestionControl;
import adapter.controller.ControlContainer;
import adapter.controller.PostControl;
import businessrule.usecase.util.PostDisplayFormatter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static driver.screen.UIDesign.*;
import static driver.screen.UIDrawer.*;

public class TheQuestionOpenUI extends QuestionUI implements ActionListener {
    protected String userName;
    protected int userId;
    protected JPanel helloMessage;
    protected UIManager UIManager;
    JPanel questionTitle;
    JPanel previousDiscussions;
    JScrollPane postScrollPane;
    JPanel newReply;
    Map<Integer, PostDisplayFormatter> postMap;
    JTextArea inputPostArea = new JTextArea(3, 30);
    static final String CLOSE_BUTTON_NAME = "Close question";
    static final String NEW_REPLY_PLACEHOLDER = "Type your content here...";
    static final String POST_BUTTON_NAME = "Post";

    public TheQuestionOpenUI(String userName, int userId, UIManager UIManager, String title,
                             String questionType, LocalDate deadline, Map<Integer, PostDisplayFormatter> postMap) {
        super(userName, userId ,UIManager,title, questionType, deadline, postMap);

        //The input post
        inputPostArea.setLineWrap(true);
        inputPostArea.setWrapStyleWord(true);
        PlaceholderText placeholderText = new PlaceholderText(inputPostArea, NEW_REPLY_PLACEHOLDER);
        inputPostArea.addFocusListener(placeholderText);
        inputPostArea.getDocument().addDocumentListener(placeholderText);

        JScrollPane inputScrollPane = new JScrollPane(inputPostArea);
        inputScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        inputScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        setSizeInLayout(inputScrollPane, new Dimension(270, 50));

        //add the textbox and the reply button together
        JButton postReply = new JButton(POST_BUTTON_NAME);
        postReply.setForeground(darkGreenColor);
        Dimension postButtonSize = new Dimension(80, 50);
        setSizeInLayout(postReply, postButtonSize);
        postReply.addActionListener(this);
        JPanel overallInputPost = new JPanel();
        overallInputPost.setLayout(new FlowLayout());
        overallInputPost.add(inputScrollPane);
        overallInputPost.add(postReply);
        overallInputPost.setOpaque(false);

        //The spacer
        JPanel spacer = addSpacer(20);
        JPanel spacer2 = addSpacer(30);

        //The close function

        List<String> buttonList = new ArrayList<>();
        buttonList.add(CLOSE_BUTTON_NAME);
        buttonList.add(BACK_BUTTON_NAME);
        buttonList.add(HOME_PAGE_BUTTON_NAME);
        JPanel buttons = setButtonPanel(buttonList, new Dimension(200, 50), 20, this);

        ///Add everything together
        add(helloMessage);
        add(spacer2);
        add(questionTitle);
        add(spacer2);
        add(previousDiscussions);
        add(postScrollPane);
        add(spacer2);
        add(overallInputPost);
        add(spacer);
        add(buttons);
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
        ControlContainer controlContainer = UIManager.getControlContainer();
        JPanel screens = UIManager.getScreens();
        CardLayout cardLayout = UIManager.getCardLayout();
        if (POST_BUTTON_NAME.equals(actionCommand)) {
            System.out.println("The user wants to post a reply.");
            try {
                PostControl postControl = controlContainer.getPostControl();
                postControl.createPost(questionId, userId, inputPostArea.getText());
            } catch (ApplicationException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }else if (CLOSE_BUTTON_NAME.equals(actionCommand)){
            System.out.println("Client want to close the question");
            try {
                CloseQuestionControl closeQuestionControl = controlContainer.getCloseQuestionControl();
                closeQuestionControl.closeQuestion(questionId, userId);
                JFrame rateFrame = new JFrame("Rate this question");
                RatePanel ratePanel = new RatePanel(controlContainer, rateFrame, userId, questionId);
                rateFrame.add(ratePanel);
                rateFrame.pack();
                rateFrame.setVisible(true);
            } catch (ApplicationException ex){
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        } else if (HOME_PAGE_BUTTON_NAME.equals(actionCommand)){
            cardLayout.show(screens, "Home Page");
        }
    }
}
