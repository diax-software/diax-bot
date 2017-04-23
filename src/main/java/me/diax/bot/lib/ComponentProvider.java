package me.diax.bot.lib;

/**
 * Created by Comportment on 20/04/17.
 *
 * But really it was just me copying what Crystal did
 */
public interface ComponentProvider {

    <T> T getInstance(Class<T> type);
}