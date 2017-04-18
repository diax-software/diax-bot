package me.diax.diaxbot.lib.objects;

/**
 * Created by comportment on 18/04/17.
 */
public class DiaxAuthor {

    private final String fullName;
    private final String simpleName;

    /**
     *
     * @param fullName TODO: Replace with some sort of UUID.
     * @param simpleName The user-shown name of the author.
     */
    public DiaxAuthor(String fullName, String simpleName) {
        this.fullName = fullName;
        this.simpleName= simpleName;
    }

    /**
     *
     * @return The uniquely identifying name of the author.
     */
    public String getFullName() {
        return fullName;
    }

    /**
     *
     * @return The simple name of the author.
     */
    public String getSimpleName() {
        return simpleName;
    }
}
