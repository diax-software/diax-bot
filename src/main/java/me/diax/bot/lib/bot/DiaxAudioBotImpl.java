package me.diax.bot.lib.bot;

import me.diax.bot.lib.DiaxAudioTrack;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by comportment on 18/04/17.
 *
 * An abstract class for bots that can use audio commands.
 */
public abstract class DiaxAudioBotImpl extends DiaxBotImpl implements DiaxAudioBot {

    private DiaxAudioTrack currentTrack;
    private BlockingQueue<DiaxAudioTrack> queue;

    /**
     * Used to reset the queue and the current track when a new instance of an AudioBot is made.
     */
    public DiaxAudioBotImpl() {
        currentTrack = null;
        queue = new LinkedBlockingQueue<>();
    }

    public BlockingQueue<DiaxAudioTrack> getQueue() {
        return queue;
    }

    public DiaxAudioTrack getCurrentTrack() {
        return currentTrack;
    }

    public void setCurrentTrack(DiaxAudioTrack track) {
        currentTrack = track;
    }
}
