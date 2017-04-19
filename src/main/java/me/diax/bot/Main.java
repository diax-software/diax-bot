package me.diax.bot;

import me.diax.bot.bots.discord.DiaxDiscordBot;
import me.diax.bot.lib.AbstractDiaxBot;
import me.diax.bot.lib.command.DiaxCommandHandler;

/**
 * Created by comportment on 17/04/17.
 *
 *
 */
public class Main {

    private static final DiaxCommandHandler handler = new DiaxCommandHandler();

    public static void main(String[] args) throws Exception {
        new Main().main();
    }

    public static DiaxCommandHandler getHandler() {
        return handler;
    }

    private void main() throws Exception {
        AbstractDiaxBot bot = new DiaxDiscordBot().start();
    }
}