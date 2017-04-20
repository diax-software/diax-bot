package me.diax.bot.lib.objects;

/**
 * Created by Comportment on 18/04/17.
 *
 *
 */
public class DiaxAudioTrack {

    private final String trackName;
    private final String trackUrl;
    private final String trackAuthor;
    private final int length;

    /**
     * Constructor for a DiaxAudioTrack
     *
     * @param trackName The name of the track.
     * @param trackUrl The direct url to the track.
     * @param trackAuthor The author of the track.
     * @param length The length of the track in milliseconds.
     */
    public DiaxAudioTrack(String trackName, String trackUrl, String trackAuthor, int length) {
        this.trackName = trackName;
        this.trackUrl = trackUrl;
        this.trackAuthor = trackAuthor;
        this.length = length;
    }

    /**
     *
     * @return The name of the track.
     */
    public String getTrackName() {
        return trackName;
    }

    /**
     *
     * @return The url of the track.
     */
    public String getTrackUrl() {
        return trackUrl;
    }

    /**
     *
     * @return The author of the track.
     */
    public String getTrackAuthor() {
        return trackAuthor;
    }

    /**
     *
     * @return The length of the track in milliseconds.
     */
    public int getLength() {
        return length;
    }
}