package me.diax.diaxbot.lib.objects;

/**
 * Created by comportment on 18/04/17.
 */
public class DiaxAuthor {

    private String fullName;
    private String simpleName;

    public DiaxAuthor(String fullName, String simpleName) {
        this.fullName = fullName;
        this.simpleName= simpleName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getSimpleName() {
        return simpleName;
    }
}
