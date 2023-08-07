package attorneygetrecommendation;

import java.util.List;

public class MatchingResult {
    private int resultId;
    private List<Matching> matchingResult;

    public MatchingResult(List<Matching> matchingResult) {this.matchingResult = matchingResult;}

    public List<Matching> getMatchingResult() {return matchingResult;}

    public void setMatchingResult(List<Matching> matchingResult) {this.matchingResult = matchingResult;}
}

