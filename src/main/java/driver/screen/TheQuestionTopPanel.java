package driver.screen;

import businessrule.usecase.PostDisplayFormatter;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static driver.screen.UIDesign.*;

public class TheQuestionTopPanel extends JPanel{
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

    public TheQuestionTopPanel(int userId, String userName, int questionId, String title,
                               String type, LocalDate deadline, Map<Integer, PostDisplayFormatter> postMap) {
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

        //Question title and deadline
        JPanel titleContainer = new JPanel();
        titleContainer.setLayout(new BoxLayout(titleContainer, BoxLayout.Y_AXIS));
        JPanel titlePanel = UIDesign.questionTitleDrawer(type, title);
        titlePanel.setAlignmentX(CENTER_ALIGNMENT);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String deadlineString = "Deadline: " + deadline.format(formatter);
        JLabel deadlineLine = new JLabel(deadlineString);
        deadlineLine.setOpaque(false);
        deadlineLine.setAlignmentX(CENTER_ALIGNMENT);

        titleContainer.add(titlePanel);
        titleContainer.add(deadlineLine);
        setSizeInLayout(titleContainer, new Dimension(360, 50));

        JPanel previousPosts = new JPanel();

        JLabel label = new JLabel("Previous discussions");
        UIDesign.setNameFont(label);
        label.setForeground(whiteColor);
        JPanel labelPanel = new JPanel();
        labelPanel.setBackground(darkGreenColor);
        setSizeInLayout(labelPanel, new Dimension(350, 30));
        labelPanel.add(label);

        JPanel postScrollPanel = new JPanel();
        postScrollPanel.setLayout(new BoxLayout(postScrollPanel, BoxLayout.Y_AXIS));
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
        JScrollPane postScrollPane = new JScrollPane(postScrollPanel);
        postScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        postScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        setSizeInLayout(postScrollPane, new Dimension(350, 200));
        postScrollPane.setOpaque(false);

        previousPosts.setLayout(new BoxLayout(previousPosts, BoxLayout.Y_AXIS));
        setSizeInLayout(previousPosts, new Dimension(350, 250));
        previousPosts.add(labelPanel);
        previousPosts.add(postScrollPane);

        //Add everything into JPanel
        UIDesign.setBackgroundFrame(this);
        setBackground(lightGreenColor);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setSizeInLayout(this, new Dimension(360, 500));
        add(helloMessage);
        add(titleContainer);
        add(postScrollPane);
    }
}
