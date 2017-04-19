package me.diax.bot.lib;

import me.diax.bot.lib.exceptions.BotStartException;
import me.diax.bot.lib.exceptions.BotStopException;
import me.diax.bot.lib.objects.DiaxChannel;
import me.diax.bot.lib.objects.DiaxMessage;

/**
 * Created by Comportment on 18/04/17.
 *
 * Does anyone read these? \o/
 */
public interface DiaxBotImpl {

    /**
     * Starts an instance of the bot.
     *
     * @return The instance of the bot that was started, useful for chaining.
     * @throws BotStartException If the bot could not be started.
     */
    AbstractDiaxBot start() throws Exception;

    /**
     * Stops the instance of the bot.
     *
     * @return The instance of the bot that was stopped, useful for chaining.
     * @throws BotStopException If the could could not be stopped.
     */
    AbstractDiaxBot stop() throws Exception;

    /**
     * @return The instance of the bot, useful for chaining.
     */
    AbstractDiaxBot messageTo(DiaxChannel channel, DiaxMessage message);
}