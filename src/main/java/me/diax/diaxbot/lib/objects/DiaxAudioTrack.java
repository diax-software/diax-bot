package me.diax.diaxbot.lib.objects;

/**
 * Created by comportment on 18/04/17.
 */
public class DiaxAudioTrack {

    private final String trackName;
    private final String trackUrl;
    private final String trackAuthor;

    public DiaxAudioTrack(String trackName, String trackUrl, String trackAuthor) {
        this.trackName = trackName;
        this.trackUrl = trackUrl;
        this.trackAuthor = trackAuthor;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getTrackUrl() {
        return trackUrl;
    }

    public String getTrackAuthor() {
        return trackAuthor;
    }
}