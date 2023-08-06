package attorneygetrecommendation;

import businessrule.gateway.AttorneyGateway;
import businessrule.gateway.ClientGateway;
import businessrule.gateway.QuestionGateway;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import businessrule.gateway.*;

import java.io.*;

import entity.Question;
import entity.Attorney;
import entity.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import java.nio.charset.StandardCharsets;

import javax.persistence.criteria.CriteriaBuilder;


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

    private double getProb(Client client, Question question, Attorney attorney) throws JsonProcessingException {
        // Serialize all entity
        Map<String, Object> javaPara = new HashMap<>();
        javaPara.put("client", serialize(client));
        javaPara.put("question", serialize(question));
        javaPara.put("attorney", serialize(attorney));
        String input = serialize(javaPara);

        // Using command line to get output from python
        String command = "python lib/prediction.py --input=" + input;
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
        MatchingHandler m = new MatchingHandler();
        Client c = new Client();
        Question q = new Question();
        Attorney a = new Attorney();
        c.setPostalCode("0");
        a.setPostalCode("0");
        try {
            System.out.println(m.getProb(c, q, a));}
        catch (JacksonException e) {
            e.printStackTrace();
        }

    }
}
