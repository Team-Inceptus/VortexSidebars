package me.dragin.vortexsidebars.text;

import me.dragin.vortexsidebars.Main;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class AnimatedText extends Text {
    private List<String> texts;

    /**
     * @return the list of Strings that are cycled through when displaying
     */
    public List<String> getTexts() { return texts; }

    /**
     * @param newTexts the list of Strings to cycle through when displaying
     */
    public void setTexts(List<String> newTexts) { texts = newTexts; }

    private int delay;

    /**
     * @return the delay (in ticks) between each update
     */
    public int getDelay() { return delay; }

    /**
     * @param newDelay the delay (in ticks) between each update
     */
    public void setDelay(int newDelay) { delay = newDelay; }

    private int index;

    /**
     * @return the index for the current String being displayed
     */
    public int getCurrentIndex() { return index; }

    /**
     * @param newIndex the index for the String to be displayed
     */
    public void setIndex(int newIndex) {
        if (newIndex < texts.size() && newIndex >= 0) {
            index = newIndex;
            setText(texts.get(index));
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    private boolean paused = false;

    /**
     * @return whether the animation is paused
     */
    public boolean getIsPaused() { return paused; }

    /**
     * @param newValue whether the animation should be paused
     */
    public void setPaused(boolean newValue) { paused = newValue; }

    /**
     * @param texts the list of Strings to cycle through
     * @param delay the delay (in ticks) between each update
     */
    public AnimatedText(List<String> texts, int delay) {
        super(texts.get(0));
        this.texts = texts;
        this.delay = delay;

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!getIsPaused()) {
                    update();
                }
            }
        }.runTaskTimer(Main.getInstance(), 1, delay);
    }

    /**
     * @param texts the list of Strings to cycle through
     * @param delay the delay (in ticks) between each update
     * @param startingIndex the index of the String to start with
     */
    public AnimatedText(List<String> texts, int delay, int startingIndex) {
        super(texts.get(startingIndex));
        this.texts = texts;
        this.delay = delay;

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!getIsPaused()) {
                    update();
                }
            }
        }.runTaskTimer(Main.getInstance(), 1, delay);
    }

    private void update() {
        if (getCurrentIndex() == getTexts().size()-1) {
            setIndex(0);
        } else setIndex(getCurrentIndex()+1);
        setText(texts.get(getCurrentIndex()));
    }
}
