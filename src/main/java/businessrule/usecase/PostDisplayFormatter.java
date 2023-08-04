package businessrule.usecase;

import entity.Post;

import java.time.LocalDate;

/**
 * The "PostDisplayFormatter" class represents a formatted display of a post for presentation purposes.
 *
 * This class is responsible for formatting a post in a user-friendly way for displaying it to users
 * in the application's user interface. It encapsulates the essential information related to a post,
 */

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
