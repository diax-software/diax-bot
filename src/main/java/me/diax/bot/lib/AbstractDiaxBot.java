package me.diax.bot.lib;

import me.diax.bot.lib.objects.DiaxChannel;
import me.diax.bot.lib.objects.DiaxMessage;

/**
 * Created by Comportment on 17/04/17.
 *
 * An abstract class for bots that can use audio commands.
 */
public abstract class AbstractDiaxBot implements DiaxBotImpl {

    /**
     * Boolean value that represents if the bot is started or not.
     */
    private boolean started;

    /**
     *
     * @return If this instance of the bot has been started.
     */
    public boolean hasStarted() {
        return started;
    }

    /**
     *
     * @param started A boolean to set the bot started or not.
     * @return The instance of the bot that the started value was set for, useful for chaining.
     */
    public AbstractDiaxBot setStarted(boolean started) {
        this.started = started;
        return this;
    }

    /**
     * A method to send messages to channels.
     *
     * @param channel The channel to send the message to.
     * @param message The message to send to the channel.
     * @return The instance of the bot that sent the message, useful for chaining.
     */
    @Override
    public AbstractDiaxBot messageTo(DiaxChannel channel, DiaxMessage message) {
        messageTo(channel, message.getContent());
        return this;
    }
}