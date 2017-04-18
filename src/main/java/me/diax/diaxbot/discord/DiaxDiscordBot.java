package me.diax.diaxbot.discord;

import me.diax.diaxbot.lib.AbstractDiaxAudioBot;
import me.diax.diaxbot.lib.exceptions.BotStartException;
import me.diax.diaxbot.lib.exceptions.BotStopException;

/**
 * Created by comportment on 17/04/17.
 *
 * gr8 bot 100% working would use again.
 */
public class DiaxDiscordBot extends AbstractDiaxAudioBot {

    @Override
    public DiaxDiscordBot start() throws BotStartException {
        if (this.hasStarted()) throw new BotStartException("Bot has already started.");
        System.out.println("Starting...");
        this.setStarted(true);
        return this;
    }

    @Override
    public DiaxDiscordBot stop() throws BotStopException {
        if (!this.hasStarted()) throw new BotStopException("Bot has already stopped.");
        System.out.println("Stopping...");
        this.setStarted(false);
        return this;
    }
}
