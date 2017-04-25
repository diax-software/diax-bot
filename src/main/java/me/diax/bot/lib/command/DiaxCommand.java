package me.diax.bot.lib.command;

import me.diax.bot.lib.DiaxMessage;
import me.diax.bot.lib.bot.DiaxBot;

/**
 * Created by Comportment on 18/04/17.
 *
 * Putting the FUN into FUNctional interface.
 */
@FunctionalInterface
public interface DiaxCommand {

    /**
     *
     * @return Whether or not the command was executed successfully.
     */
    void execute(DiaxBot bot, DiaxMessage trigger, String raw);
}
