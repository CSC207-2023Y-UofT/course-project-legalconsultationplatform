package driver.screen;
import businessrule.usecase.util.PostDisplayFormatter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static driver.screen.UIDesign.*;
import static driver.screen.UIDrawer.*;
import static java.awt.Color.white;


public class QuestionUI extends UserUI{
    protected String userName;
    protected int userId;
    protected JPanel helloMessage;
    protected UIManager UIManager;
    int questionId;
    JPanel questionTitle;
    JPanel previousDiscussions;
    JScrollPane postScrollPane;
    JPanel topPanel;
    JPanel backAndHomepage;
    static final String BACK_BUTTON_NAME = "Back";
    static final String HOME_PAGE_BUTTON_NAME = "Home Page";
    public QuestionUI(String userName, int userId, UIManager UIManager, String title,
                      String questionType, LocalDate deadline, Map<Integer, PostDisplayFormatter> postMap) {
        super(userName, userId, UIManager);

        questionTitle.setLayout(new BoxLayout(questionTitle, BoxLayout.Y_AXIS));
        JPanel titlePanel = questionTitleDrawer(questionType, title);
        titlePanel.setAlignmentX(CENTER_ALIGNMENT);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String deadlineString = "Deadline: " + deadline.format(formatter);
        JLabel deadlineLine = new JLabel(deadlineString);
        deadlineLine.setAlignmentX(CENTER_ALIGNMENT);

        questionTitle.add(titlePanel);
        questionTitle.add(deadlineLine);
        setSizeInLayout(questionTitle, new Dimension(360, 50));
        questionTitle.setOpaque(false);

        setSizeInLayout(previousDiscussions, new Dimension(350, 30));
        JLabel label = new JLabel("Previous discussions");
        label.setFont(new Font("Novo Sans", Font.BOLD, 16));
        label.setForeground(white);
        previousDiscussions.add(label);
        previousDiscussions.setBackground(darkGreenColor);

        JPanel postScrollPanel = new JPanel();
        postScrollPanel.setLayout(new BoxLayout(postScrollPanel, BoxLayout.Y_AXIS));
        for (PostDisplayFormatter post : postMap.values()) {
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
        JPanel spacer = addSpacer(20);

        topPanel.add(helloMessage);
        topPanel.add(questionTitle);
        topPanel.add(spacer);
        topPanel.add(previousDiscussions);
        topPanel.add(postScrollPane);

        //Set up back and homepage buttons
        List<String> buttonList = new ArrayList<>();
        buttonList.add(BACK_BUTTON_NAME);
        buttonList.add(HOME_PAGE_BUTTON_NAME);
        backAndHomepage = setButtonPanel(buttonList, new Dimension(150, 50), 20, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        CardLayout cardLayout = UIManager.getCardLayout();
        JPanel screens = UIManager.getScreens();
        if (HOME_PAGE_BUTTON_NAME.equals(actionCommand)) {
            cardLayout.show(screens, "Home Page");
        } else if (BACK_BUTTON_NAME.equals(actionCommand)){
            cardLayout.show(screens, "Question List");
        }
    }
}
