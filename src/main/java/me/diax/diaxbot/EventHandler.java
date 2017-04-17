package me.diax.diaxbot;


import me.diax.diaxbot.events.ChannelMessageEvent;
import me.diax.diaxbot.events.SystemEvent;

/**
 * Created by NachtRaben on 4/16/2017.
 */
public abstract class EventHandler {

    public void onSystemEvent(SystemEvent event){}
    public void onChannelMessage(ChannelMessageEvent event){}
}
