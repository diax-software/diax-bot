package me.diax.diaxbot.lib.objects;

/**
 * Created by comportment on 18/04/17.
 */
public class DiaxMessage {

    private DiaxAuthor author;
    private String content;
    private DiaxTimestamp timestamp;

    public DiaxMessage(DiaxAuthor author, String content, DiaxTimestamp timestamp) {
        this.author = author;
        this.content = content;
        this.timestamp = timestamp;
    }

    public DiaxAuthor getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public DiaxTimestamp getTimestamp() {
        return timestamp;
    }
}