package me.diax.bot.lib.command;

import me.diax.bot.lib.ComponentProvider;
import org.reflections.Reflections;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Comportment on 18/04/17.
 * <p>
 * DOn't code while sleep deprived :^)
 */
@Singleton
public class DiaxCommands {

    private static final String COMMAND_PACKAGE = "me.diax.bot.commands";
    private final ComponentProvider provider;
    private final Map<DiaxCommandDescription, Class<? extends AbstractDiaxCommand>> commands;

    @Inject
    DiaxCommands(ComponentProvider provider) {
        this.provider = provider;
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

    DiaxCommandDescription find(String input) {
        for (DiaxCommandDescription cmd : commands.keySet()) {
            for (String s : cmd.triggers()) {
                if (input.equalsIgnoreCase(s)) {
                    return cmd;
                }
            }
        }
        return null;
    }

    AbstractDiaxCommand newInstance(DiaxCommandDescription description) throws Exception {
        Class<? extends AbstractDiaxCommand> type;
        if (description != null && (type = commands.get(description)) != null) {
            return provider.getInstance(type);
        } else {
            return null;
        }
    }
}