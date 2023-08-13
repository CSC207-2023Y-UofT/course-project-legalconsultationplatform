package businessrule.usecase.util;

import entity.Post;

import java.time.LocalDate;

public class PostDisplayFormatter {
    private final String postText;
    private final String userType;
    private final String name;
    private final LocalDate createAt;

    public PostDisplayFormatter(String postText,String userType, String name, LocalDate createAt) {
        this.postText = postText;
        this.userType = userType;

        this.name = name;
        this.createAt = createAt;
    }

    public String getPostText() {
        return postText;
    }

    public String getUserType() {
        return userType;
    }

    public String getName() {
        return name;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }
}
