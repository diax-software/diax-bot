package me.diax.bot.lib.exceptions;

import java.io.IOException;

/**
 * Created by comportment on 18/04/17.
 */
public class BotStartException extends IOException {

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