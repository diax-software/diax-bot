package me.diax.diaxbot.events;

import me.diax.diaxbot.IRCEvent;
import me.diax.diaxbot.bots.DiaxIRCBot;

import java.util.regex.Matcher;

/**
 * Created by NachtRaben on 4/17/2017.
 */
public class UserJoinEvent extends IRCEvent {
    
    private String user;
    private String client;
    private String address;
    private String channel;

    public UserJoinEvent(DiaxIRCBot ircBot, Matcher response) {
        super(ircBot, response);
        user = response.group(1);
        client = response.group(2);
        address = response.group(3);
        channel = response.group(4);
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

    @Override
    public String toString() {
        return "[JOIN]: User, " + user + ", joined " + channel;
    }
}
