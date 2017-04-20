package me.diax.bot.lib;

/**
 * Created by comportment on 20/04/17.
 */
public interface ComponentProvider {

    <T> T getInstance(Class<T> type);
}