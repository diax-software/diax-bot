package me.diax.bot.lib.command;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by comportment on 18/04/17.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Deprecated
public @interface DiaxCommandAttribute {

    String key();
    String value();
}
