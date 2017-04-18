package me.diax.bot.commands;

import me.diax.bot.lib.command.AbstractDiaxCommand;
import me.diax.bot.lib.command.DiaxCommandDescription;
import me.diax.bot.lib.objects.DiaxMessage;

/**
 * Created by Comportment on 18/04/17.
 *
 * Its a test/example command, k, k???
 */
@DiaxCommandDescription(name = "the name", triggers = {"ping"}, description = "Some stuff.", minimumArgs = 1)
public class PingCommand extends AbstractDiaxCommand {

    public boolean execute(DiaxMessage message, String raw) {
        System.out.println("Noice");
        return true;
    }
}
