package me.diax.diaxbot.bots;

import me.diax.diaxbot.EventHandler;

import java.util.Arrays;
import java.util.List;

/**
 * Created by comportment on 17/04/17.
 */
public abstract class DiaxAbstractBot implements Runnable {

    protected List<EventHandler> eventHandlers;

    public List<EventHandler> getEventHandlers() {
        return eventHandlers;
    }

    public DiaxAbstractBot addEventHandler(EventHandler... handlers) {
        eventHandlers.addAll(Arrays.asList(handlers));
        return this;
    }

    public DiaxAbstractBot removeEventHandler(EventHandler... handlers) {
        eventHandlers.removeAll(Arrays.asList(handlers));
        return this;
    }
}
