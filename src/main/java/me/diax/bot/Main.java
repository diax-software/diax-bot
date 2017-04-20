package me.diax.bot;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import me.diax.bot.bots.irc.DiaxIRCBot;
import me.diax.bot.lib.bot.AbstractDiaxBot;
import me.diax.bot.lib.providers.ComponentProvider;
import me.diax.bot.lib.providers.DiaxCommandProvider;

/**
 * Created by comportment on 17/04/17.
 *
 *
 */
public class Main implements ComponentProvider, Module {

    private final Injector injector;
    private final DiaxCommandHandler handler;

    public Main() {
        injector = Guice.createInjector(this);
        handler = injector.getInstance(DiaxCommandHandler.class);
    }

    public static void main(String[] args) throws Exception {
        new Main().main();
    }

    private void main() throws Exception {
        AbstractDiaxBot bot = injector.getInstance(DiaxIRCBot.class).start();
        //AbstractDiaxAudioBot bot2 = new DiaxDiscordBot().start();
    }

    @Override
    public void configure(Binder binder) {
        binder.bind(ComponentProvider.class).toInstance(this);
        binder.bind(DiaxCommandProvider.class.to(DiaxCommandHandler.class);
        binder.bind(DiaxCommandHandler.class).toInstance(handler);
    }

    @Override
    public <T> T getInstance(Class<T> type) {
        return injector.getInstance(type);
    }
}