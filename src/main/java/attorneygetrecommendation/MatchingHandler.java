package attorneygetrecommendation;

import businessrule.gateway.AttorneyGateway;
import businessrule.gateway.ClientGateway;
import businessrule.gateway.QuestionGateway;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import businessrule.gateway.*;
import java.io.*;
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
import org.apache.commons.io.IOUtils;
import java.nio.charset.StandardCharsets;


public class MatchingHandler {
//    final AttorneyGateway attorneyGateway;
//    final ClientGateway clientGateway;
//    final QuestionGateway questionGateway;
    final ObjectMapper objectMapper = new ObjectMapper();

//
//    public void updateMatching(MatchingResult matchingResult) {
//        attorneyGateway.clearAllRecommendations();
//
//        for (Matching matching: matchingResult.getMatchingResult()){
//            Attorney attorney = (Attorney) attorneyGateway.getUser(matching.getAttorneyId());
//            Question question = questionGateway.getQuestion(matching.getQuestionId());
//            attorney.addRecommendation(question);
//            attorneyGateway.updateRecommendations(matching.getAttorneyId());
//        }
//    }
//
//    // TODO: figure out how to decouple matchMap
//    public MatchingResult getMatching() {
//        List<Question> questionList = questionGateway.getNotTakenQuestion();
//        List<Attorney> attorneyList = attorneyGateway.getAllAttorney();
//        Map<Map<Integer, Integer>, Double> weights = constructWeight(questionList, attorneyList);
//
//        List<Map<Integer, Integer>> matchingResult = pythonMatching(getQuestionIdList(questionList), getAttorneyIdList(attorneyList), weights);
//        List<Matching> matchingList = new ArrayList<Matching>();
//        for (Map<Integer, Integer> matchMap: matchingResult) {
//            matchingList.add(new Matching());
//        }
//        return new MatchingResult(matchingList);
//    }
//
//    // TODO: figure out how to return the correct value
//    private List<Map<Integer, Integer>> pythonMatching(List<Integer> questions, List<Integer> attorneys, Map<Map<Integer, Integer>, Double> weights) {
//        try (Interpreter jep = new SharedInterpreter()) {
//
//            jep.runScript("");
//
//            String serializedQuestions= serializeEntity(questions);
//            String serializedAttorneys = serializeEntity(attorneys);
//            String serializedWeights = serializeEntity(weights);
//
//            // Call function to predict the probability of client satisfaction
//            jep.eval("result = matching_algo('" + serializedQuestions + "', '" + serializedAttorneys+ "', '" + serializedWeights + "')");
//            return jep.getValue("matching", List.class);
//        }
//    }
//
//
//
//    private Map<Map<Integer, Integer>, Double> constructWeight(List<Question> questionList, List<Attorney> attorneyList) {
//        // initialize the map to store weights
//        Map<Map<Integer, Integer>, Double> weights = new HashMap<>();
//
//        // Loop over question and attorney list
//        for (Question question: questionList) {
//            Client client = (Client) clientGateway.getUser(question.getAskedByClient());
//            for (Attorney attorney: attorneyList){
//                // initialize the map to store question, attorney id pair
//                Map<Integer, Integer> pair = new HashMap<>();
//                pair.put(question.getQuestionId(), attorney.getUserId());
//                // update weights
//                weights.put(pair, getProb(client, question, attorney));
//            }
//        }
//        return weights;
//    }

    private double getProb(Client client, Question question, Attorney attorney) throws JsonProcessingException, IOException {
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
        String command = "python lib/prediction.py --input=" + tempFile.toString();
        String result = readPythonExec(command);
        return objectMapper.readValue(result, double.class);
    }

    private String readPythonExec(String command) {
        Process proc;
        try {
            proc = Runtime.getRuntime().exec(command);
            int i = proc.waitFor();
            System.out.println(i);
            InputStream inputStream = proc.getInputStream();
            InputStream errorStream = proc.getErrorStream();
            String errorOutput = IOUtils.toString(errorStream, StandardCharsets.UTF_8);
            if (!errorOutput.isEmpty()) {
                System.err.println("Error from Python: " + errorOutput);
            }
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        } catch (IOException | InterruptedException e) {
            System.out.println("exception");
            e.printStackTrace();
        } return null;
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

        MatchingHandler m = new MatchingHandler();
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
