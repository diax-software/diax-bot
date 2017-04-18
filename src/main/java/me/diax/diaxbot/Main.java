package me.diax.diaxbot;

import me.diax.diaxbot.discord.DiaxDiscordBot;
import me.diax.diaxbot.lib.objects.DiaxMessage;
import me.diax.diaxbot.lib.objects.DiaxAuthor;
import me.diax.diaxbot.lib.objects.DiaxTimestamp;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

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
        DiaxMessage message = new DiaxMessage(new DiaxAuthor("Testing", "Test"), "Hello everybody!", new DiaxTimestamp(0L));
        System.out.println(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(DiaxTimestamp.getCurrentTimestamp()));
    }
}
