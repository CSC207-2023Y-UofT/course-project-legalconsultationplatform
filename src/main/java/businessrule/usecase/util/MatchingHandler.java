package businessrule.usecase.util;

import businessrule.gateway.AttorneyGateway;
import businessrule.gateway.ClientGateway;
import businessrule.gateway.QuestionGateway;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.*;
import com.fasterxml.jackson.core.type.TypeReference;
import driver.database.AttorneyRepository;
import driver.database.ClientRepository;
import driver.database.QuestionRepo;
import entity.Post;
import entity.Question;
import entity.Attorney;
import entity.Client;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;

public class MatchingHandler {
    final AttorneyGateway attorneyGateway;
    final ClientGateway clientGateway;
    final QuestionGateway questionGateway;
    final ObjectMapper objectMapper = new ObjectMapper();
    final PythonReader pythonReader = new PythonReader();

    public MatchingHandler(AttorneyGateway attorneyGateway, ClientGateway clientGateway, QuestionGateway questionGateway) {
        this.attorneyGateway = attorneyGateway;
        this.clientGateway = clientGateway;
        this.questionGateway = questionGateway;
    }

    public void match() {
        try {
            MatchingResult matchingResult = getMatching();
            updateMatching(matchingResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateMatching(MatchingResult matchingResult) {
        // clear all matching
        attorneyGateway.clearAllRecommendations();

        // update
        for (Matching matching: matchingResult.getMatchingResult()){
            Attorney attorney = (Attorney) attorneyGateway.get(matching.getAttorneyId());
            Question question = questionGateway.get(matching.getQuestionId());
            attorneyGateway.addRecommendation(matching.getAttorneyId(), question);
        }
    }

    public MatchingResult getMatching() throws IOException {
        List<Question> questionList = questionGateway.getNotTakenQuestion();
        List<Attorney> attorneyList = attorneyGateway.getAll();
        Map<Integer[], Double> weights = constructWeight(questionList, attorneyList);

        List<Integer[]> matchingResult = pythonMatching(getQuestionIdList(questionList), getAttorneyIdList(attorneyList), weights);
        List<Matching> matchingList = new ArrayList<Matching>();
        for (Integer[] match: matchingResult) {
            matchingList.add(new Matching(match[0], match[1]));
        }
        return new MatchingResult(matchingList);
    }

    private List<Integer[]> pythonMatching(List<Integer> questions, List<Integer> attorneys, Map<Integer[], Double> weights) throws IOException{
        Map<String, Object> javaPara = new HashMap<>();
        javaPara.put("questions", questions);
        javaPara.put("attorneys", attorneys);
        javaPara.put("weights", weights);
        String input = serialize(javaPara);

        // Java code to write to a temp file
        Path tempFile = Files.createTempFile("input", ".json");
        assert input != null;
        Files.write(tempFile, input.getBytes(StandardCharsets.UTF_8));

        // Using command line to get output from python
        String command = "python lib/matching.py --input=" + tempFile;
        String result = pythonReader.readPythonExec(command);
        return objectMapper.readValue(result, new TypeReference<>() {});
    }

    private Map<Integer[], Double> constructWeight(List<Question> questionList, List<Attorney> attorneyList) throws IOException{
        // initialize the map to store weights
        Map<Integer[], Double> weights = new HashMap<>();

        // Loop over question and attorney list
        for (Question question: questionList) {
            Client client = (Client) clientGateway.get(question.getAskedByClient());
            for (Attorney attorney: attorneyList){
                // initialize the array to store question, attorney id pair
                Integer[] pair = new Integer[]{question.getQuestionId(), attorney.getUserId()};
                // update weights
                weights.put(pair, getProb(client, question, attorney));
            }
        }
        return weights;
    }

    private double getProb(Client client, Question question, Attorney attorney) throws IOException {
        // Serialize all entity
        Map<String, Object> javaPara = new HashMap<>();
        javaPara.put("client", client);
        javaPara.put("question", question);
        javaPara.put("attorney", attorney);
        String input = serialize(javaPara);

        // Java code to write to a temp file
        Path tempFile = Files.createTempFile("input", ".json");
        assert input != null;
        Files.write(tempFile, input.getBytes(StandardCharsets.UTF_8));

        // Using command line to get output from python
        String command = "python lib/prediction.py --input=" + tempFile;
        String result = pythonReader.readPythonExec(command);
        return objectMapper.readValue(result, double.class);
    }

    private List<Integer> getQuestionIdList(List<Question> questionList) {
        List<Integer> questionIdList = new ArrayList<>();
        for (Question question: questionList) {
            questionIdList.add(question.getQuestionId());
        }
        return questionIdList;
    }

    private List<Integer> getAttorneyIdList(List<Attorney> attorneyList) {
        List<Integer> attorneyIdList = new ArrayList<>();
        for (Attorney attorney: attorneyList) {
            attorneyIdList.add(attorney.getUserId());
        }
        return attorneyIdList;
    }

    private String serialize(Object entity) {
        try {
            return objectMapper.writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        int QUESTION_ID = 3000;
        int ATTORNEY_ID = 1000;
        int CLIENT_ID = 2000;

        ClientGateway clientGateway1 = new ClientRepository();
        AttorneyGateway attorneyGateway1 = new AttorneyRepository();
        QuestionGateway questionGateway1 = new QuestionRepo();

        MatchingHandler m = new MatchingHandler(attorneyGateway1, clientGateway1, questionGateway1);
        Client c = new Client();
        c.setUserId(CLIENT_ID);
        Question q = new Question();
        q.setQuestionId(QUESTION_ID);
        Attorney a = new Attorney();
        a.setUserId(ATTORNEY_ID);

        Post attorneyPost = new Post();
        Post clientPost = new Post();
        clientPost.setPostId(1);
        clientPost.setPostText("I have a really trick issue. I really worry about that for more than one year and cannot figure that out. Could someone help me?");
        clientPost.setBelongsTo(CLIENT_ID);
        clientPost.setQuestionId(QUESTION_ID);
        attorneyPost.setPostId(2);
        attorneyPost.setPostText("ok, I understand. Unfortunately, I cannot answer this question. I suggest you ask for a new attorney or search on other website to better understand this problem");
        attorneyPost.setBelongsTo(ATTORNEY_ID);
        attorneyPost.setQuestionId(QUESTION_ID);

        q.setAskedByClient(CLIENT_ID);
        q.setTaken(true);
        q.setTakenByAttorney(ATTORNEY_ID);
        q.addPosts(clientPost);
        q.addPosts(attorneyPost);
        q.setRating(0);

        c.addQuestion(q);
        c.setPostalCode("0");
        a.setPostalCode("10000000");
        c.setAnnualIncome(10000);
        c.setGender("Female");

        a.addQuestion(q);

        try {
            System.out.println(m.getProb(c, q, a));}
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
