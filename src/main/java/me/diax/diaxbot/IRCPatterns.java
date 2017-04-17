package me.diax.diaxbot;

import me.diax.diaxbot.bots.DiaxIRCBot;
import me.diax.diaxbot.events.ChannelMessageEvent;
import me.diax.diaxbot.events.SystemEvent;
import me.diax.diaxbot.events.UserJoinEvent;
import me.diax.diaxbot.events.UserLeaveEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by NachtRaben on 4/16/2017.
 */
public class IRCPatterns {

    public static final Pattern SYSTEM_EVENT_PATTERN = Pattern.compile("^:(\\w.+){2,3}\\s+([0-9]{3})\\s+(.*)$");
    public static final Pattern MESSAGE_PATTERN = Pattern.compile("^:(\\S+)!(\\S+)@(\\S+)+.PRIVMSG.(#\\S+).:(.*)$");
    public static final Pattern USER_JOIN_PATTERN = Pattern.compile("^:(\\S+)!(\\S+)@(\\S+)+.JOIN.(#\\S+)$");
    public static final Pattern USER_LEAVE_PATTERN = Pattern.compile("^:(\\S+)!(\\S+)@(\\S+)+.QUIT :.(./*)$");

    private static Set<IRCPatternHandler> handlers;

    static {
        handlers = new HashSet<>();
        handlers.add(new IRCPatternHandler() {
            @Override
            public boolean handle(DiaxIRCBot bot, String message) {
                Matcher m = SYSTEM_EVENT_PATTERN.matcher(message);
                if(m.matches()) {
                    SystemEvent event = new SystemEvent(bot, m);
                    for(EventHandler eh : bot.getEventHandlers())
                        eh.onSystemEvent(event);
                    return true;
                } else {
                    return false;
                }
            }
        });

        handlers.add(new IRCPatternHandler() {
            @Override
            public boolean handle(DiaxIRCBot bot, String message) {
                Matcher m = USER_JOIN_PATTERN.matcher(message);
                if(m.matches()) {
                    UserJoinEvent event = new UserJoinEvent(bot, m);
                    for(EventHandler eh : bot.getEventHandlers())
                        eh.onUserJoin(event);
                    return true;
                } else {
                    return false;
                }
            }
        });

        handlers.add(new IRCPatternHandler() {
            @Override
            public boolean handle(DiaxIRCBot bot, String message) {
                Matcher m = USER_LEAVE_PATTERN.matcher(message);
                if(m.matches()) {
                    UserLeaveEvent event = new UserLeaveEvent(bot, m);
                    for(EventHandler eh : bot.getEventHandlers())
                        eh.onUserQuit(event);
                    return true;
                } else {
                    return false;
                }
            }
        });

        handlers.add(new IRCPatternHandler() {
            @Override
            public boolean handle(DiaxIRCBot bot, String message) {
                Matcher m = MESSAGE_PATTERN.matcher(message);
                if(m.matches()) {
                    ChannelMessageEvent event = new ChannelMessageEvent(bot, m);
                    for(EventHandler eh : bot.getEventHandlers())
                        eh.onChannelMessage(event);
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    public static boolean handle(DiaxIRCBot bot, String message) {
        for(IRCPatternHandler handler : handlers) {
            if(handler.handle(bot, message))
                return true;
        }
        return false;
    }

}