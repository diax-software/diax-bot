package me.diax.bot.lib.exceptions;

/**
 * Created by comportment on 18/04/17.
 */
public class BotStopException extends RuntimeException {

    public BotStopException() {
        super();
    }

    public BotStopException(String message) {
        super(message);
    }

    public BotStopException(Throwable throwable) {
        super(throwable);
    }

    public BotStopException(String message, Throwable throwable) {
        super(message, throwable);
    }
}