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
import me.diax.bot.api.Message;
import me.diax.bot.api.MessageContent;
import me.diax.bot.api.MessageContentBuilder;
import me.diax.bot.api.User;
import me.diax.bot.api.channel.Channel;
import me.diax.bot.api.channel.ChannelType;
import me.diax.bot.api.channel.IRCChannel;
import me.diax.bot.api.command.Command;
import me.diax.bot.api.command.CommandHandler;
import me.diax.bot.api.command.CommandProvider;
import org.jibble.pircbot.PircBot;

import javax.inject.Inject;
import java.sql.Timestamp;

/**
 * Created by Comportment at 20:26 on 08/05/17
 * https://github.com/Comportment | comportment@diax.me
 *
 * @author Comportment
 */
public class PircIRCBot extends PircBot {

    private final DiaxProperties properties;
    private final String prefix;
    private final CommandHandler handler;
    private final CommandProvider provider;

    @Inject
    public PircIRCBot(DiaxProperties properties, CommandHandler handler, CommandProvider provider) {
        this.properties = properties;
        this.prefix = properties.getPrefix();
        this.handler = handler;
        this.provider = provider;
    }

    @Override
    public void onMessage(String channel, String sender, String login, String hostname, String message) {
        ChannelType type = channel.startsWith("#") ? ChannelType.PUBLIC : ChannelType.PRIVATE;
        if (message.startsWith(prefix)) {
            message = message.replaceFirst(prefix, "");
        } else {
            if (!type.equals(ChannelType.PRIVATE)) {
                return;
            }
        }
        String fmsg = message;
        PircIRCBot bot = this;
        Command command = provider.newInstance(provider.find(message.split(" ")[0]));
        if (command == null) return;
        Message msg = new Message() {
            @Override
            public User getAuthor() {
                return new User() {
                    @Override
                    public String getSimpleName() {
                        return sender;
                    }

                    @Override
                    public String getLongName() {
                        return login;
                    }

                    @Override
                    public String getId() {
                        return hostname;
                    }
                };
            }

            @Override
            public Channel getChannel() {
                return new IRCChannel(bot, type, channel);
            }

            @Override
            public MessageContent getContent() {
                return new MessageContentBuilder().setContent(fmsg).build();
            }

            @Override
            public Timestamp getTimestamp() {
                return null;
            }

            @Override
            public String getId() {
                return "";
            }
        };
        handler.execute(command, msg, message.replaceFirst(message.split(" ")[0], ""));
    }
}