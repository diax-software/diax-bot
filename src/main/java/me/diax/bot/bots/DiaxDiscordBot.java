package me.diax.bot.bots;

import com.mashape.unirest.http.Unirest;
import me.diax.bot.lib.ComponentProvider;
import me.diax.bot.lib.bot.DiaxAudioBotImpl;
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
public class DiaxDiscordBot extends DiaxAudioBotImpl {

    private static JDA[] SHARDS;
    private static String TOKEN;
    private final String prefix;
    private final DiaxCommandProvider handler;
    private final ComponentProvider provider;

    @Inject
    public DiaxDiscordBot(DiaxCommandProvider handler, ComponentProvider provider, @Named("prefix") String prefix, @Named("discord_token") String token) {
        this.handler = handler;
        this.prefix = prefix;
        this.provider = provider;
        TOKEN = token;
    }

    private static int getRecommendedShards() {
        try {

            return Unirest.get("https://discordapp.com/api/gateway/bot")
                    .header("Authorization", "Bot " + TOKEN)
                    .header("Content-Type", "application/json").asJson().getBody().getObject().getInt("shards");
        } catch (Exception e) {
            return -1;
        }
    }

    private static TextChannel findChannel(long id) {
        System.out.println(id);
        return SHARDS[getShard(id)].getTextChannelById(id);
    }

    private static int getShard(long guildID) {
        JDA.ShardInfo info = SHARDS[0].getShardInfo();
        int amount = (info == null ? 1 : info.getShardTotal());
        return (int) ((guildID >> 22) % amount);
    }

    @Override
    public void start() throws Exception {
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
                                if (!event.getMessage().getRawContent().startsWith(prefix)) return;
                                DiaxMessage dmsg = new DiaxMessage(
                                        new DiaxAuthor(event.getAuthor().getAsMention(), event.getAuthor().getName()),
                                        event.getMessage().getRawContent(),
                                        new Timestamp(System.currentTimeMillis()),
                                        new DiaxChannel(event.getChannel().getId(), event.getChannel().getName())
                                );
                                handler.execute(provider.getInstance(DiaxDiscordBot.class), dmsg);
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
    }

    @Override
    public void stop() throws Exception {
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
    }

    @Override
    public void messageTo(DiaxChannel channel, String message) throws Exception {
        findChannel(Long.valueOf(channel.getIdentifier())).sendMessage(message).queue();
    }
}