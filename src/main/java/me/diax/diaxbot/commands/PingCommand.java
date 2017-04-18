package me.diax.diaxbot.commands;

import me.diax.diaxbot.lib.command.AbstractDiaxCommand;
import me.diax.diaxbot.lib.command.DiaxCommandDescription;
import me.diax.diaxbot.lib.objects.DiaxAuthor;

/**
 * Created by comportment on 18/04/17.
 */
@DiaxCommandDescription(name = "the name", triggers = {"1", "2", "3"}, description = "Some stuff.")
public class PingCommand extends AbstractDiaxCommand {

    public boolean execute(DiaxAuthor author) {
        return true;
    }
}
