package me.diax.bot.lib.scheduler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by comportment on 20/04/17.
 */
public class DiaxScheduler {

    private static final ScheduledExecutorService timer = Executors.newScheduledThreadPool(10, r -> new Thread(r, "Diax Scheduled Task"));
    private static final Map<String, ScheduledFuture<?>> tasks = new HashMap<>();

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

    public static void delayTask(Runnable task, long delay) {
        timer.schedule(task, delay, TimeUnit.MILLISECONDS);
    }

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
