package me.diax.diaxbot.lib.command;

import java.lang.annotation.*;

/**
 * Created by comportment on 18/04/17.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DiaxCommandDescription {

    String name();
    String[] triggers();
    String description();
}
