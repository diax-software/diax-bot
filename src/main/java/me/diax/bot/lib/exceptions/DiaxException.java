package me.diax.bot.lib.exceptions;

/**
 * Created by comportment on 25/04/17.
 */
public class DiaxException extends RuntimeException {

    private final Throwable throwable;
    private final String friendly;
    private final ExceptionLevel level;

    public DiaxException(ExceptionLevel level, Throwable throwable, String friendly) {
        this.level = level;
        this.throwable = throwable;
        this.friendly = friendly;
    }

    public ExceptionLevel getLevel() {
        return level;
    }

    public String getFriendly() {
        return friendly;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}