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
import com.google.inject.name.Names;
import me.diax.bot.api.Bot;
import me.diax.bot.api.DiscordBot;

/**
 * Created by Comportment at 14:52 on 30/04/17
 * https://github.com/Comportment | comportment@diax.me
 *
 * @author Comportment
 */
public final class Main implements ComponentProvider, Module {

    private final Injector injector;

    private Main() {
        injector = Guice.createInjector(this);
    }

    public static void main(String[] args) {
        new Main().main();
    }

    private void main() {
        Bot bot = getInstance(DiscordBot.class);
        bot.start();
    }

    @Override
    public <T> T getInstance(Class<T> type) {
        return injector.getInstance(type);
    }

    @Override
    public void configure(Binder binder) {
        binder.bind(ComponentProvider.class).toInstance(this);
        binder.bind(String.class).annotatedWith(Names.named("token")).toInstance("---");
    }
}