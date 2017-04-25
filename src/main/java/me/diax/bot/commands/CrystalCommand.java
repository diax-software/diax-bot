package me.diax.bot.commands;

import me.diax.bot.lib.DiaxMessage;
import me.diax.bot.lib.bot.DiaxBot;
import me.diax.bot.lib.command.DiaxCommandDescription;
import me.diax.bot.lib.command.DiaxCommandImpl;

/**
 * Created by Comportment on 18/04/17.
 *
 * Its a test/example command, k, k???
 */
@DiaxCommandDescription(name = "the name", triggers = {"crystal"}, description = "Some stuff.", minimumArgs = 1)
public class CrystalCommand extends DiaxCommandImpl {

    public void execute(DiaxBot bot, DiaxMessage message, String raw) {
        bot.messageTo(message.getChannel(), "Sucks");
    }
}
