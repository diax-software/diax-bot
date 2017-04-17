package me.diax.diaxbot.events;

import me.diax.diaxbot.bots.DiaxIRCBot;
import me.diax.diaxbot.IRCEvent;

import java.util.regex.Matcher;

/**
 * Created by NachtRaben on 4/16/2017.
 */
public class ChannelMessageEvent extends IRCEvent {
    public ChannelMessageEvent(DiaxIRCBot ircBot, Matcher response) {
        super(ircBot, response);
    }
}
