package me.diax.bot.lib.bot;

import me.diax.bot.lib.DiaxChannel;
import me.diax.bot.lib.DiaxMessage;

/**
 * Created by Comportment on 17/04/17.
 *
 * An abstract class for bots that can use audio commands.
 */
public abstract class DiaxBotImpl implements DiaxBot {

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
    public void setStarted(boolean started) {
        this.started = started;
    }

    /**
     * A method to send messages to channels.
     *
     * @param channel The channel to send the message to.
     * @param message The message to send to the channel.
     * @return The instance of the bot that sent the message, useful for chaining.
     */
    @Override
    public void messageTo(DiaxChannel channel, DiaxMessage message) throws Exception {
        messageTo(channel, message.getContent());
    }
}