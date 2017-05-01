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

package me.diax.bot.api.channel;

import net.dv8tion.jda.core.JDA;

/**
 * Created by Comportment at 15:58 on 01/05/17
 * https://github.com/Comportment | comportment@diax.me
 *
 * @author Comportment
 */
public class DiscordChannel extends AbstractChannel {

    private JDA jda;

    public DiscordChannel(long id, JDA jda) {
        super(id);
        this.jda = jda;
    }

    @Override
    public void sendMessage(String message) {
        jda.getTextChannelById(getId()).sendMessage(message).queue();
    }
}