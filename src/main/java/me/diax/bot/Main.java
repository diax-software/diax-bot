package me.diax.bot;

import me.diax.bot.bots.DiaxOfflineBot;
import me.diax.bot.lib.objects.DiaxAuthor;
import me.diax.bot.lib.objects.DiaxChannel;
import me.diax.bot.lib.objects.DiaxMessage;

import java.sql.Timestamp;

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
        DiaxAuthor author = new DiaxAuthor("sadas", "a");
        DiaxChannel channel = new DiaxChannel("nope");
        DiaxMessage message = new DiaxMessage(new DiaxAuthor("Testing", "Test"), "<>ping", new Timestamp(System.currentTimeMillis()));
        new DiaxOfflineBot().start().messageTo(channel, message);
    }
}