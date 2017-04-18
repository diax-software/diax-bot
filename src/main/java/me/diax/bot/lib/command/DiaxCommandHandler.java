package me.diax.bot.lib.command;

import me.diax.bot.lib.exceptions.NotEnoughArgsException;
import me.diax.bot.lib.objects.DiaxMessage;

/**
 * Created by Comportment on 18/04/17.
 * <p>
 * Crystal can do a lot better.
 */
public class DiaxCommandHandler {

    private final String prefix = "<>";

    public DiaxCommandHandler(DiaxMessage input) {
        if (!input.getContent().startsWith(prefix)) return;
        execute(input);
    }

    private void execute(DiaxMessage input) {
        String content = input.getContent().replaceFirst(prefix, "").trim();
        DiaxCommandDescription description = new DiaxCommands().find(content.split(" ")[0]);
        if (description == null) return;
        try {
            if (content.split(" ").length < description.minimumArgs())
                throw new NotEnoughArgsException(description.name() + " needs " + description.minimumArgs() + " args.");
            new DiaxCommands().newInstance(description).execute(input, content);
        } catch (Exception e) {
            System.err.println("Error executing: " + description.name());
        }
    }
}
