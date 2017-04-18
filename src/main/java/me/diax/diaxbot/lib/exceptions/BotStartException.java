package me.diax.diaxbot.lib.exceptions;

/**
 * Created by comportment on 18/04/17.
 */
public class BotStartException extends RuntimeException {

    public BotStartException() {
        super();
    }

    public BotStartException(String message) {
        super(message);
    }

    public BotStartException(Throwable throwable) {
        super(throwable);
    }

    public BotStartException(String message, Throwable throwable) {
        super(message, throwable);
    }
}