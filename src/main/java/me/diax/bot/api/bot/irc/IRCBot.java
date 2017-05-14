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

package me.diax.bot.api.bot.irc;

import com.google.inject.Inject;
import me.diax.bot.ComponentProvider;
import me.diax.bot.api.bot.AbstractBot;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Comportment at 20:19 on 08/05/17
 * https://github.com/Comportment | comportment@diax.me
 *
 * @author Comportment
 */
public class IRCBot extends AbstractBot {

    private ComponentProvider provider;

    @Inject
    public IRCBot(ComponentProvider provider) {
        this.provider = provider;
        start();
    }

    @Override
    public void stop() {
        shards = null;
    }

    @Override
    public void start() {
        Configuration configuration = new Configuration.Builder()
                .setRealName("Diax-Bot")
                .setName("Diax")
                .setLogin("Diax")
                .setAutoNickChange(true)
                .setAutoReconnect(true)
                .addServer("irc.domirc.net")
                .addAutoJoinChannel("#diax.me")
                .addListener(provider.getInstance(IRCListener.class))
                .buildConfiguration();
        shards = new PircBotX[]{new PircBotX(configuration)};
        Arrays.stream(shards).forEach(bot -> {
            try {
                ((PircBotX) bot).startBot();
            } catch (IOException | IrcException ignored) {
            }
        });
    }
}