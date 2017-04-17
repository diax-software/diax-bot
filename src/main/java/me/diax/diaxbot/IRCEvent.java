package me.diax.diaxbot;

import me.diax.diaxbot.bots.DiaxIRCBot;

import java.util.regex.Matcher;

/**
 * Created by NachtRaben on 4/16/2017.
 */
public abstract class IRCEvent {

    private DiaxIRCBot ircBot;
    private String ircResponse;

    public IRCEvent(DiaxIRCBot ircBot, Matcher response) {
        this.ircBot = ircBot;
        this.ircResponse = response.group(0);
    }

    public DiaxIRCBot getIrcBot() {
        return ircBot;
    }

    public String getIrcResponse() {
        return ircResponse;
    }

}
