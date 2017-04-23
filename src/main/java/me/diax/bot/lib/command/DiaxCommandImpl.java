package me.diax.bot.lib.command;

/**
 * Created by comportment on 18/04/17.
 */
public abstract class DiaxCommandImpl implements Comparable<DiaxCommandImpl>, DiaxCommand {

    /**
     * @return The {@link DiaxCommandDescription} of that class. <b>Should</b> be never null.
     */
    public DiaxCommandDescription getCommandAnnotation() {
        return getClass().getAnnotation(DiaxCommandDescription.class);
    }

    /**
     *
     * @return The unique name of the command.
     */
    public String getName() {
        return getCommandAnnotation().name();
    }

    /**
     * @return The keywords that could trigger the command.
     */
    public String[] getTriggers() {
        return getCommandAnnotation().triggers();
    }

    /**
     * @return The description of the command.
     */
    public String getDescription() {
        return getCommandAnnotation().description();
    }

    @Override
    public int compareTo(DiaxCommandImpl that) {
        return this.getName().compareTo(that.getName());
    }
}
