package me.diax.bot;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.name.Names;
import com.knockturnmc.api.util.ConfigurationUtils;
import me.diax.bot.bots.DiaxDiscordBot;
import me.diax.bot.lib.ComponentProvider;
import me.diax.bot.lib.DiaxProperties;
import me.diax.bot.lib.bot.AbstractDiaxAudioBot;
import me.diax.bot.lib.command.DiaxCommandProvider;

import java.io.File;
import java.util.Arrays;
import java.util.Properties;

/**
 * Created by Comportment on 17/04/17.
 *
 * Better than the last version
 */
public final class Main implements ComponentProvider, Module {

    private final Injector injector;
    private final DiaxProperties properties;

    private Main() {
        Properties sys = System.getProperties();
        Properties config = new Properties();
        String[] fields = {"discord_token", "prefix"};
        Arrays.stream(fields).forEach(field -> sys.computeIfPresent(field, config::put));
        properties = ConfigurationUtils.loadConfiguration(
                this.getClass().getClassLoader(),
                "diax.properties",
                new File(System.getProperty("user.dir")),
                DiaxProperties.class);
        injector = Guice.createInjector(this);
    }

    public static void main(String[] args) throws Exception {
        new Main().main();
    }

    private void main() throws Exception {
        //AbstractDiaxBot bot = injector.getInstance(DiaxIRCBot.class).start();
        AbstractDiaxAudioBot bot2 = injector.getInstance(DiaxDiscordBot.class).start();
    }

    @Override
    public void configure(Binder binder) {
        binder.bind(ComponentProvider.class).toInstance(this);
        binder.bind(DiaxCommandProvider.class).to(DiaxCommandHandler.class);
        binder.bind(String.class).annotatedWith(Names.named("prefix")).toInstance(properties.getPrefix());
        binder.bind(String.class).annotatedWith(Names.named("discord_token")).toInstance(properties.getDiscordToken());
    }

    @Override
    public <T> T getInstance(Class<T> type) {
        return injector.getInstance(type);
    }
}