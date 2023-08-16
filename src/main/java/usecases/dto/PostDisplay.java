package usecases.dto;

import java.time.LocalDate;

/**
 * This class represents the formatted display information for a post.
 * It contains attributes such as the post text, whether it's from a client, the name of the poster,
 * and the creation date.
 */
public class PostDisplay {
    private final String postText;
    private final String userType;
    private final String name;
    private final LocalDate createAt;

    /**
     * Constructs a `PostDisplayFormatter` object with the specified attributes.
     *
     * @param postText The text content of the post.
     * @param userType Indicates the user type.
     * @param name The name of the poster.
     * @param createAt The creation date of the post.
     */
    public PostDisplay(String postText, String userType, String name, LocalDate createAt) {
        this.postText = postText;
        this.userType = userType;
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
     * Checks the user type of the post from.
     *
     * @return `true` if the post is from a client, otherwise `false`.
     */
    public String getUserType() {
        return userType;
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
