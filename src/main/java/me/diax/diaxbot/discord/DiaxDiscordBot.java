package me.diax.diaxbot.discord;

import me.diax.diaxbot.lib.AbstractDiaxAudioBot;

/**
 * Created by comportment on 17/04/17.
 */
public class DiaxDiscordBot extends AbstractDiaxAudioBot {

    @Override
    public DiaxDiscordBot start() {
        System.out.println("Starting...");
        return this;
    }

    @Override
    public DiaxDiscordBot stop() {
        System.out.println("Stopping...");
        return this;
    }
}
