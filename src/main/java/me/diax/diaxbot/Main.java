package me.diax.diaxbot;

import me.diax.diaxbot.discord.DiaxDiscordBot;

/**
 * Created by comportment on 17/04/17.
 */
public class Main {

    public static void main(String[] args) {
        new Main().main();
    }

    public void main() {
        new DiaxDiscordBot().start();
    }
}
