package me.diax.diaxbot.events;

import me.diax.diaxbot.bots.DiaxIRCBot;
import me.diax.diaxbot.IRCEvent;

import java.util.regex.Matcher;

/**
 * Created by NachtRaben on 4/16/2017.
 */
public class ChannelMessageEvent extends IRCEvent {

    private String user;
    private String client;
    private String address;
    private String channel;
    private String message;
    
    public ChannelMessageEvent(DiaxIRCBot ircBot, Matcher response) {
        super(ircBot, response);
        user = response.group(1);
        client = response.group(2);
        address = response.group(3);
        channel = response.group(4);
        message = response.group(5);
    }

    public String getUser() {
        return user;
    }

    public String getClient() {
        return client;
    }

    public String getAddress() {
        return address;
    }

    public String getChannel() {
        return channel;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "[MESSAGE][" + channel + "]" + user + ": " + message;
    }
}
