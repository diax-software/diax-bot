package me.diax.bot.lib.command;

import me.diax.bot.lib.bot.AbstractDiaxBot;
import me.diax.bot.lib.exceptions.NotEnoughArgsException;
import me.diax.bot.lib.objects.DiaxMessage;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Comportment on 18/04/17.
 * <p>
 * Crystal can do a lot better.
 */
public class DiaxCommandHandler {

    private final DiaxCommands commands;
    private final String prefix;

    @Inject
    public DiaxCommandHandler(DiaxCommands commands) {
        this.commands = commands;
        this.prefix   = "<>";
    }

    public boolean execute(AbstractDiaxBot bot, DiaxMessage input) {
        if (!input.getContent().startsWith(prefix)) return false;
        System.out.println(input.getContent());
        String content = input.getContent().replaceFirst(prefix, "").trim();
        DiaxCommandDescription description = commands.find(content.split(" ")[0]);
        if (description == null) return false;
        try {
            if (content.split(" ").length < description.minimumArgs())
                throw new NotEnoughArgsException(description.name() + " needs " + description.minimumArgs() + " args.");
            return commands.newInstance(description).execute(bot, input, content);
        } catch (Exception e) {
            System.err.println("Error executing: " + description.name());
            return false;
        }
    }
}