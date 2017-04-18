package me.diax.diaxbot.lib;

import me.diax.diaxbot.lib.exceptions.BotStartException;
import me.diax.diaxbot.lib.exceptions.BotStopException;

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
}