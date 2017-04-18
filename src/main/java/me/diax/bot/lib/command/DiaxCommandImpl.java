package me.diax.bot.lib.command;

/**
 * Created by comportment on 18/04/17.
 */
@FunctionalInterface
public interface DiaxCommandImpl {

    /**
     *
     * @return Whether or not the command was executed successfully.
     */
    boolean execute();
}
