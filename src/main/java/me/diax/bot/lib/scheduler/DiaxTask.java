package me.diax.bot.lib.scheduler;

/**
 * Created by comportment on 20/04/17.
 */
public abstract class DiaxTask implements Runnable {

    private String name;

    public DiaxTask(String name) {
        this.name = name;
    }

    public DiaxTask() {
        this.name = "DiaxTask-" + System.currentTimeMillis();
    }

    /**
     * @param delay The amount of time in millis to delay the task by.
     */
    public void delay(long delay) {
        DiaxScheduler.delayTask(this, delay);
    }

    /**
     *
     * @param delay The initial delay before starting the task.
     * @param interval The interval delay between each time the task is ran.
     * @return If the task could be scheduled.
     */
    public boolean repeat(long delay, long interval) {
        return DiaxScheduler.scheduleRepeating(this, name, delay, interval);
    }

    /**
     *
     * @return If the task could be cancelled.
     */
    public boolean cancel() {
        return DiaxScheduler.cancelTask(name);
    }
}
