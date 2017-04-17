package me.diax.diaxbot.irc;

/**
 * Created by comportment on 17/04/17.
 */
public class LoginException extends RuntimeException {

    public LoginException(){
        super("Bot is already logged in!");
    }
}
