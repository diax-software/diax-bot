package me.diax.diaxbot;

import me.diax.diaxbot.bots.DiaxIRCBot;

/**
 * Created by NachtRaben on 4/17/2017.
 */
public abstract class IRCPatternHandler {

    public abstract boolean handle(DiaxIRCBot bot, String message);

}
