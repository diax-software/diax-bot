/*
 * Copyright 2017 Comportment | comportment@diax.me
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.diax.bot;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.knockturnmc.api.util.ConfigurationUtils;
import me.diax.bot.api.bot.irc.IRCBot;
import me.diax.bot.api.channel.Channel;
import me.diax.bot.api.channel.ChannelType;
import me.diax.bot.api.channel.IRCChannel;
import me.diax.bot.api.command.CommandHandler;
import me.diax.bot.api.command.CommandProvider;
import me.diax.bot.api.command.Commands;
import org.pircbotx.PircBotX;

import java.io.File;
import java.util.Arrays;
import java.util.Properties;

/**
 * Created by Comportment at 14:52 on 30/04/17
 * https://github.com/Comportment | comportment@diax.me
 *
 * @author Comportment
 */
public final class Main implements ComponentProvider, Module {

    private final DiaxProperties properties;
    private final Injector injector;
    private final CommandHandler handler;

    private Main() {
        Properties sys = System.getProperties();
        Properties config = new Properties();
        Arrays.stream(DiaxProperties.getFields()).forEach(field -> sys.computeIfPresent(field, config::put));
        properties = ConfigurationUtils.loadConfiguration(
                this.getClass().getClassLoader(),
                "diax.properties",
                new File(System.getProperty("user.dir")),
                DiaxProperties.class);
        handler = new CommandHandler();
        injector = Guice.createInjector(this);
    }

    public static void main(String[] args) {
        new Main().main();
    }

    private void main() {
        IRCBot bot = new IRCBot(this);
        bot.start();
        Channel channel = new IRCChannel((PircBotX) bot.getShards()[0], ChannelType.PRIVATE, "#diax");
        channel.sendMessages("owo", "uwu", "dab <o/");
    }

    @Override
    public <T> T getInstance(Class<T> type) {
        return injector.getInstance(type);
    }

    @Override
    public void configure(Binder binder) {
        binder.bind(ComponentProvider.class).toInstance(this);
        binder.bind(DiaxProperties.class).toInstance(properties);
        binder.bind(CommandProvider.class).to(Commands.class);
        binder.bind(CommandHandler.class).toInstance(handler);
    }
}