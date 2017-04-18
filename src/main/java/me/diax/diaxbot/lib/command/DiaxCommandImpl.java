package me.diax.diaxbot.lib.command;

import me.diax.diaxbot.lib.objects.DiaxAuthor;

/**
 * Created by comportment on 18/04/17.
 */
@FunctionalInterface
public interface DiaxCommandImpl {

    boolean execute(DiaxAuthor author);
}
