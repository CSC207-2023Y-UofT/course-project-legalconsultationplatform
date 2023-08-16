package infrastructure.screens;
import adapters.controllers.ControlContainer;
import infrastructure.screens.utils.UIManager;
import usecases.dto.PostDisplay;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static infrastructure.screens.utils.UIDesign.*;
import static infrastructure.screens.utils.UIDrawer.*;

/**
 * This abstract class represents a base for all question-specific user interface.
 */
public abstract class QuestionUI extends UserUI {
    int questionId;
    JPanel questionTitle = new JPanel();
    JPanel previousDiscussions = new JPanel();
    JScrollPane postScrollPane = new JScrollPane();
    JPanel topPanel = new JPanel();
    JPanel backAndHomepage;
    JPanel inputPost;
    JPanel closeButton;
    JTextArea inputPostArea = new JTextArea(3, 30);
    static final String BACK_BUTTON_NAME = "Back";
    static final String HOME_PAGE_BUTTON_NAME = "Home Page";
    static final String NEW_REPLY_PLACEHOLDER = "Type your content here...";
    static final String POST_BUTTON_NAME = "Post";
    static final String CLOSE_BUTTON_NAME = "Close Question";

    public QuestionUI(String userName, int userId, UIManager UIManager, int questionId, String title,
                      String questionType, LocalDate deadline, Map<Integer, PostDisplay> postMap) {
        super(userName, userId, UIManager);
        this.questionId = questionId;

        //Set question title
        questionTitle.setLayout(new BoxLayout(questionTitle, BoxLayout.Y_AXIS));
        JPanel titlePanel = questionTitleDrawer(questionType, title);
        titlePanel.setAlignmentX(CENTER_ALIGNMENT);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String deadlineString = "Deadline: " + deadline.format(formatter);
        JLabel deadlineLine = new JLabel(deadlineString);
        setPromptFont(deadlineLine);
        deadlineLine.setAlignmentX(CENTER_ALIGNMENT);

        questionTitle.add(titlePanel);
        questionTitle.add(deadlineLine);
        setSizeInLayout(questionTitle, new Dimension(360, 50));
        questionTitle.setOpaque(false);

        //Set previous discussion
        setSizeInLayout(previousDiscussions, new Dimension(350, 30));
        JLabel label = new JLabel("Previous discussions");
        label.setFont(new Font("Novo Sans", Font.BOLD, 16));
        label.setForeground(whiteColor);
        previousDiscussions.add(label);
        previousDiscussions.setBackground(darkGreenColor);

        //Set post scroll pane
        JPanel postScrollPanel = new JPanel();
        postScrollPanel.setLayout(new BoxLayout(postScrollPanel, BoxLayout.Y_AXIS));
        for (PostDisplay post : postMap.values()) {
            //read all variables from displayFormatter
            String name = post.getName();
            String postText = post.getPostText();
            String userType = post.getUserType();
            String postDate = post.getCreateAt().format(formatter);
            //lines
            String idLine = name + "(" + userType + ")";
            String dateLine = "Posted on: " + postDate;
            //Format them all into a textArea
            JPanel thisPost = singlePostDrawer(idLine, dateLine, postText, userType);
            //Add the postArea into the scrollpanel
            postScrollPanel.add(thisPost);
        }
        postScrollPane.setViewportView(postScrollPanel);
        postScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        postScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        setSizeInLayout(postScrollPane, new Dimension(350, 200));
        postScrollPane.setOpaque(false);


        //Set up topPanel

        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setOpaque(false);
        topPanel.setAlignmentX(CENTER_ALIGNMENT);
        topPanel.add(helloMessage);
        topPanel.add(questionTitle);
        topPanel.add(previousDiscussions);
        topPanel.add(postScrollPane);

        //Set up inpot post
        PlaceholderText placeholderText = new PlaceholderText(inputPostArea, NEW_REPLY_PLACEHOLDER);
        inputPostArea.addFocusListener(placeholderText);
        inputPostArea.getDocument().addDocumentListener(placeholderText);
        inputPost = replyAreaDrawer(inputPostArea, POST_BUTTON_NAME, this);

        //Set up close button
        List<String> closeButtonList = new ArrayList<>();
        closeButtonList.add(CLOSE_BUTTON_NAME);
        closeButton = setButtonPanel(closeButtonList, new Dimension(200, 50), 20, this);

        //Set up back and homepage buttons
        List<String> buttonList = new ArrayList<>();
        buttonList.add(BACK_BUTTON_NAME);
        buttonList.add(HOME_PAGE_BUTTON_NAME);
        backAndHomepage = setButtonPanel(buttonList, new Dimension(150, 50), 20, this);

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
        ControlContainer controlContainer = uiManager.getControlContainer();
        CardLayout cardLayout = uiManager.getCardLayout();
        JPanel screens = uiManager.getScreens();
        switch (actionCommand) {
            case HOME_PAGE_BUTTON_NAME:
                cardLayout.show(screens, "Home Page");
                break;

            case BACK_BUTTON_NAME:
                cardLayout.show(screens, "Question List");
                break;

            case POST_BUTTON_NAME:
                handlePostAction(controlContainer, screens, cardLayout);
                break;

            case CLOSE_BUTTON_NAME:
                handleCloseAction(controlContainer, screens, cardLayout);
                break;
        }
    }
    protected abstract void handlePostAction(ControlContainer controlContainer, JPanel screens,
                                             CardLayout cardLayout);
    protected abstract void handleCloseAction(ControlContainer controlContainer, JPanel screens,
                                              CardLayout cardLayout);
}
