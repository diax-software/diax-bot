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

import java.util.Arrays;

/**
 * Created by Comportment at 21:13 on 01/05/17
 * https://github.com/Comportment | comportment@diax.me
 *
 * @author Comportment
 */
public enum PermissionLevel {

    BOT_OWNER(4, "Bot Owner"),
    BOT_DEVELOPER(3, "Bot Developer"),
    SERVER_OWNER(2, "Server Owner"),
    SERVER_ADMIN(1, "Server Admin"),
    DEFAULT(0, "Default"),
    UNKNOWN(-1, "Unknown");

    private int level;
    private String string;

    PermissionLevel(int level, String string) {
        this.level = level;
        this.string = string;
    }

    static PermissionLevel fromLevel(int level) {
        return Arrays.stream(PermissionLevel.values()).filter(permissionLevel -> permissionLevel.getLevel() == level).findAny().orElse(UNKNOWN);
    }

    public int getLevel() {
        return level;
    }

    public String asString() {
        return string;
    }
}