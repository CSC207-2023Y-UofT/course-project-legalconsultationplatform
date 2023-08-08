package businessrule.usecase.util;

import java.time.LocalDate;

public class QuestionDisplayFormatter {
    private final String title;
    private final String type;
    private final LocalDate legalDeadline;
    private final boolean isClose;

    public QuestionDisplayFormatter(String title, String type, LocalDate legalDeadline, boolean isClose) {
        this.title = title;
        this.type = type;
        this.legalDeadline = legalDeadline;
        this.isClose = isClose;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public LocalDate getLegalDeadline() {
        return legalDeadline;
    }

    public boolean isClose() {return isClose;}
}
