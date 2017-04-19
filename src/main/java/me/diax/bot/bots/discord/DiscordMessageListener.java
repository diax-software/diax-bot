package me.diax.bot.bots.discord;

import me.diax.bot.lib.command.DiaxCommandHandler;
import me.diax.bot.lib.objects.DiaxAuthor;
import me.diax.bot.lib.objects.DiaxChannel;
import me.diax.bot.lib.objects.DiaxMessage;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.sql.Timestamp;

/**
 * Created by comportment on 19/04/17.
 */
public class DiscordMessageListener extends ListenerAdapter {

    private final DiaxDiscordBot bot;

    public DiscordMessageListener(DiaxDiscordBot bot) {
        this.bot = bot;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        DiaxMessage message = new DiaxMessage(
                new DiaxAuthor(
                        event.getAuthor().getAvatarId(),
                        event.getAuthor().getName()),
                event.getMessage().getRawContent(),
                new Timestamp(1000 * event.getMessage().getCreationTime().toEpochSecond()),
                new DiaxChannel(event.getChannel().getId(), event.getChannel().getName()));
        new DiaxCommandHandler().execute(bot, message);

    }
}
