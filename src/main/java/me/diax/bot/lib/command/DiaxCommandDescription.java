package me.diax.bot.lib.command;

import java.lang.annotation.*;

/**
 * Created by comportment on 18/04/17.
 *
 * The annotation that should be in every command.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface DiaxCommandDescription {

    /**
     * The unique name of the command.
     */
    String name();

    /**
     * The keywords which can trigger the command.
     */
    String[] triggers();

    /**
     * The description of the command.
     */
    String description();

    /**
     * The minimum amount of args needed to execute the command.
     */
    int minimumArgs();
}