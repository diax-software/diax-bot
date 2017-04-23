package me.diax.bot.commands;

import me.diax.bot.lib.bot.DiaxBot;
import me.diax.bot.lib.command.DiaxCommandDescription;
import me.diax.bot.lib.command.DiaxCommandImpl;
import me.diax.bot.lib.objects.DiaxMessage;

/**
 * Created by Comportment on 18/04/17.
 *
 * Its a test/example command, k, k???
 */
@DiaxCommandDescription(name = "the name", triggers = {"crystal"}, description = "Some stuff.", minimumArgs = 1)
public class CrystalCommand extends DiaxCommandImpl {

    public boolean execute(DiaxBot bot, DiaxMessage message, String raw) {
        try {
            bot.messageTo(message.getChannel(), "Sucks");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
