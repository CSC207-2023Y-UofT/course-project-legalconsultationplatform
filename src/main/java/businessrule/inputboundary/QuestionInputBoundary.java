package businessrule.inputboundary;

import businessrule.responsemodel.TheQuestionResponseModel;
import businessrule.requestmodel.QuestionRequestModel;

/**
 * This interface provides a method for creating a question based on the provided question request model.
 */
public interface QuestionInputBoundary {

    /**
     * Create a question based on the provided question request model.
     *
     * @param questionRequestModel The request model containing the necessary information for question creation.
     * @return A response model containing the information about the created question.
     */
    TheQuestionResponseModel createQuestion(QuestionRequestModel questionRequestModel);
}