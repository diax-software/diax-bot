package me.diax.bot.bots.offline;

import me.diax.bot.lib.AbstractDiaxBot;
import me.diax.bot.lib.objects.DiaxChannel;

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
    public AbstractDiaxBot messageTo(DiaxChannel channel, String message) {
        System.out.println(channel.getName() + ": " + message);
        return this;
    }
}
