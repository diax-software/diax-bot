package me.diax.bot.lib.command;

import me.diax.bot.lib.DiaxMessage;
import me.diax.bot.lib.bot.DiaxBotImpl;

/**
 * Created by Comportment on 18/04/17.
 * <p>
 * DOn't code while sleep deprived :^)
 */
public interface DiaxCommandProvider {

    DiaxCommandDescription find(String input);

    DiaxCommandImpl newInstance(DiaxCommandDescription description);

    void execute(DiaxBotImpl bot, DiaxMessage input);
}