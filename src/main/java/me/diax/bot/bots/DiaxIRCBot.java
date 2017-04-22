package me.diax.bot.bots;

import me.diax.bot.lib.ComponentProvider;
import me.diax.bot.lib.bot.AbstractDiaxBot;
import me.diax.bot.lib.command.DiaxCommandProvider;
import me.diax.bot.lib.exceptions.BotStartException;
import me.diax.bot.lib.objects.DiaxAuthor;
import me.diax.bot.lib.objects.DiaxChannel;
import me.diax.bot.lib.objects.DiaxMessage;
import org.jibble.pircbot.PircBot;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.sql.Timestamp;

/**
 * Created by Comportment on 19/04/17.
 * <p>
 * Lets make this pretty again.
 */
@Singleton
public class DiaxIRCBot extends AbstractDiaxBot {

    private final DiaxCommandProvider handler;
    private final ComponentProvider provider;
    private final String prefix;
    private PircBot bot;

    @Inject
    public DiaxIRCBot(ComponentProvider provider, DiaxCommandProvider handler, @Named("prefix") String prefix) {
        this.handler = handler;
        this.provider = provider;
        this.prefix = prefix;
    }

    @Override
    public AbstractDiaxBot start() throws Exception {
        if (this.hasStarted()) throw new BotStartException("Bot has already started.");
        bot = new PircBot() {
            @Override
            protected void onMessage(String channel, String sender, String login, String hostname, String message) {
                if (!message.startsWith(prefix)) return;
                DiaxMessage dmsg = new DiaxMessage(
                        new DiaxAuthor(hostname, sender),
                        message,
                        new Timestamp(System.currentTimeMillis()),
                        new DiaxChannel(channel, channel)
                );
                handler.execute(provider.getInstance(DiaxIRCBot.class), dmsg);
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
        if (!this.hasStarted()) throw new BotStartException("Bot has not started.");
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