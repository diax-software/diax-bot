package me.diax.bot.lib.objects;

import java.sql.Timestamp;

/**
 * Created by Comportment on 18/04/17.
 *
 * A global message object.
 */
public class DiaxMessage implements Comparable<DiaxMessage> {

    private final DiaxAuthor author;
    private final String content;
    private final Timestamp timestamp;
    private final DiaxChannel channel;

    /**
     * Constructor to build a message, it must have these values.
     *
     * @param author The sender of the message.
     * @param content The message's content.
     * @param timestamp The time the message was sent at.
     */
    public DiaxMessage(DiaxAuthor author, String content, Timestamp timestamp, DiaxChannel channel) {
        this.author = author;
        this.content = content;
        this.timestamp = timestamp;
        this.channel = channel;
    }

    /**
     *
     * @return The author of the message.
     */
    public DiaxAuthor getAuthor() {
        return author;
    }

    /**
     *
     * @return The content of the message.
     */
    public String getContent() {
        return content;
    }

    /**
     *
     * @return The timestamp the message was sent at.
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * @return The message the channel has been sent in.
     */
    public DiaxChannel getChannel() {
        return channel;
    }

    /**
     * Method overridden from {@link Comparable}
     *
     * @param that The other message to compare to.
     * @return An int representing the outcome of the comparison.
     */
    @Override
    public int compareTo(DiaxMessage that) {
        return this.getTimestamp().compareTo(that.getTimestamp());
    }
}