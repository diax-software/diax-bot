package me.diax.diaxbot.events;

import me.diax.diaxbot.bots.DiaxIRCBot;
import me.diax.diaxbot.IRCEvent;

import java.util.regex.Matcher;

/**
 * Created by NachtRaben on 4/16/2017.
 */
public class SystemEvent extends IRCEvent {

    private String server;
    private int code;
    private String message;

    public SystemEvent(DiaxIRCBot ircBot, Matcher response) {
        super(ircBot, response);
        server = response.group(1);
        code = Integer.parseInt(response.group(2));
        message = response.group(3);
    }

    public String getServer() {
        return server;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
