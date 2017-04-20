package me.diax.bot;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.name.Names;
import me.diax.bot.bots.irc.DiaxIRCBot;
import me.diax.bot.lib.ComponentProvider;
import me.diax.bot.lib.bot.AbstractDiaxBot;
import me.diax.bot.lib.command.DiaxCommandHandler;

/**
 * Created by comportment on 17/04/17.
 *
 *
 */
public class Main implements ComponentProvider, Module {

    private final DiaxCommandHandler handler;
    private final Injector injector;

    public Main() {
        injector = Guice.createInjector(this);
        handler = this.getInstance(DiaxCommandHandler.class);
    }

    public static void main(String[] args) throws Exception {
        new Main().main();
    }

    public DiaxCommandHandler getHandler() {
        return handler;
    }

    private void main() throws Exception {
        AbstractDiaxBot bot = injector.getInstance(DiaxIRCBot.class).start();
        //AbstractDiaxAudioBot bot2 = new DiaxDiscordBot().start();
    }

    @Override
    public void configure(Binder binder) {
        binder.bind(ComponentProvider.class).toInstance(this);
        binder.bind(DiaxCommandHandler.class).toInstance(injector.getInstance(DiaxCommandHandler.class));
        binder.bind(String.class).annotatedWith(Names.named("prefix")).toInstance("<>");
    }

    @Override
    public <T> T getInstance(Class<T> type) {
        return injector.getInstance(type);
    }
}