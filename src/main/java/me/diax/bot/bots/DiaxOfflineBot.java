package me.diax.bot.bots;

import me.diax.bot.lib.AbstractDiaxBot;
import me.diax.bot.lib.objects.DiaxChannel;
import me.diax.bot.lib.objects.DiaxMessage;

import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Created by Comportment on 19/04/17.
 * <p>
 * no u
 */
public class DiaxOfflineBot extends AbstractDiaxBot {

    @Override
    public AbstractDiaxBot start() throws Exception {
        System.out.println("Starting...");
        System.out.println("Started.");
        return this;
    }

    @Override
    public AbstractDiaxBot stop() throws Exception {
        System.out.println("Stopping...");
        System.out.println("Stopped.");
        return this;
    }

    @Override
    public AbstractDiaxBot messageTo(DiaxChannel channel, DiaxMessage message) {
        System.out.println(DateTimeFormatter.ofPattern("[HH:mm:ss]").format(OffsetTime.ofInstant(message.getTimestamp().toInstant(), ZoneId.systemDefault())) + " " + channel.getName() + ": " + message.getContent());
        return this;
    }
}
