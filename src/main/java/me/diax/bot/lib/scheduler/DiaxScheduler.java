package me.diax.bot.lib.scheduler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by Comportment on 20/04/17.
 *
 * With some help from bcwfwalshy
 */
public class DiaxScheduler {

    private static final ScheduledExecutorService timer = Executors.newScheduledThreadPool(10, r -> new Thread(r, "Diax Scheduled Task"));
    private static final Map<String, ScheduledFuture<?>> tasks = new HashMap<>();

    /**
     * Schedules a new repeating task, best to call this method using a lambda expression.
     * <br>
     * <code> DiaxScheduler.scheduleRepeating(() -> {
     * //The stuff you want to do here.
     * }, "Task name", 1L, 1L);
     * </code>
     *
     * @param task     The task you want to run.
     * @param name     The name of the task.
     * @param delay    The millisecond delay before the task first starts.
     * @param interval The millisecond interval before the task repeats.
     * @return If the task could be made, false if the task already exists.
     */
    public static boolean scheduleRepeating(Runnable task, String name, long delay, long interval) {
        if (tasks.containsKey(name)) return false;
        tasks.put(name, timer.scheduleAtFixedRate(() -> {
            try {
                task.run();
            } catch (Exception e) {
                System.err.println("Error executing " + name);
            }
        }, delay, interval, TimeUnit.MILLISECONDS));
        return true;
    }

    /**
     * Delays the given task by the amount of milliseconds given.
     *
     * @param task The task that should be delayed.
     * @param delay The delay in milliseconds that should be given to the task.
     */
    public static void delayTask(Runnable task, long delay) {
        timer.schedule(task, delay, TimeUnit.MILLISECONDS);
    }

    /**
     * Cancels a task related to the one with the name given.
     *
     * @param taskName The name of the task to cancel.
     * @return <code>true</code> if the task could be cancelled.
     */
    public static boolean cancelTask(String taskName) {
        Iterator<Map.Entry<String, ScheduledFuture<?>>> i = tasks.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry<String, ScheduledFuture<?>> next = i.next();
            if (next.getKey().equals(taskName)) {
                next.getValue().cancel(false);
                i.remove();
                return true;
            }
        }
        return false;
    }
}
