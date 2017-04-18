package me.diax.bot;

import me.diax.bot.discord.DiaxDiscordBot;
import me.diax.bot.lib.command.DiaxCommandHandler;
import me.diax.bot.lib.objects.DiaxAuthor;
import me.diax.bot.lib.objects.DiaxMessage;

import java.sql.Timestamp;

/**
 * Created by comportment on 17/04/17.
 *
 *
 */
public class Main {

    public static void main(String[] args) {
        new Main().main();
    }

    private void main() {
        new DiaxDiscordBot().start().stop();
        DiaxMessage message = new DiaxMessage(new DiaxAuthor("Testing", "Test"), "<>ping", new Timestamp(System.currentTimeMillis()));
        new DiaxCommandHandler(message);
    }
}