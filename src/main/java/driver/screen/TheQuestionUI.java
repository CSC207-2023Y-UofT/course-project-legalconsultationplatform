package driver.screen;

import adapter.controller.CloseQuestionControl;
import adapter.controller.ControlContainer;
import adapter.controller.PostControl;
import adapter.controller.RateControl;
import businessrule.usecase.util.PostDisplayFormatter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;

import static driver.screen.UIDesign.*;

/**
 * TheQuestionUI class represents the user interface for a specific question.
 * It displays information about the question, posts, and provides interaction
 * options such as posting replies, closing the question, and rating it.
 */
public class TheQuestionUI extends JPanel implements ActionListener {
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

    JTextArea inputPostArea = new JTextArea();
    String[] rateList = {"Satisfied", "Not satisfied"};
    JComboBox<String> rate = new JComboBox<>(rateList);

    /**
     * Constructs a new instance of TheQuestionUI with provided data and components.
     *
     * @param controlContainer The container holding various controller instances.
     * @param cardLayout       The CardLayout used for managing screen transitions.
     * @param screens          The panel containing various screens.
     * @param userId           The ID of the user interacting with the UI.
     * @param userName         The name of the user.
     * @param questionId       The ID of the question being displayed.
     * @param title            The title of the question.
     * @param type             The type/category of the question.
     * @param deadline         The deadline associated with the question.
     * @param postMap          A map of post IDs to their display formatters.
     */
    public TheQuestionUI(ControlContainer controlContainer, CardLayout cardLayout,
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


        //UserName and UserId
        String helloMessageString = "Hello, " + userName + "(" + userId + ")";
        JLabel helloMessage = new JLabel(helloMessageString);

        //Question title and type
        JPanel titlePanel = UIDesign.questionTitleDrawer(type, title);
        //Question deadline
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String deadlineString = "Deadline: " + deadline.format(formatter);
        JLabel deadlineLine = new JLabel(deadlineString);
        UIDesign.setPromptFont(deadlineLine);


        //The scrollable posts
        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
        JLabel previousPost = new JLabel("Followup Discussions");
        UIDesign.setNameFont(previousPost);

        int numberOfPosts = postMap.size();
        JScrollPane postScrollPane = new JScrollPane();

        JPanel postScrollPanel = new JPanel();
        postScrollPanel.setLayout(new GridLayout(numberOfPosts, 1));
        for (PostDisplayFormatter post : postMap.values()) {
            //read all variables from displayFormatter
            String name = post.getName();
            String postText = post.getPostText();
            String userType;
            if (post.isClient()) {
                userType = "Client";
            } else {
                userType = "Attorney";
            }
            String postDate = post.getCreateAt().format(formatter);
            //lines
            String idLine = name + "(" + userType + ")";
            String dateLine = "Posted on: " + postDate;
            //Format them all into a textArea
            JPanel thisPost = UIDesign.singlePostDrawer(idLine, dateLine, postText, userType);
            //Add the postArea into the scrollpanel
            postScrollPanel.add(thisPost);
        }
        postScrollPane.setViewportView(postScrollPanel);
        Dimension scrollPaneDimension = new Dimension(330, 200);
        postScrollPane.setMinimumSize(scrollPaneDimension);
        postScrollPane.setMinimumSize(scrollPaneDimension);
        postScrollPane.setPreferredSize(scrollPaneDimension);

        postPanel.add(previousPost);
        postPanel.add(postScrollPane);
        previousPost.setAlignmentX(LEFT_ALIGNMENT);
        postScrollPane.setAlignmentX(LEFT_ALIGNMENT);


        //The new post textBox
        inputPostArea.setLineWrap(true);
        inputPostArea.setWrapStyleWord(true);
        JPanel inputPostWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPostWrapper.setBackground(whiteColor);
        inputPostWrapper.add(inputPostArea);
        JScrollPane inputScrollPane = new JScrollPane(inputPostWrapper);
        inputScrollPane.setPreferredSize(new Dimension(250, 30));

        //add the textbox and the reply button together
        JButton postReply = new JButton("Post");
        postReply.setForeground(darkGreenColor);
        Dimension postButtonSize = new Dimension(60, 40);
        postReply.setPreferredSize(postButtonSize);
        postReply.setMaximumSize(postButtonSize);
        postReply.setMinimumSize(postButtonSize);
        postReply.addActionListener(this);
        JPanel overallInputPost = new JPanel();
        overallInputPost.setLayout(new FlowLayout());
        overallInputPost.add(inputScrollPane);
        overallInputPost.add(postReply);
        overallInputPost.setBackground(lightGreenColor);

        //The rate function
        DropDownPanel ratePanel = new DropDownPanel(new JLabel("Rate this question"), rate);

        //The three buttons
        JPanel buttons = new JPanel();
        buttons.setBackground(UIDesign.lightGreenColor);
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS)); // Use X_AXIS for horizontal alignment
        JButton closeQuestion = new JButton("Close question");
        JButton rateQuestion = new JButton("Rate question");
        closeQuestion.setPreferredSize(buttonSize);
        rateQuestion.setPreferredSize(buttonSize);

        buttons.add(closeQuestion);
        buttons.add(rateQuestion);
        closeQuestion.addActionListener(this);
        rateQuestion.addActionListener(this);

        //Add everything into JPanel
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(lightGreenColor);
        contentPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        contentPanel.add(helloMessage);
        contentPanel.add(titlePanel);
        contentPanel.add(deadlineLine);
        contentPanel.add(postScrollPane);
        contentPanel.add(overallInputPost); // Use the JScrollPane for inputPostArea
        contentPanel.add(ratePanel);
        contentPanel.add(buttons);

        this.add(contentPanel);





    }
    /**
     * Handles user actions performed within the UI.
     *
     * @param e The ActionEvent representing the user action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if ("Post reply".equals(actionCommand)){
            System.out.println("The user posts a reply.");
            PostControl postControl = controlContainer.getPostControl();
            postControl.createPost(questionId, userId, inputPostArea.getText());
        } else if ("Close question".equals(actionCommand)){
            System.out.println("Client closes the question, return to client home page");
            CloseQuestionControl closeQuestionControl = controlContainer.getCloseQuestionControl();
            closeQuestionControl.closeQuestion(questionId, userId);
        } else if ("Rate question".equals(actionCommand)){
            System.out.println("Client rates the question, return to client home page");
            RateControl rateControl = controlContainer.getRateControl();
            int updateRate;
            if (Objects.equals(rate.getSelectedItem(), "Satisfied")){
                updateRate = 1;
            } else{
                updateRate = 0;
            }
            rateControl.rateAnswer(updateRate, questionId, userId);
        }
    }
}
