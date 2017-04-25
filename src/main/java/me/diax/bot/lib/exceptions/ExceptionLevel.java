package me.diax.bot.lib.exceptions;

/**
 * Created by comportment on 25/04/17.
 */
public enum ExceptionLevel {

    HIGH(3),
    MEDIUM(2),
    LOW(1),
    UNKNOWN(0);

    private final int level;

    ExceptionLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}