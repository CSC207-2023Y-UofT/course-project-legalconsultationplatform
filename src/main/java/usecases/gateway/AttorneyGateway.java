package usecases.gateway;

import entities.user.Attorney;
import entities.Question;

/**
 * This interface provides methods for retrieving an attorney, clearing recommendations, and
 * adding recommendations for questions.
 */
public interface AttorneyGateway extends UserGateway<Attorney> {

    /**
     * Get an attorney by their ID.
     *
     * @param id The ID of the attorney to retrieve.
     * @return The attorney object associated with the provided ID.
     */
    @Override
    Attorney get(int id);

    /**
     * Clear all recommendations associated with attorneys.
     */
    void clearAllRecommendations();


    /**
     * Add a recommendation for a question to an attorney.
     *
     * @param userId The ID of the user (attorney) receiving the recommendation.
     * @param question The question to be recommended.
     */
    void addRecommendation(int userId, Question question);
}
