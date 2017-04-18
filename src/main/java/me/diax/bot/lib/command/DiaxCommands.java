package me.diax.bot.lib.command;

import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Comportment on 18/04/17.
 * <p>
 * DOn't code while sleep deprived :^)
 */
public class DiaxCommands {

    private static final String COMMAND_PACKAGE = "me.diax.bot.commands";
    private final Map<DiaxCommandDescription, Class<? extends AbstractDiaxCommand>> commands;

    public DiaxCommands() {
        this.commands = new HashMap<>();
        init();
    }

    @SuppressWarnings("unchecked")
    private void init() {
        Reflections reflections = new Reflections(COMMAND_PACKAGE);
        Set<Class<?>> types = reflections.getTypesAnnotatedWith(DiaxCommandDescription.class);
        for (Class<?> type : types) {
            DiaxCommandDescription description = type.getAnnotation(DiaxCommandDescription.class);
            commands.put(description, (Class<? extends AbstractDiaxCommand>) type);
        }
    }

    public Set<DiaxCommandDescription> getCommands() {
        return commands.keySet();
    }

    public DiaxCommandDescription find(String input) {
        for (DiaxCommandDescription cmd : commands.keySet()) {
            for (String s : cmd.triggers()) {
                if (input.equalsIgnoreCase(s)) {
                    return cmd;
                }
            }
        }
        return null;
    }

    public AbstractDiaxCommand newInstance(DiaxCommandDescription description) throws Exception {
        Class<? extends AbstractDiaxCommand> type;
        if (description != null && (type = commands.get(description)) != null) {
            return type.newInstance();
        } else {
            return null;
        }
    }
}