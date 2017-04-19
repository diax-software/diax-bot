package me.diax.bot.lib.objects;

/**
 * Created by Comportment on 19/04/17.
 * <p>
 * Can't other people do this?
 */
public class DiaxChannel {

    private final String name;

    /**
     * @param name The name of the channel.
     */
    public DiaxChannel(String name) {
        this.name = name;
    }

    /**
     * @return The name of the channel.
     */
    public String getName() {
        return name;
    }
}
