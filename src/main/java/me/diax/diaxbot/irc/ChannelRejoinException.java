package me.diax.diaxbot.irc;

/**
 * Created by comportment on 17/04/17.
 */
public class ChannelRejoinException extends RuntimeException {

    public ChannelRejoinException(String channel) {
        super(String.format("Already a member of %s!", channel));
    }
}