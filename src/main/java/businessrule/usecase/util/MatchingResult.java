package businessrule.usecase.util;

import java.util.List;

public class MatchingResult {
    private final List<Matching> matchingResult;

    public MatchingResult(List<Matching> matchingResult) {this.matchingResult = matchingResult;}

    public List<Matching> getMatchingResult() {return matchingResult;}
}

