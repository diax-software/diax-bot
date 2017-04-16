package me.diax.diaxbot;

/**
 * Created by Comportment on 16/04/17.
 *
 * Don't worry, if it breaks we can run to NachtRaben.
 */

public class Main {

    public static void main(String[] args) throws Exception {
        new DiaxIRCBot(6667, "irc.domirc.net", "##testing", "#diax.me");
    }
}