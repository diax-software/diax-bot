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

import me.diax.bot.DiaxProperties;
import me.diax.bot.Message;
import me.diax.bot.api.MessageContentBuilder;
import me.diax.bot.api.User;
import me.diax.bot.api.channel.ChannelType;
import me.diax.bot.api.channel.IRCChannel;
import me.diax.bot.api.command.Command;
import me.diax.bot.api.command.CommandHandler;
import me.diax.bot.api.command.CommandProvider;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import javax.inject.Inject;
import java.sql.Timestamp;

/**
 * Created by Comportment at 19:57 on 09/05/17
 * https://github.com/Comportment | comportment@diax.me
 *
 * @author Comportment
 */
public class IRCListener extends ListenerAdapter {

    private final DiaxProperties properties;
    private final CommandProvider provider;
    private final CommandHandler handler;

    @Inject
    public IRCListener(DiaxProperties properties, CommandProvider provider, CommandHandler handler) {
        this.properties = properties;
        this.provider = provider;
        this.handler = handler;
    }

    @Override
    public void onMessage(MessageEvent event) {
        String p = properties.getPrefix();
        String content = event.getMessage();
        ChannelType type = (event.getChannel().getName().startsWith("#") ? ChannelType.PUBLIC : ChannelType.PRIVATE);
        if (content.startsWith(p)) {
            content = content.replaceFirst(p, "");
            if ("".equals(content)) return;
        } else {
            if (!type.equals(ChannelType.PRIVATE)) return;
        }
        Command command = provider.newInstance(provider.find(content.split(" ")[0]));
        if (command == null) return;
        if (event.getUser() == null) return;
        Message message = new Message(
                event.getId() + "",
                new User(event.getUser().getIdent(), event.getUser().getNick(), event.getUser().getHostmask()),
                new IRCChannel(event.getBot(), type, event.getChannel().getName()),
                new MessageContentBuilder().setContent(event.getMessage()).build(),
                new Timestamp(System.currentTimeMillis()));
        handler.execute(command, message, content.replaceFirst(content.split(" ")[0], ""));
    }
}