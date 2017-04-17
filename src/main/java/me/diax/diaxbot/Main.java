package me.diax.diaxbot;

import me.diax.diaxbot.bots.DiaxIRCBot;
import me.diax.diaxbot.events.ChannelMessageEvent;
import me.diax.diaxbot.events.SystemEvent;
import me.diax.diaxbot.events.UserJoinEvent;
import me.diax.diaxbot.events.UserLeaveEvent;

/**
 * \o/
 */
public class Main {

    private static final String IDENTIFIER = "DiaxIRC";

    public static void main(String[] args) throws Exception {
        DiaxIRCBot test = new DiaxIRCBot("irc.domirc.net", IDENTIFIER, IDENTIFIER);
        test.joinChannel("diax.me", "##testing");
        test.addEventHandler(new EventHandler() {
            @Override
            public void onSystemEvent(SystemEvent event) {
            }

            @Override
            public void onChannelMessage(ChannelMessageEvent event) {
                System.out.println(event.toString());
            }

            @Override
            public void onUserJoin(UserJoinEvent event) {
                System.out.println(event.toString());
            }

            @Override
            public void onUserQuit(UserLeaveEvent event) {
                System.out.println(event.toString());
            }
        });
    }

}