package me.diax.bot;

import me.diax.bot.lib.bot.AbstractDiaxBot;
import me.diax.bot.lib.command.AbstractDiaxCommand;
import me.diax.bot.lib.command.DiaxCommandDescription;
import me.diax.bot.lib.exceptions.NotEnoughArgsException;
import me.diax.bot.lib.objects.DiaxMessage;
import me.diax.bot.lib.ComponentProvider;
import me.diax.bot.lib.command.DiaxCommandProvider;
import org.reflections.Reflections;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Comportment on 18/04/17.
 * <p>
 * Crystal can do a lot better.
 */
@Singleton
class DiaxCommandHandler implements DiaxCommandProvider {

    private static final String COMMAND_PACKAGE = "me.diax.bot.commands";
    private final Map<DiaxCommandDescription, Class<? extends AbstractDiaxCommand>> commands;
    private final ComponentProvider provider;
    private final String prefix;

    @Inject
    DiaxCommandHandler(ComponentProvider provider, @Named("prefix") String prefix) {
        this.provider = provider;
        this.commands = new HashMap<>();
        this.prefix = prefix;
        registerCommands();
    }

    public void registerCommands() {
        Reflections reflections = new Reflections(COMMAND_PACKAGE);
        Set<Class<?>> types = reflections.getTypesAnnotatedWith(DiaxCommandDescription.class);
        types.forEach(cmd -> {
            commands.put(cmd.getAnnotation(DiaxCommandDescription.class), (Class<? extends AbstractDiaxCommand>) cmd);
        });
    }

    public AbstractDiaxCommand newInstance(DiaxCommandDescription description) {
        Class<? extends AbstractDiaxCommand> type;
        if (description != null && (type = commands.get(description)) != null) {
            return provider.getInstance(type);
        } else {
            return null;
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

    public boolean execute(AbstractDiaxBot bot, DiaxMessage input) {
        if (!input.getContent().startsWith(prefix)) return false;
        System.out.println(input.getContent());
        String content = input.getContent().replaceFirst(prefix, "").trim();
        DiaxCommandDescription description = find(content.split(" ")[0]);
        if (description == null) return false;
        try {
            if (content.split(" ").length < description.minimumArgs())
                throw new NotEnoughArgsException(description.name() + " needs " + description.minimumArgs() + " args.");
            return newInstance(description).execute(bot, input, content);
        } catch (Exception e) {
            System.err.println("Error executing: " + description.name());
            return false;
        }
    }
}