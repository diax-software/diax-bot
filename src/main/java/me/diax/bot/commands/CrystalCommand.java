package me.diax.bot.commands;

import me.diax.bot.lib.AbstractDiaxBot;
import me.diax.bot.lib.command.AbstractDiaxCommand;
import me.diax.bot.lib.command.DiaxCommandDescription;
import me.diax.bot.lib.objects.DiaxMessage;

/**
 * Created by Comportment on 18/04/17.
 *
 * Its a test/example command, k, k???
 */
@DiaxCommandDescription(name = "the name", triggers = {"crystal"}, description = "Some stuff.", minimumArgs = 1)
public class CrystalCommand extends AbstractDiaxCommand {

    public boolean execute(AbstractDiaxBot bot, DiaxMessage message, String raw) {
        bot.messageTo(message.getChannel(), "Sucks");
        return true;
    }
}
