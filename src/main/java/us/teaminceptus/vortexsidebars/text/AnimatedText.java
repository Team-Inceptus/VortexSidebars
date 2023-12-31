package us.teaminceptus.vortexsidebars.text;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class AnimatedText extends Text {
    private List<String> texts;

    /**
     * Fetches an immutable copy of the texts to be displayed.
     * @return the list of Strings that are cycled through when displaying
     */
    @NotNull
    public List<String> getTexts() { 
        return ImmutableList.copyOf(texts);
    }

    /**
     * Sets the list of Strings to cycle through when displaying
     * @param newTexts the list of Strings to cycle through when displaying
     */
    public void setTexts(@Nullable List<String> newTexts) {
        texts = new ArrayList<>(newTexts);
    }

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
    public boolean isPaused() { return paused; }

    /**
     * @param newValue whether the animation should be paused
     */
    public void setPaused(boolean newValue) { paused = newValue; }

    /**
     * @param texts the list of Strings to cycle through
     * @param delay the delay (in ticks) between each update
     */
    public AnimatedText(@NotNull Plugin plugin, @NotNull List<String> texts, int delay) {
        super(texts.get(0));
        
        if (plugin == null || texts == null) throw new IllegalArgumentException("plugin and texts cannot be null");

        this.texts = texts;
        this.delay = delay;

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!isPaused()) {
                    update();
                }
            }
        }.runTaskTimer(plugin, delay, delay);
    }

    /**
     * @param texts the list of Strings to cycle through
     * @param delay the delay (in ticks) between each update
     * @param startingIndex the index of the String to start with
     */
    public AnimatedText(@NotNull Plugin plugin, @NotNull List<String> texts, int delay, int startingIndex) {
        super(texts.get(startingIndex));
        this.texts = texts;
        this.delay = delay;

        if (plugin == null || texts == null) throw new IllegalArgumentException("plugin and texts cannot be null");

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!isPaused()) {
                    update();
                }
            }
        }.runTaskTimer(plugin, delay, delay);
    }

    private void update() {
        if (getCurrentIndex() == getTexts().size()-1) {
            setIndex(0);
        } else setIndex(getCurrentIndex()+1);
        setText(texts.get(getCurrentIndex()));
    }
}
