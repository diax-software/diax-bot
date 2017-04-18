package me.diax.diaxbot.lib.objects;

import java.util.Date;

/**
 * Created by comportment on 18/04/17.
 */
public class DiaxTimestamp extends Date {

    private static long DIAX_EPOCH = 1483228800000L;

    public DiaxTimestamp(long time) {
        super(time);
    }

    public static DiaxTimestamp getCurrentTimestamp() {
        return new DiaxTimestamp(System.currentTimeMillis() + DIAX_EPOCH);
    }
}
