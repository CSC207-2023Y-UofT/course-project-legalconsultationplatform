package businessrule.usecase.util;

import java.util.List;

/**
 * This class represents the result of the matching process.
 * It contains a list of `Matching` objects that represent the matches between questions and attorneys.
 */
public class MatchingResult {
    private final List<Matching> matchingResult;

    public MatchingResult(List<Matching> matchingResult) {this.matchingResult = matchingResult;}

    public List<Matching> getMatchingResult() {return matchingResult;}
}

