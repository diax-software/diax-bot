package me.diax.diaxbot.lib.objects;

import java.sql.Timestamp;

/**
 * Created by comportment on 18/04/17.
 */
public class DiaxMessage implements Comparable<DiaxMessage> {

    private DiaxAuthor author;
    private String content;
    private Timestamp timestamp;

    public DiaxMessage(DiaxAuthor author, String content, Timestamp timestamp) {
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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    @Override
    public int compareTo(DiaxMessage that) {
        return this.getTimestamp().compareTo(that.getTimestamp());
    }
}