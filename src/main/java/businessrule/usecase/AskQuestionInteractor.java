package businessrule.usecase;

import businessrule.usecase.util.PostDisplayFormatter;
import businessrule.usecase.util.RandomNumberGenerator;
import entity.*;
import businessrule.inputboundary.QuestionInputBoundary;
import businessrule.requestmodel.QuestionRequestModel;
import businessrule.gateway.ClientGateway;
import businessrule.gateway.QuestionGateway;
import businessrule.outputboundary.TheQuestionOutputBoundary;
import businessrule.responsemodel.TheQuestionResponseModel;
import entity.factory.QuestionFactory;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class AskQuestionInteractor implements QuestionInputBoundary {
    final QuestionGateway questionGateway;
    final TheQuestionOutputBoundary theQuestionOutputBoundary;
    final QuestionFactory questionFactory;
    final ClientGateway clientGateway;

    public AskQuestionInteractor(QuestionGateway questionGateway, TheQuestionOutputBoundary theQuestionOutputBoundary, QuestionFactory questionFactory, ClientGateway clientGateway) {
        this.questionGateway = questionGateway;
        this.theQuestionOutputBoundary = theQuestionOutputBoundary;
        this.questionFactory = questionFactory;
        this.clientGateway = clientGateway;
    }

    public TheQuestionResponseModel createQuestion(QuestionRequestModel questionRequestModel){
        try {checkValidateInput(questionRequestModel);}
        catch (ApplicationException e) {
            return theQuestionOutputBoundary.prepareFail(e.getMessage());
        }

        // create question entity
        Question question = createQuestionEntity(questionRequestModel);
        int askedByClient = questionRequestModel.getAskedByClient();
        Client askedBy = clientGateway.get(askedByClient);

        // construct response model
        Map<Integer, PostDisplayFormatter> postMap = new HashMap<>();
        TheQuestionResponseModel theQuestionResponseModel = new TheQuestionResponseModel(questionRequestModel.getAskedByClient(), question.getQuestionId(), askedBy.getUserName(), question.getTitle(), question.getType(), question.getLegalDeadline(),false, postMap);
        return theQuestionOutputBoundary.prepareSuccess(theQuestionResponseModel);
    }

    private void checkValidateInput(QuestionRequestModel questionRequestModel) {
        if (questionRequestModel.getQuestionCategory() == null) {
            throw new ApplicationException("Please specify your question type.");
        } else if (questionRequestModel.getTitle() == null) {
            throw new ApplicationException("Please specify your question title.");
        }

    }

    private int generateQuestionId(){
        RandomNumberGenerator generator = new RandomNumberGenerator();
        int randomQuestionId = generator.generateQuestionId(9);
        boolean exists = questionGateway.existsById(randomQuestionId);
        while (exists){
            randomQuestionId = generator.generateQuestionId(9);
            exists = questionGateway.existsById(randomQuestionId);
        }
        return randomQuestionId;
    }

    private Question createQuestionEntity(QuestionRequestModel questionRequestModel){
        // generate question id
        int randomQuestionId = generateQuestionId();

        // create question entity
        LocalDate now = LocalDate.now();
        Question question = questionFactory.create(randomQuestionId, questionRequestModel.getQuestionCategory(), questionRequestModel.getTitle(), now, questionRequestModel.getAskedByClient(), questionRequestModel.getLegalDeadline());
        questionGateway.save(question);
        clientGateway.updateQuestionList(questionRequestModel.getAskedByClient(), question);

        return question;
    }
}


