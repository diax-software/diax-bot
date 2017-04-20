package me.diax.bot.lib.command;

import java.util.Set;

/**
 * Created by Comportment on 18/04/17.
 * <p>
 * DOn't code while sleep deprived :^)
 */
public interface DiaxCommandProvider {

    void registerCommands();

    Set<DiaxCommandDescription> getCommands();

    DiaxCommandDescription find(String input);

    AbstractDiaxCommand newInstance(DiaxCommandDescription description);
}