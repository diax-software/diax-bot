package me.diax.bot.lib;

/**
 * Created by Comportment on 19/04/17.
 * <p>
 * Can't other people do this?
 */
public class DiaxChannel {

    private final String identifier;
    private final String name;

    /**
     * @param name The name of the channel.
     */
    public DiaxChannel(String identifier, String name) {
        this.identifier = identifier;
        this.name = name;
    }

    /**
     * @return The unique identifier for the channel.
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * @return The name of the channel.
     */
    public String getName() {
        return name;
    }
}
