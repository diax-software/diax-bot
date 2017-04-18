package me.diax.diaxbot.lib.command;

/**
 * Created by comportment on 18/04/17.
 */
public abstract class AbstractDiaxCommand implements Comparable<AbstractDiaxCommand>, DiaxCommandImpl {

    public DiaxCommandDescription getCommandAnnotation() {
        return getClass().getAnnotation(DiaxCommandDescription.class);
    }

    public String getName() {
        return getCommandAnnotation().name();
    }

    public String[] getTriggers() {
        return getCommandAnnotation().triggers();
    }

    public String getDescription() {
        return getCommandAnnotation().description();
    }

    @Override
    public int compareTo(AbstractDiaxCommand that) {
        return this.getName().compareTo(that.getName());
    }
}
