package me.diax.bot;

import me.diax.bot.bots.discord.DiaxDiscordBot;
import me.diax.bot.lib.AbstractDiaxBot;

/**
 * Created by comportment on 17/04/17.
 *
 *
 */
public class Main {

    public static void main(String[] args) throws Exception {
        new Main().main();
    }

    private void main() throws Exception {
        AbstractDiaxBot bot = new DiaxDiscordBot().start();
    }
}