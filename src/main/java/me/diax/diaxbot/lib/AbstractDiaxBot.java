package me.diax.diaxbot.lib;

import me.diax.diaxbot.lib.exceptions.BotStartException;

/**
 * Created by Comportment on 17/04/17.
 *
 * Needs 50000% more abstraction.exe
 */
public abstract class AbstractDiaxBot {

    private boolean started;

    public abstract AbstractDiaxBot start() throws BotStartException;
    public abstract AbstractDiaxBot stop();

    public boolean hasStarted() {
        return started;
    }

    public AbstractDiaxBot setStarted(boolean started) {
        this.started = started;
        return this;
    }
}