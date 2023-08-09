package businessrule.usecase.util;

import entity.Post;

import java.time.LocalDate;

/**
 * This class represents the formatted display information for a post.
 * It contains attributes such as the post text, whether it's from a client, the name of the poster,
 * and the creation date.
 */
public class PostDisplayFormatter {
    private final String postText; // The text content of the post
    private final boolean isClient; // Indicates if the post is from a client
    private final String name; // The name of the poster
    private final LocalDate createAt; // The creation date of the post

    /**
     * Constructs a `PostDisplayFormatter` object with the specified attributes.
     *
     * @param postText The text content of the post.
     * @param isClient Indicates if the post is from a client.
     * @param name The name of the poster.
     * @param createAt The creation date of the post.
     */
    public PostDisplayFormatter(String postText, boolean isClient, String name, LocalDate createAt) {
        this.postText = postText;
        this.isClient = isClient;
        this.name = name;
        this.createAt = createAt;
    }

    /**
     * Gets the text content of the post.
     *
     * @return The post text.
     */
    public String getPostText() {
        return postText;
    }

    /**
     * Checks if the post is from a client.
     *
     * @return `true` if the post is from a client, otherwise `false`.
     */
    public boolean isClient() {
        return isClient;
    }

    /**
     * Gets the name of the poster.
     *
     * @return The name of the poster.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the creation date of the post.
     *
     * @return The creation date.
     */
    public LocalDate getCreateAt() {
        return createAt;
    }
}