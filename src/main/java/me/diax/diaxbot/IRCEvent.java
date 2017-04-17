package me.diax.diaxbot;

import java.util.regex.Matcher;

/**
 * Created by NachtRaben on 4/16/2017.
 */
public abstract class IRCEvent {

    private IRCBot ircBot;
    private String ircResponse;

    public IRCEvent(IRCBot ircBot, Matcher response) {
        this.ircBot = ircBot;
        this.ircResponse = response.group(0);
    }

    public IRCBot getIrcBot() {
        return ircBot;
    }

    public String getIrcResponse() {
        return ircResponse;
    }

}
