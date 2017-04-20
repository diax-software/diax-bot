package me.diax.bot.bots.irc;

import me.diax.bot.Main;
import me.diax.bot.lib.AbstractDiaxBot;
import me.diax.bot.lib.objects.DiaxAuthor;
import me.diax.bot.lib.objects.DiaxChannel;
import me.diax.bot.lib.objects.DiaxMessage;
import org.jibble.pircbot.PircBot;

import java.sql.Timestamp;

/**
 * Created by Comportment on 19/04/17.
 * <p>
 * Lets make this pretty again.
 */
public class DiaxIRCBot extends AbstractDiaxBot {

    private static PircBot bot;

    @Override
    public AbstractDiaxBot start() throws Exception {
        bot = new PircBot() {

            @Override
            protected void onMessage(String channel, String sender, String login, String hostname, String message) {
                DiaxMessage dmsg = new DiaxMessage(
                        new DiaxAuthor(hostname, sender),
                        message,
                        new Timestamp(System.currentTimeMillis()),
                        new DiaxChannel(channel, channel)
                );
                Main.getHandler().execute(new DiaxIRCBot(), dmsg);
            }
        };
        bot.connect("irc.domirc.net", 6667);
        bot.setAutoNickChange(true);
        bot.changeNick("Diax");
        bot.joinChannel("#diax.me");
        return this;
    }

    @Override
    public AbstractDiaxBot stop() throws Exception {
        bot.dispose();
        bot.disconnect();
        bot = null;
        return this;
    }

    @Override
    public AbstractDiaxBot messageTo(DiaxChannel channel, String message) throws Exception {
        bot.sendMessage(channel.getIdentifier(), message);
        return this;
    }
}