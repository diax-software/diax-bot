package me.diax.diaxbot.discord;

import me.diax.diaxbot.lib.AbstractDiaxBot;

/**
 * Created by comportment on 17/04/17.
 */
public class DiaxDiscordBot extends AbstractDiaxBot {

    @Override
    public DiaxDiscordBot start() {
        System.out.println("Starting...");
        return this;
    }

    @Override
    public DiaxDiscordBot stop() {
        return this;
    }
}
