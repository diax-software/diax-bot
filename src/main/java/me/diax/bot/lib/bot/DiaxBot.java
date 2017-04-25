package me.diax.bot.lib.bot;

import me.diax.bot.lib.DiaxChannel;
import me.diax.bot.lib.DiaxMessage;
import me.diax.bot.lib.exceptions.BotStartException;
import me.diax.bot.lib.exceptions.BotStopException;

/**
 * Created by Comportment on 18/04/17.
 *
 * Does anyone read these? \o/
 */
public interface DiaxBot {

    /**
     * Starts an instance of the bot.
     *
     * @return The instance of the bot that was started, useful for chaining.
     * @throws BotStartException If the bot could not be started.
     */
    void start() throws Exception;

    /**
     * Stops the instance of the bot.
     *
     * @return The instance of the bot that was stopped, useful for chaining.
     * @throws BotStopException If the could could not be stopped.
     */
    void stop() throws Exception;

    /**
     * @return The instance of the bot, useful for chaining.
     */
    void messageTo(DiaxChannel channel, DiaxMessage message);

    /**
     * @return The instance of the bot, useful for chaining.
     */
    void messageTo(DiaxChannel channel, String message);
}