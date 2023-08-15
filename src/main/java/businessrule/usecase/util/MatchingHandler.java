package businessrule.usecase.util;

import businessrule.gateway.AttorneyGateway;
import businessrule.gateway.ClientGateway;
import businessrule.gateway.QuestionGateway;
import businessrule.requestmodel.RegistrationData;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import driver.database.AttorneyRepository;
import driver.database.ClientRepository;
import driver.database.QuestionRepo;
import entity.Post;
import entity.Question;
import entity.Attorney;
import entity.Client;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.factory.ClientFactory;
import java.nio.charset.StandardCharsets;

/**
 * This class represents the process of matching questions with attorneys.
 * It uses various gateways and utility classes to reach this process.
 */
public class MatchingHandler {
    final AttorneyGateway attorneyGateway;
    final ClientGateway clientGateway;
    final QuestionGateway questionGateway;
    final ObjectMapper objectMapper = new ObjectMapper();
    final PythonReader pythonReader = new PythonReader();

    /**
     * Constructs a `MatchingHandler` object with the given gateways.
     *
     * @param attorneyGateway The gateway for accessing attorney information.
     * @param clientGateway The gateway for accessing client information.
     * @param questionGateway The gateway for accessing question information.
     */
    public MatchingHandler(AttorneyGateway attorneyGateway, ClientGateway clientGateway, QuestionGateway questionGateway) {
        this.attorneyGateway = attorneyGateway;
        this.clientGateway = clientGateway;
        this.questionGateway = questionGateway;
    }

    /**
     * Initiates the matching process by obtaining matching results and updating recommendations.
     */
    public void match() {
        try {
            MatchingResult matchingResult = getMatching();
            updateMatching(matchingResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates attorney recommendations based on the matching results.
     *
     * @param matchingResult The result of the matching process.
     */
    public void updateMatching(MatchingResult matchingResult) {
        // clear all matching
        attorneyGateway.clearAllRecommendations();

        // update
        for (Matching matching: matchingResult.getMatchingResult()){
            Question question = questionGateway.get(matching.getQuestionId());
            attorneyGateway.addRecommendation(matching.getAttorneyId(), question);
        }
    }

    /**
     * Retrieves matching results by processing questions and attorneys.
     *
     * @return The matching results.
     * @throws IOException If there's an I/O error during the process.
     */
    public MatchingResult getMatching() throws IOException {
        List<Question> questionList = questionGateway.getNotTakenQuestion();
        List<Attorney> attorneyList = attorneyGateway.getAll();
        Map<Integer[], Double> weights = constructWeight(questionList, attorneyList);

        List<Integer[]> matchingResult = pythonMatching(getQuestionIdList(questionList), getAttorneyIdList(attorneyList), weights);
        List<Matching> matchingList = new ArrayList<>();
        for (Integer[] match: matchingResult) {
            matchingList.add(new Matching(match[0], match[1]));
        }
        return new MatchingResult(matchingList);
    }


    private List<Integer[]> pythonMatching(List<Integer> questions, List<Integer> attorneys, Map<Integer[], Double> weights) throws IOException{
        Map<String, Double> stringWeights = new HashMap<>();
        for (Map.Entry<Integer[], Double> entry : weights.entrySet()) {
            String keyAsString = Arrays.toString(entry.getKey());
            stringWeights.put(keyAsString, entry.getValue());
        }

        Map<String, Object> javaPara = new HashMap<>();
        javaPara.put("questions", questions);
        javaPara.put("attorneys", attorneys);
        javaPara.put("weights", stringWeights);
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

    public Map<Integer[], Double> constructWeight(List<Question> questionList, List<Attorney> attorneyList) throws IOException{
        // initialize the map to store weights
        Map<Integer[], Double> weights = new HashMap<>();

        // Loop over question and attorney list
        for (Question question: questionList) {
            Client client = clientGateway.get(question.getAskedByClient());
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
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
