package me.diax.bot.lib.exceptions;

/**
 * Created by Comportment on 18/04/17.
 * <p>
 * All the exceptions.
 */
public class NotEnoughArgsException extends DiaxException {

    public NotEnoughArgsException(Throwable throwable) {
        super(ExceptionLevel.LOW, throwable, "Not enough arguments were supplied.");
    }
}