package me.diax.bot.lib.exceptions;

/**
 * Created by comportment on 18/04/17.
 */
public class BotStartException extends DiaxException {

    public BotStartException(Throwable throwable) {
        super(ExceptionLevel.HIGH, throwable, "Bot could not start.");
    }
}