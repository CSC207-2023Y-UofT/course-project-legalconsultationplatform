package usecases.interactors;

import usecases.session.SessionManager;
import usecases.session.UserSession;
import usecases.gateway.ClientGateway;
import usecases.inputboundary.CloseInputBoundary;
import usecases.outputboundary.UserOutputBoundary;
import usecases.requests.CloseRequestModel;
import usecases.responses.UserResponseModel;
import entities.Question;
import usecases.gateway.QuestionGateway;

/**
 * This class represents a use case interactor for closing a question.
 * This class is responsible for handling the business logic related to closing a question in the system.
 */
public class CloseQuestionInteractor implements CloseInputBoundary {

    final QuestionGateway questionGateway;
    final UserOutputBoundary outputBoundary;
    final ClientGateway clientGateway;

    /**
     * Constructs an instance of CloseQuestionInteractor with the necessary dependencies.
     *
     * @param questionGateway       The gateway for question-related operations.
     * @param outputBoundary  The output boundary for preparing responses to the home page.
     * @param clientGateway    The gateway of client-specific operations
     */
    public CloseQuestionInteractor(QuestionGateway questionGateway, UserOutputBoundary outputBoundary, ClientGateway clientGateway) {
        this.questionGateway = questionGateway;
        this.outputBoundary = outputBoundary;
        this.clientGateway = clientGateway;
    }

    /**
     * Closes a question based on the provided CloseRequestModel and returns a response model for the home page.
     *
     * @param closeRequestModel The model containing information to close a question.
     * @return The response model for the home page indicating the outcome of the close attempt.
     */
    @Override
    public UserResponseModel closeQuestion(CloseRequestModel closeRequestModel) {
        // get input data
        UserSession session = SessionManager.getSession();
        UserResponseModel response = session.getUserResponseModel();
        int questionId = closeRequestModel.getQuestionId();
        Question question = questionGateway.get(questionId);


        // handle close logic and prepare response model
        if(question.isTaken()){
            questionGateway.updateIsClose(questionId, true);
            return outputBoundary.prepareSuccess(response);
        }
        else{
            return outputBoundary.prepareFail("You can only close this question once it was taken by attorney.");
        }
    }
}
