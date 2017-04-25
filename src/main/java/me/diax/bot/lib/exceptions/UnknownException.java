package me.diax.bot.lib.exceptions;

/**
 * Created by comportment on 25/04/17.
 */
public class UnknownException extends DiaxException {

    public UnknownException(Throwable throwable) {
        super(ExceptionLevel.UNKNOWN, throwable, "Unknown exception.");
    }
}
