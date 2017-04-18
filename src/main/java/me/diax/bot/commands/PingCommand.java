package me.diax.bot.commands;

import me.diax.bot.lib.command.AbstractDiaxCommand;
import me.diax.bot.lib.command.DiaxCommandDescription;

/**
 * Created by comportment on 18/04/17.
 */
@DiaxCommandDescription(name = "the name", triggers = {"1", "2", "3"}, description = "Some stuff.")
public class PingCommand extends AbstractDiaxCommand {

    public boolean execute() {
        return true;
    }
}
