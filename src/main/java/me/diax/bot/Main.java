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
import me.diax.bot.api.bot.Bot;
import me.diax.bot.api.bot.DiscordBot;
import me.diax.bot.api.channel.Channel;
import me.diax.bot.api.channel.DiscordChannel;
import me.diax.bot.api.command.CommandProvider;
import me.diax.bot.api.command.Commands;

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

    private Main() {
        Properties sys = System.getProperties();
        Properties config = new Properties();
        String[] fields = {"discordToken", "prefix"};
        Arrays.stream(fields).forEach(field -> sys.computeIfPresent(field, config::put));
        properties = ConfigurationUtils.loadConfiguration(
                this.getClass().getClassLoader(),
                "diax.properties",
                new File(System.getProperty("user.dir")),
                DiaxProperties.class);
        injector = Guice.createInjector(this);
    }

    public static void main(String[] args) {
        new Main().main();
    }

    private void main() {
        Bot bot = this.getInstance(DiscordBot.class);
        bot.start();
        Channel channel = new DiscordChannel(303542298594115584L, DiscordBot.getSHARDS()[0]);
        channel.sendMessages("owo", "uwu", "dab <o/");
        channel.sendMessage("memes");
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
    }
}