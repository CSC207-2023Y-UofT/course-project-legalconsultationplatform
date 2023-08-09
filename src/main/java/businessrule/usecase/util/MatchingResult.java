package businessrule.usecase.util;

import businessrule.usecase.util.Matching;

import java.util.List;

/**
 * This class represents the result of the matching process.
 * It contains a list of `Matching` objects that represent the matches between questions and attorneys.
 */
public class MatchingResult {
    private int resultId; // The unique ID for the result (not used in this class)
    private List<Matching> matchingResult; // The list of matching results

    /**
     * Constructs a `MatchingResult` object with the specified list of matching results.
     *
     * @param matchingResult The list of `Matching` objects representing the matching results.
     */
    public MatchingResult(List<Matching> matchingResult) {
        this.matchingResult = matchingResult;
    }

    /**
     * Gets the list of matching results.
     *
     * @return The list of `Matching` objects representing the matching results.
     */
    public List<Matching> getMatchingResult() {
        return matchingResult;
    }

    /**
     * Sets the list of matching results.
     *
     * @param matchingResult The list of `Matching` objects to set as matching results.
     */
    public void setMatchingResult(List<Matching> matchingResult) {
        this.matchingResult = matchingResult;
    }
}
