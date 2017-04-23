package me.diax.bot.lib.command;

import me.diax.bot.lib.bot.DiaxBotImpl;
import me.diax.bot.lib.objects.DiaxMessage;

/**
 * Created by Comportment on 18/04/17.
 * <p>
 * DOn't code while sleep deprived :^)
 */
public interface DiaxCommandProvider {

    DiaxCommandDescription find(String input);

    DiaxCommandImpl newInstance(DiaxCommandDescription description);

    boolean execute(DiaxBotImpl bot, DiaxMessage input);
}