package me.diax.diaxbot;

import me.diax.diaxbot.bots.DiaxIRCBot;

/**
 * \o/
 */
public class Main {

    private static final String IDENTIFIER = "DiaxBOT";

    public static void main(String[] args) throws Exception {
        DiaxIRCBot bot = new DiaxIRCBot("irc.domirc.net", IDENTIFIER, IDENTIFIER);
        bot.joinChannel("#diax.me", "##testing").joinChannel("#diax.me");
    }
}