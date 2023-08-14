package driver.screen;

import adapter.controller.CloseQuestionControl;
import adapter.controller.ControlContainer;
import adapter.controller.PostControl;
import businessrule.usecase.util.PostDisplayFormatter;
import entity.ApplicationException;

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

public class QuestionOpenClientUI extends QuestionOpenAttorneyUI{
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
    static final String CLOSE_BUTTON_NAME = "Close Question";

    public QuestionOpenClientUI(String userName, int userId, UIManager UIManager, String title,
                                String questionType, LocalDate deadline, Map<Integer, PostDisplayFormatter> postMap) {
        super(userName, userId ,UIManager,title, questionType, deadline, postMap);

        //The spacer
        JPanel spacer = addSpacer(20);
        JPanel spacer2 = addSpacer(30);

        //The close function

        List<String> buttonList = new ArrayList<>();
        buttonList.add(CLOSE_BUTTON_NAME);
        JPanel closeButton = setButtonPanel(buttonList, new Dimension(200, 50), 20, this);

        ///Add everything together
        add(topPanel);
        add(spacer2);
        add(inputPost);
        add(spacer2);
        add(closeButton);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        ControlContainer controlContainer = UIManager.getControlContainer();
        JPanel screens = UIManager.getScreens();
        CardLayout cardLayout = UIManager.getCardLayout();
        switch (actionCommand) {
            case POST_BUTTON_NAME:
                System.out.println("The client wants to post a reply.");
                try {
                    PostControl postControl = controlContainer.getPostControl();
                    postControl.createPost(questionId, inputPostArea.getText());
                } catch (ApplicationException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
                break;

            case CLOSE_BUTTON_NAME:
                System.out.println("Client wants to close the question.");
                try {
                    CloseQuestionControl closeQuestionControl = controlContainer.getCloseQuestionControl();
                    closeQuestionControl.closeQuestion(questionId);
                    JFrame rateFrame = new JFrame("Rate this question");
                    RatePanel ratePanel = new RatePanel(controlContainer, rateFrame, userId, questionId);
                    rateFrame.add(ratePanel);
                    rateFrame.pack();
                    rateFrame.setVisible(true);
                } catch (ApplicationException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
                break;

            case HOME_PAGE_BUTTON_NAME:
                cardLayout.show(screens, "Home Page");
                break;

            case BACK_BUTTON_NAME:
                cardLayout.show(screens, "Question List");
                break;
        }
    }
}
