package me.diax.bot.bots;

import com.mashape.unirest.http.Unirest;
import me.diax.bot.Main;
import me.diax.bot.lib.bot.AbstractDiaxAudioBot;
import me.diax.bot.lib.command.DiaxCommandProvider;
import me.diax.bot.lib.exceptions.BotStartException;
import me.diax.bot.lib.exceptions.BotStopException;
import me.diax.bot.lib.objects.DiaxAuthor;
import me.diax.bot.lib.objects.DiaxChannel;
import me.diax.bot.lib.objects.DiaxMessage;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.security.auth.login.LoginException;
import java.sql.Timestamp;
import java.util.Arrays;

/**
 * Created by comportment on 17/04/17.
 * <p>
 * gr8 bot 100% working would use again.
 */
@Singleton
public class DiaxDiscordBot extends AbstractDiaxAudioBot {

    private static JDA[] SHARDS;
    private static String TOKEN;
    private final String prefix;
    private DiaxCommandProvider handler;

    @Inject
    public DiaxDiscordBot(DiaxCommandProvider handler, @Named("prefix") String prefix, @Named("discord_token") String token) {
        this.handler = handler;
        this.prefix = prefix;
        TOKEN = token;
    }

    @Override
    public DiaxDiscordBot start() throws Exception {
        if (this.hasStarted()) throw new BotStartException("Bot has already started.");
        System.out.println("Starting...");
        int amount = getRecommendedShards();
        SHARDS = new JDA[amount >= 3 ? amount : 1];
        for (int i = 0; i < SHARDS.length; i++) {
            JDA jda = null;
            try {
                JDABuilder builder = new JDABuilder(AccountType.BOT)
                        .addEventListener(new ListenerAdapter() {
                            @Override
                            public void onMessageReceived(MessageReceivedEvent event) {
                                DiaxMessage dmsg = new DiaxMessage(
                                        new DiaxAuthor(event.getAuthor().getAsMention(), event.getAuthor().getName()),
                                        event.getMessage().getRawContent(),
                                        new Timestamp(System.currentTimeMillis()),
                                        new DiaxChannel(event.getChannel().getId(), event.getChannel().getName())
                                );
                                handler.execute(new Main().getInstance(DiaxIRCBot.class), dmsg);
                            }
                        })
                        .setAudioEnabled(true)
                        .setGame(Game.of(prefix + "help"))
                        .setToken(TOKEN)
                        .setStatus(OnlineStatus.ONLINE);
                if (SHARDS.length >= 3)
                    builder.useSharding(i, SHARDS.length);
                jda = builder.buildBlocking();
            } catch (LoginException | RateLimitedException | InterruptedException ignored) {
            }
            if (jda != null) {
                SHARDS[i] = jda;
            }
        }
        System.out.println("Started.");
        this.setStarted(true);
        return this;
    }

    @Override
    public DiaxDiscordBot stop() throws Exception {
        if (!this.hasStarted()) throw new BotStopException("Bot has already stopped.");
        System.out.println("Stopping...");
        Arrays.stream(SHARDS).forEach(shard -> {
            try {
                shard.shutdown(false);
            } catch (Exception ignored) {
            }
        });
        Arrays.asList(SHARDS).clear();
        System.out.println("Stopped.");
        this.setStarted(false);
        return this;
    }

    @Override
    public DiaxDiscordBot messageTo(DiaxChannel channel, String message) throws Exception {
        findChannel(Long.valueOf(channel.getIdentifier())).sendMessage(message).queue();
        return this;
    }

    private int getRecommendedShards() {
        try {

            return Unirest.get("https://discordapp.com/api/gateway/bot")
                    .header("Authorization", "Bot " + TOKEN)
                    .header("Content-Type", "application/json").asJson().getBody().getObject().getInt("shards");
        } catch (Exception e) {
            return -1;
        }
    }

    private TextChannel findChannel(long id) {
        return SHARDS[getShard(id)].getTextChannelById(id);
    }

    private int getShard(long guildID) {
        return (int) ((guildID >> 22) % SHARDS[0].getShardInfo().getShardTotal());
    }
}