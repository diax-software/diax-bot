package me.diax.diaxbot;


import me.diax.diaxbot.events.ChannelMessageEvent;
import me.diax.diaxbot.events.SystemEvent;
import me.diax.diaxbot.events.UserJoinEvent;
import me.diax.diaxbot.events.UserLeaveEvent;

/**
 * Created by NachtRaben on 4/16/2017.
 */
public abstract class EventHandler {

    public void onSystemEvent(SystemEvent event){}
    public void onChannelMessage(ChannelMessageEvent event){}
    public void onUserJoin(UserJoinEvent event){}
    public void onUserQuit(UserLeaveEvent event) {}
}
