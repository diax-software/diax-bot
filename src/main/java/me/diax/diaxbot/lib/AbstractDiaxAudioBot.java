package me.diax.diaxbot.lib;

import me.diax.diaxbot.lib.objects.DiaxAudioTrack;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by comportment on 18/04/17.
 *
 * An abstract class for bots that can use audio commands.
 */
public abstract class AbstractDiaxAudioBot extends AbstractDiaxBot {

    private DiaxAudioTrack currentTrack;
    private BlockingQueue<DiaxAudioTrack> queue;

    /**
     * Used to reset the queue and the current track when a new instance of an AudioBot is made.
     */
    public AbstractDiaxAudioBot() {
        currentTrack = null;
        queue = new LinkedBlockingQueue<>();
    }

    /**
     *
     * @return The current queue of the music bot, sometimes null.
     */
    public BlockingQueue<DiaxAudioTrack> getQueue() {
        return queue;
    }

    /**
     *
     * @param track The track to set as currently playing.
     * @return An instance of the bot, useful for chaining.
     */
    public AbstractDiaxAudioBot setCurrentTrack(DiaxAudioTrack track) {
        currentTrack = track;
        return this;
    }

    /**
     *
     * @return The current playing track.
     */
    public DiaxAudioTrack getCurrentTrack() {
        return currentTrack;
    }
}
