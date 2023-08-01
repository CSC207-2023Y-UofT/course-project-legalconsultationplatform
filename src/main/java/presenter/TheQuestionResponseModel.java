package presenter;

import com.objectdb.o.SLP;

import java.time.LocalDate;
import java.time.LocalTime;

public class TheQuestionResponseModel {
    int userId;
    LocalDate creationTime;

    public TheQuestionResponseModel(int userId, LocalDate creationTime) {
        this.userId = userId;
        this.creationTime = creationTime;
    }

    public int getUserId() {
        return userId;
    }
    public LocalDate getCreationTime() {
        return creationTime;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCreationTime(LocalDate creationTime) {
        this.creationTime = creationTime;
    }
}
