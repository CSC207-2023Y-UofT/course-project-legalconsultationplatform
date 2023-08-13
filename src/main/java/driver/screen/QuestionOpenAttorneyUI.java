package driver.screen;

import adapter.controller.ControlContainer;
import adapter.controller.PostControl;
import businessrule.usecase.util.PostDisplayFormatter;
import entity.ApplicationException;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.LocalDate;
import java.util.Map;

import static driver.screen.UIDesign.darkGreenColor;
import static driver.screen.UIDesign.setSizeInLayout;
import static driver.screen.UIDrawer.*;

public class QuestionOpenAttorneyUI extends QuestionUI{
    protected String userName;
    protected int userId;
    protected UIManager UIManager;
    int questionId;
    JPanel topPanel;
    JPanel backAndHomepage;
    JPanel inputPost;
    JTextArea inputPostArea = new JTextArea(3, 30);
    static final String BACK_BUTTON_NAME = "Back";
    static final String HOME_PAGE_BUTTON_NAME = "Home Page";
    static final String NEW_REPLY_PLACEHOLDER = "Type your content here...";
    static final String POST_BUTTON_NAME = "Post";

    public QuestionOpenAttorneyUI(String userName, int userId, UIManager UIManager, String title,
                                  String questionType, LocalDate deadline, Map<Integer, PostDisplayFormatter> postMap) {
        super(userName, userId, UIManager, title, questionType, deadline, postMap);

        //spacers
        JPanel spacer = addSpacer(30);
        JPanel spacer2 = addSpacer(20);

        //reply text area
        inputPostArea.setLineWrap(true);
        inputPostArea.setWrapStyleWord(true);
        QuestionOpenClientUI.PlaceholderText placeholderText = new QuestionOpenClientUI.PlaceholderText(inputPostArea, NEW_REPLY_PLACEHOLDER);
        inputPostArea.addFocusListener(placeholderText);
        inputPostArea.getDocument().addDocumentListener(placeholderText);

        JScrollPane inputScrollPane = new JScrollPane(inputPostArea);
        inputScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        inputScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        setSizeInLayout(inputScrollPane, new Dimension(270, 50));

        JButton postReply = new JButton(POST_BUTTON_NAME);
        postReply.setForeground(darkGreenColor);
        Dimension postButtonSize = new Dimension(80, 50);
        setSizeInLayout(postReply, postButtonSize);
        postReply.addActionListener(this);
        inputPost = new JPanel();
        inputPost.setLayout(new FlowLayout());
        inputPost.add(inputScrollPane);
        inputPost.add(postReply);
        inputPost.setOpaque(false);

        //Add everything together
        add(topPanel);
        add(spacer2);
        add(inputPost);
        add(spacer2);
        add(backAndHomepage);
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

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        CardLayout cardLayout = UIManager.getCardLayout();
        ControlContainer controlContainer = UIManager.getControlContainer();
        JPanel screens = UIManager.getScreens();
        if (HOME_PAGE_BUTTON_NAME.equals(actionCommand)) {
            cardLayout.show(screens, "Home Page");
        } else if (BACK_BUTTON_NAME.equals(actionCommand)){
            cardLayout.show(screens, "Question List");
        } else if (POST_BUTTON_NAME.equals(actionCommand)){
            System.out.println("The user wants to post a reply.");
            try {
                PostControl postControl = controlContainer.getPostControl();
                postControl.createPost(questionId, inputPostArea.getText());
            } catch (ApplicationException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }
}
