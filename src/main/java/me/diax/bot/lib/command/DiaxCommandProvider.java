package me.diax.bot.lib.command;

import me.diax.bot.lib.bot.AbstractDiaxBot;
import me.diax.bot.lib.objects.DiaxMessage;

/**
 * Created by Comportment on 18/04/17.
 * <p>
 * DOn't code while sleep deprived :^)
 */
public interface DiaxCommandProvider {

    DiaxCommandDescription find(String input);

    AbstractDiaxCommand newInstance(DiaxCommandDescription description);

    boolean execute(AbstractDiaxBot bot, DiaxMessage input);
}