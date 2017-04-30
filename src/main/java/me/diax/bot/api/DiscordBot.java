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

package me.diax.bot.api;

import com.google.inject.Inject;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

import javax.inject.Named;
import java.util.Arrays;

/**
 * Created by Comportment at 14:35 on 30/04/17
 * https://github.com/Comportment | comportment@diax.me
 *
 * @author Comportment
 */
public class DiscordBot extends AbstractBot {

    static JDA[] SHARDS = null;
    private String token;

    @Inject
    public DiscordBot(@Named("token") String token) {
        this.token = token;
    }

    @Override
    public void start() {
        if (SHARDS != null) {
            //Error...
            return;
        }
        JDA jda;
        try {
            jda = new JDABuilder(AccountType.BOT).setToken(token).buildBlocking();
        } catch (Exception ignored) {
            jda = null;
        }
        SHARDS = new JDA[]{jda};
    }

    @Override
    public void stop() {
        if (SHARDS == null) {
            //Error...
            return;
        }
        Arrays.stream(SHARDS).forEach(JDA::shutdown);
        SHARDS = null;
    }

    @Override
    public void sendMessage(String channel, String message) {
        if (SHARDS == null) {
            //Error...
            return;
        }
        SHARDS[0].getTextChannelById(channel).sendMessage(message).queue();
    }
}