package me.diax.diaxbot;

import me.diax.diaxbot.discord.DiaxDiscordBot;
import me.diax.diaxbot.lib.objects.DiaxAuthor;
import me.diax.diaxbot.lib.objects.DiaxMessage;
import me.diax.diaxbot.lib.objects.DiaxTimestamp;

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
        new DiaxDiscordBot().start();
        DiaxMessage message = new DiaxMessage(new DiaxAuthor("Testing", "Test"), "Hello everybody!", DiaxTimestamp.getCurrentTime());
    }
}
