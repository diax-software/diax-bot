package me.diax.diaxbot.lib;

import me.diax.diaxbot.lib.exceptions.BotStartException;
import me.diax.diaxbot.lib.exceptions.BotStopException;

/**
 * Created by Comportment on 17/04/17.
 *
 * An abstract class for bots that can use audio commands.
 */
public abstract class AbstractDiaxBot {

    /**
     * Boolean value that represents if the bot is started or not.
     */
    private boolean started;

    /**
     * Starts an instance of the bot.
     *
     * @return The instance of the bot that was started, useful for chaining.
     * @throws BotStartException If the bot could not be started.
     */
    public abstract AbstractDiaxBot start() throws BotStartException;

    /**
     * Stops the instance of the bot.
     *
     * @return The instance of the bot that was stopped, useful for chaining.
     * @throws BotStopException If the could could not be stopped.
     */
    public abstract AbstractDiaxBot stop() throws BotStopException;

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
}