package attorneygetrecommendation;

import com.fasterxml.jackson.core.JsonProcessingException;
import gateway.*;
import java.io.File;
import jep.JepException;
import questionentities.Question;
import userentities.Attorney;
import userentities.Client;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import jep.Interpreter;
import jep.SharedInterpreter;

public class MatchingHandler {
    final AttorneyGateway attorneyGateway;
    final ClientGateway clientGateway;
    final QuestionGateway questionGateway;
    final ObjectMapper objectMapper = new ObjectMapper();

    public MatchingHandler(AttorneyGateway attorneyGateway, ClientGateway clientGateway, QuestionGateway questionGateway) {
        this.attorneyGateway = attorneyGateway;
        this.clientGateway = clientGateway;
        this.questionGateway = questionGateway;
    }

    public void updateMatching(MatchingResult matchingResult) {
        attorneyGateway.clearAllRecommendations();

        for (Matching matching: matchingResult.getMatchingResult()){
            Attorney attorney = (Attorney) attorneyGateway.getUser(matching.getAttorneyId());
            Question question = questionGateway.getQuestion(matching.getQuestionId());
            attorney.addRecommendation(question);
            attorneyGateway.updateRecommendations(matching.getAttorneyId());
        }
    }

    // TODO: figure out how to decouple matchMap
    public MatchingResult getMatching() {
        List<Question> questionList = questionGateway.getNotTakenQuestion();
        List<Attorney> attorneyList = attorneyGateway.getAllAttorney();
        Map<Map<Integer, Integer>, Double> weights = constructWeight(questionList, attorneyList);

        List<Map<Integer, Integer>> matchingResult = pythonMatching(getQuestionIdList(questionList), getAttorneyIdList(attorneyList), weights);
        List<Matching> matchingList = new ArrayList<Matching>();
        for (Map<Integer, Integer> matchMap: matchingResult) {
            matchingList.add(new Matching());
        }
        return new MatchingResult(matchingList);
    }

    private double getProb(Client client, Question question, Attorney attorney) throws JepException {
        try (Interpreter jep = new SharedInterpreter()) {
            // Run python scrip of ml algo
            jep.runScript("");

            // Serialize all entity
            String serializedClient = serializeEntity(client);
            String serializedQuestion = serializeEntity(question);
            String serializedAttorney = serializeEntity(attorney);

            // Call function to predict the probability of client satisfaction
            jep.invoke("result = calculate_probability('" + serializedClient + "', '" + serializedQuestion + "', '" + serializedAttorney + "')");
            return jep.getValue("prob", double.class);
        }
    }

    // TODO: figure out how to return the correct value
    private List<Map<Integer, Integer>> pythonMatching(List<Integer> questions, List<Integer> attorneys, Map<Map<Integer, Integer>, Double> weights) {
        try (Interpreter jep = new SharedInterpreter()) {

            jep.runScript("");

            String serializedQuestions= serializeEntity(questions);
            String serializedAttorneys = serializeEntity(attorneys);
            String serializedWeights = serializeEntity(weights);

            // Call function to predict the probability of client satisfaction
            jep.eval("result = matching_algo('" + serializedQuestions + "', '" + serializedAttorneys+ "', '" + serializedWeights + "')");
            return jep.getValue("matching", List.class);
        }
    }

    private Map<Map<Integer, Integer>, Double> constructWeight(List<Question> questionList, List<Attorney> attorneyList) {
        // initialize the map to store weights
        Map<Map<Integer, Integer>, Double> weights = new HashMap<>();

        // Loop over question and attorney list
        for (Question question: questionList) {
            Client client = (Client) clientGateway.getUser(question.getAskedByClient());
            for (Attorney attorney: attorneyList){
                // initialize the map to store question, attorney id pair
                Map<Integer, Integer> pair = new HashMap<>();
                pair.put(question.getQuestionId(), attorney.getUserId());
                // update weights
                weights.put(pair, getProb(client, question, attorney));
            }
        }
        return weights;
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

    private String serializeEntity(Object entity) {
        try {
            return objectMapper.writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Interpreter jep = new SharedInterpreter();
    }
}
