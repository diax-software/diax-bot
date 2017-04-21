package me.diax.bot.lib;

import com.knockturnmc.api.util.NamedProperties;
import com.knockturnmc.api.util.Property;

/**
 * Created by Comportment on 21/04/17.
 * <p>
 * Thanking Knockturn once again for an amazing lib.
 */
public final class DiaxProperties extends NamedProperties {

    @Property(value = "prefix", defaultvalue = "<<")
    private String prefix;

    @Property(value = "discord_token")
    private String discordToken;

    public String getPrefix() {
        return prefix;
    }

    public String getDiscordToken() {
        return discordToken;
    }
}