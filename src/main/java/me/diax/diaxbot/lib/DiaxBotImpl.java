package me.diax.diaxbot.lib;

import me.diax.diaxbot.lib.exceptions.BotStartException;
import me.diax.diaxbot.lib.exceptions.BotStopException;

/**
 * Created by comportment on 18/04/17.
 */
public interface DiaxBotImpl {

    /**
     * Starts an instance of the bot.
     *
     * @return The instance of the bot that was started, useful for chaining.
     * @throws BotStartException If the bot could not be started.
     */
    AbstractDiaxBot start() throws BotStartException;

    /**
     * Stops the instance of the bot.
     *
     * @return The instance of the bot that was stopped, useful for chaining.
     * @throws BotStopException If the could could not be stopped.
     */
    AbstractDiaxBot stop() throws BotStopException;
}