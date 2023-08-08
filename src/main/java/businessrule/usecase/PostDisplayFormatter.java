package adapter.presenter;

import entity.Post;

import java.time.LocalDate;

/**
 * This class represents a formatted display of a post for presentation purposes.
 *
 * This class is responsible for formatting a post in a user-friendly way for displaying it to users
 * in the application's user interface. It encapsulates the essential information related to a post.
 */
public class PostDisplayFormatter {
    private final String postText;
    private final boolean isClient;
    private final String name;
    private final LocalDate createAt;

    /**
     * Constructs a new PostDisplayFormatter object with the provided information.
     *
     * @param postText The text content of the post.
     * @param isClient Indicates whether the post was made by a client.
     * @param name The name associated with the post author.
     * @param createAt The date when the post was created.
     */
    public PostDisplayFormatter(String postText, boolean isClient, String name, LocalDate createAt) {
        this.postText = postText;
        this.isClient = isClient;
        this.name = name;
        this.createAt = createAt;
    }

    /**
     * Get the text content of the post.
     *
     * @return The post's text content.
     */
    public String getPostText() {
        return postText;
    }

    /**
     * Check if the post was made by a client.
     *
     * @return true if the post was made by a client, false otherwise.
     */
    public boolean isClient() {
        return isClient;
    }

    /**
     * Get the name associated with the post author.
     *
     * @return The author's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the date when the post was created.
     *
     * @return The creation date of the post.
     */
    public LocalDate getCreateAt() {
        return createAt;
    }
}