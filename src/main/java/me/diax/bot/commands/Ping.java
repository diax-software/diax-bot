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

package me.diax.bot.commands;

import me.diax.bot.api.Message;
import me.diax.bot.api.command.Command;
import me.diax.bot.api.command.CommandDescription;

/**
 * Created by Comportment at 19:46 on 01/05/17
 * https://github.com/Comportment | comportment@diax.me
 *
 * @author Comportment
 */
@CommandDescription(name = "ping", triggers = {"ping"})
public class Ping implements Command {

    @Override
    public void execute(Message message, String args) {
        message.getChannel().sendMessage("Pong!");
    }
}