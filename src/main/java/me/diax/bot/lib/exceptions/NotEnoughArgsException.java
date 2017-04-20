package me.diax.bot.lib.exceptions;

/**
 * Created by Comportment on 18/04/17.
 * <p>
 * All the exceptions.
 */
public class NotEnoughArgsException extends RuntimeException {

    public NotEnoughArgsException() {
        super();
    }

    public NotEnoughArgsException(String message) {
        super(message);
    }

    public NotEnoughArgsException(Throwable throwable) {
        super(throwable);
    }

    public NotEnoughArgsException(String message, Throwable throwable) {
        super(message, throwable);
    }
}