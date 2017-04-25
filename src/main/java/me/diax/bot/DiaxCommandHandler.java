package me.diax.bot;

import me.diax.bot.lib.ComponentProvider;
import me.diax.bot.lib.DiaxMessage;
import me.diax.bot.lib.bot.DiaxBotImpl;
import me.diax.bot.lib.command.DiaxCommandDescription;
import me.diax.bot.lib.command.DiaxCommandImpl;
import me.diax.bot.lib.command.DiaxCommandProvider;
import me.diax.bot.lib.exceptions.NotEnoughArgsException;
import me.diax.bot.lib.exceptions.UnknownException;
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
public final class DiaxCommandHandler implements DiaxCommandProvider {

    private static final String COMMAND_PACKAGE = "me.diax.bot.commands";
    private static final Map<DiaxCommandDescription, Class<? extends DiaxCommandImpl>> commands = new HashMap<>();

    static {
        Map<DiaxCommandDescription, Class<? extends DiaxCommandImpl>> cmds = new HashMap<>();
        Reflections reflections = new Reflections(COMMAND_PACKAGE);
        Set<Class<?>> types = reflections.getTypesAnnotatedWith(DiaxCommandDescription.class);
        types.forEach(cmd -> cmds.put(cmd.getAnnotation(DiaxCommandDescription.class), (Class<? extends DiaxCommandImpl>) cmd));
        commands.putAll(cmds);
    }

    private final ComponentProvider provider;
    private final String prefix;

    @Inject
    DiaxCommandHandler(ComponentProvider provider, @Named("prefix") String prefix) {
        this.provider = provider;
        this.prefix = prefix;
    }

    public DiaxCommandImpl newInstance(DiaxCommandDescription description) {
        Class<? extends DiaxCommandImpl> type;
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

    public void execute(DiaxBotImpl bot, DiaxMessage input) {
        if (!input.getContent().startsWith(prefix)) return;
        String content = input.getContent().replaceFirst(prefix, "").trim();
        DiaxCommandDescription description = find(content.split(" ")[0]);
        if (description == null) return;
        try {
            if (content.split(" ").length < description.minimumArgs())
                throw new NotEnoughArgsException(new Throwable(this.getClass().toGenericString()));
            newInstance(description).execute(bot, input, content);
        } catch (Exception e) {
            throw new UnknownException(e);
        }
    }
}