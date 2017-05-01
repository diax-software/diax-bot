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

package me.diax.bot.api.command;

import me.diax.bot.ComponentProvider;
import org.reflections.Reflections;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Comportment at 20:07 on 01/05/17
 * https://github.com/Comportment | comportment@diax.me
 *
 * @author Comportment
 */
@Singleton
public class Commands implements CommandProvider {

    private static final String COMMAND_PACKAGE = "me.diax.bot.commands";
    private final ComponentProvider provider;
    private final Map<CommandDescription, Class<? extends Command>> commands;

    @Inject
    public Commands(ComponentProvider provider) {
        this.provider = provider;
        this.commands = new HashMap<>();
        init();
    }

    @SuppressWarnings("unchecked")
    private void init() {
        new Reflections(COMMAND_PACKAGE).getTypesAnnotatedWith(CommandDescription.class).forEach(type ->
                commands.put(type.getAnnotation(CommandDescription.class), (Class<? extends Command>) type));
    }

    public Set<CommandDescription> getCommands() {
        return commands.keySet();
    }

    public CommandDescription find(String input) {
        for (CommandDescription description : commands.keySet()) {
            for (String string : description.triggers()) {
                if (string.equalsIgnoreCase(input)) {
                    return description;
                }
            }
        }
        return null;
    }

    @Override
    public Command newInstance(CommandDescription description) {
        Class<? extends Command> type;
        if (description != null && (type = commands.get(description)) != null) {
            return provider.getInstance(type);
        } else {
            return null;
        }
    }
}