package me.diax.diaxbot;

import me.diax.diaxbot.discord.DiaxDiscordBot;
import me.diax.diaxbot.lib.objects.DiaxAuthor;
import me.diax.diaxbot.lib.objects.DiaxMessage;

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
        DiaxMessage message = new DiaxMessage(new DiaxAuthor("Testing", "Test"), "Hello everybody!", new Timestamp(System.currentTimeMillis()));
    }
}