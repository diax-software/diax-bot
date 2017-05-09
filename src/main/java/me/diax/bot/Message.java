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

import me.diax.bot.api.IMessage;
import me.diax.bot.api.IUser;
import me.diax.bot.api.MessageContent;
import me.diax.bot.api.channel.Channel;

import java.sql.Timestamp;

/**
 * Created by Comportment at 20:00 on 09/05/17
 * https://github.com/Comportment | comportment@diax.me
 *
 * @author Comportment
 */
public class Message implements IMessage {

    private String id;
    private IUser author;
    private Channel channel;
    private MessageContent content;
    private Timestamp timestamp;

    public Message(String id, IUser author, Channel channel, MessageContent content, Timestamp timestamp) {
        this.id = id;
        this.author = author;
        this.channel = channel;
        this.content = content;
        this.timestamp = timestamp;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public IUser getAuthor() {
        return author;
    }

    @Override
    public Channel getChannel() {
        return channel;
    }

    @Override
    public MessageContent getContent() {
        return content;
    }

    @Override
    public Timestamp getTimestamp() {
        return timestamp;
    }
}