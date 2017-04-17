package me.diax.diaxbot.events;

import me.diax.diaxbot.IRCEvent;
import me.diax.diaxbot.bots.DiaxIRCBot;

import java.util.regex.Matcher;

/**
 * Created by NachtRaben on 4/17/2017.
 */
public class UserLeaveEvent extends IRCEvent {

    private String user;
    private String client;
    private String address;
    private String reason;

    public UserLeaveEvent(DiaxIRCBot ircBot, Matcher response) {
        super(ircBot, response);
        user = response.group(1);
        client = response.group(2);
        address = response.group(3);
        reason = response.group(4);
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

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "[QUIT]: User quit, " + user + ", for reason: " + reason;
    }
}
