package me.diax.bot.lib.bot;

import me.diax.bot.lib.objects.DiaxAudioTrack;

import java.util.concurrent.BlockingQueue;

/**
 * Created by comportment on 23/04/17.
 */
public interface DiaxAudioBot extends DiaxBot {

    /**
     * @return The current queue of the music bot, sometimes null.
     */
    BlockingQueue<DiaxAudioTrack> getQueue();

    /**
     * @return The {@link DiaxAudioTrack} currently playing.
     */
    DiaxAudioTrack getCurrentTrack();

    /**
     * @param track The track to set as currently playing.
     * @return An instance of the bot, useful for chaining.
     */
    void setCurrentTrack(DiaxAudioTrack track);
}
