package businessrule.usecase.util;

import entity.Post;

import java.time.LocalDate;

public class PostDisplayFormatter {
    private final String postText;
    private final boolean isClient;
    private final String name;
    private final LocalDate createAt;

    public PostDisplayFormatter(String postText, boolean isClient, String name, LocalDate createAt) {
        this.postText = postText;
        this.isClient = isClient;
        this.name = name;
        this.createAt = createAt;
    }

    public String getPostText() {
        return postText;
    }

    public boolean isClient() {
        return isClient;
    }

    public String getName() {
        return name;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }
}
