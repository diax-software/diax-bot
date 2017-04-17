package me.diax.diaxbot;

import me.diax.diaxbot.bots.DiaxIRCBot;

/**
 * \o/
 */
public class Main {

    private static final String IDENTIFIER = "DiaxIRC";

    public static void main(String[] args) throws Exception {
        DiaxIRCBot test = new DiaxIRCBot("irc.domirc.net", IDENTIFIER, IDENTIFIER);
        test.joinChannel("diax.me", "##testing");
    }
}