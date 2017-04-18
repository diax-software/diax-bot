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

    public AbstractDiaxAudioBot() {
        currentTrack = null;
        queue = new LinkedBlockingQueue<>();
    }

    public BlockingQueue<DiaxAudioTrack> getQueue() {
        return queue;
    }

    public AbstractDiaxAudioBot setCurrentTrack(DiaxAudioTrack track) {
        currentTrack = track;
        return this;
    }

    public DiaxAudioTrack getCurrentTrack() {
        return currentTrack;
    }
}
