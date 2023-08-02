package attorneygetrecommendation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class MatchingResult {

    @Id
    @GeneratedValue
    private int resultId;
    @OneToMany
    private List<Matching> matchingResult;

    public MatchingResult() {
    }

    public MatchingResult(List<Matching> matchingResult) {this.matchingResult = matchingResult;}

    public List<Matching> getMatchingResult() {return matchingResult;}

    public void setMatchingResult(List<Matching> matchingResult) {this.matchingResult = matchingResult;}
}

