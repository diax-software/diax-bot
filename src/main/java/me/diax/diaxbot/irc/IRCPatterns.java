package me.diax.diaxbot.irc;

import java.util.regex.Pattern;

/**
 * Created by NachtRaben on 4/16/2017.
 */
public class IRCPatterns {

    public static final Pattern SYSTEM_EVENT_PATTERN = Pattern.compile("^:(\\w.+){2,3}\\s+([0-9]{3})\\s+(.*)$");
    public static final Pattern MESSAGE_PATTERN = Pattern.compile("^:(\\S+)!(\\S+)@(\\S+)+.PRIVMSG.(#\\S+).:(.*)$");
    public static final Pattern USER_JOIN_PATTERN = Pattern.compile("^:(\\S+)!(\\S+)@(\\S+)+.JOIN.(#\\S+)$");
    public static final Pattern USER_LEAVE_PATTERN = Pattern.compile("^:(\\S+)!(\\S+)@(\\S+)+.QUIT :.(./*)$");
    public static final Pattern SYSTEM_NOTICE_PATTERN = Pattern.compile("^:(\\S+)!(\\S+)@(\\S+)+.NOTICE.(./*)$");
}