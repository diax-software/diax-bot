package me.diax.diaxbot;

import me.diax.diaxbot.bots.DiaxIRCBot;

/**
 * Created by Comportment on 16/04/17.
 */
public class Main {

    public static void main(String[] args) {
        new Main().main();
    }

    public void main() {
        DiaxIRCBot bot = new DiaxIRCBot("irc.domirc.net", "#diax.me", 6697);
    }
}