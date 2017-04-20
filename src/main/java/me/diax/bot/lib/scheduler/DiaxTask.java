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

    public void delay(long delay) {
        DiaxScheduler.delayTask(this, delay);
    }

    public boolean repeat(long delay, long interval) {
        return DiaxScheduler.scheduleRepeating(this, name, delay, interval);
    }

    public boolean cancel() {
        return DiaxScheduler.cancelTask(name);
    }
}
