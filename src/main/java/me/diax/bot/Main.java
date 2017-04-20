package me.diax.bot;

import com.google.inject.*;
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

    private final Injector injector;
    private DiaxCommandHandler handler;


    public Main() {
        injector = Guice.createInjector(this);
    }

    public static void main(String[] args) throws Exception {
        new Main().main();
    }

    public DiaxCommandHandler getHandler() {
        return handler;
    }

    private void main() throws Exception {
        handler  = new Main().getInstance(DiaxCommandHandler.class);
        AbstractDiaxBot bot = injector.getInstance(DiaxIRCBot.class).start();
        //AbstractDiaxAudioBot bot2 = new DiaxDiscordBot().start();
    }

    @Override
    public void configure(Binder binder) {
        binder.bind(ComponentProvider.class).toInstance(this);
        binder.bind(DiaxCommandHandler.class).toInstance(injector.getInstance(DiaxCommandHandler.class));
    }

    @Override
    public <T> T getInstance(Class<T> type) {
        return injector.getInstance(type);
    }
}