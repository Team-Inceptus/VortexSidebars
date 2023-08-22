package us.teaminceptus.vortexsidebars;

import us.teaminceptus.vortexsidebars.text.AnimatedText;
import us.teaminceptus.vortexsidebars.text.Text;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Sidebar that can be displayed to players.
 */
public final class Sidebar {
    private final Scoreboard scoreboard;
    private final Objective objective;
    private final List<Text> lines;
    private Text title;
    private final List<Player> players;

    /**
     * Fetches an immutable copy of the lines for the sidebar.
     * @return the lines for the scoreboard.
     */
    @NotNull
    public List<Text> getLines() { 
        return ImmutableList.copyOf(lines);
    }

    /**
     * Adds a line to the sidebar. Will cancel if the line already exists, as sidebars do not support multiple lines with the same name. Defaults to the top if the index is not specified.
     * @param newLine the line to add
     */
    public void addLine(@NotNull Text newLine) {
        if (newLine == null) throw new IllegalArgumentException("newLine cannot be null");
        addLine(newLine, lines.size());
    }

    /**
     * Adds a line to the sidebar. Will cancel if the line already exists, as sidebars do not support multiple lines with the same name.
     * @param newLine the line to add
     * @param index the index (red score on the right)
     */
    public void addLine(@NotNull Text newLine, int index) {
        if (newLine == null) throw new IllegalArgumentException("newLine cannot be null");

        if (lines.contains(newLine)) {
            return;
        }
        lines.add(index, newLine);
        for (String entry : scoreboard.getEntries()) {
            if (objective.getScore(entry).getScore() >= index) objective.getScore(entry).setScore(objective.getScore(entry).getScore()+1);
        }
    }

    /**
     * Removes a line from the sidebar.
     * @param index the index (red score on the right)
     */
    public void removeLine(int index) {
        if (lines.size() < index-1 || index < 0) return;
        scoreboard.resetScores(lines.get(index).getText());
        lines.remove(index);
        for (String entry : scoreboard.getEntries()) {
            if (objective.getScore(entry).getScore() >= index) objective.getScore(entry).setScore(objective.getScore(entry).getScore() - 1);
        }
    }
    /**
     * Removes a line from the sidebar.
     * @param text the Text of the line to remove
     */
    public void removeLine(@NotNull Text text) {
        if (!lines.contains(text)) return;
        int index = lines.indexOf(text);
        removeLine(index);
    }

    /**
     * Clears all lines for the sidebar.
     */
    public void clearLines() {
        lines.clear();
        for (String entry : scoreboard.getEntries()) {
            scoreboard.resetScores(entry);
        }
    }


    /**
     * Fetches the title for the Sidebar.
     * @return the title for the sidebar (Text)
     */
    @NotNull
    public Text getTitle() { return title; }

    /**
     * Sets the title for the sidebar.
     * @param newTitle what to change the title to (Text)
     */
    public void setTitle(@NotNull Text newTitle) {
        if (newTitle == null) throw new IllegalArgumentException("newTitle cannot be null");
        title = newTitle;
     }

    /**
     * @return the players who are being displayed the sidebar
     */
    public List<Player> getPlayers() { return players; }

    /**
     * Displays the sidebar to a player
     * @param player the player to display the sidebar to
     */
    public void addPlayer(Player player) {
        players.add(player);
        player.setScoreboard(scoreboard);
    }

    /**
     * Stops displaying the sidebar to a player
     * @param player the player to stop showing the sidebar to
     */
    public void removePlayer(Player player) {
        players.remove(player);
        assert Bukkit.getScoreboardManager() != null;
        player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
    }

    /**
     * Stops displaying the sidebar for all players
     */
    public void clearPlayers() {
        assert Bukkit.getScoreboardManager() != null;
        for (Player player : players) {
            player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
        }
        players.clear();
    }

    /**
     * Creates a new Sidebar.
     * @param plugin the plugin that is creating the sidebar
     * @param title the title for the top of the sidebar, like a server or minigame name
     */
    public Sidebar(@NotNull Plugin plugin, @NotNull Text title) {
        this(plugin, title, new ArrayList<>());
    }

    /**
     * Creates a new Sidebar.
     * @param plugin the plugin that is creating the sidebar
     * @param title the title for the top of the sidebar, like a server or minigame name
     * @param lines the lines to display on the sidebar, cannot have duplicates
     */
    public Sidebar(@NotNull Plugin plugin, @NotNull Text title, @Nullable List<Text> lines) {
        if (plugin == null) throw new IllegalArgumentException("plugin cannot be null");
        if (title == null) throw new IllegalArgumentException("title cannot be null");

        this.title = title;

        assert Bukkit.getScoreboardManager() != null;
        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        objective = scoreboard.registerNewObjective(title.getText(), "dummy", title.getText());
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        lines = new ArrayList<>(lines);
        this.lines = lines;

        int score = 0;
        for (Text line : lines) {
            objective.getScore(line.getText()).setScore(score);
            score++;
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                update();
            }
        }.runTaskTimer(plugin, 1, 1);

        players = new ArrayList<>();
    }

    private void update() {
        if (title instanceof AnimatedText) objective.setDisplayName(title.getText());
        int score = 0;
        for (Text line : lines) {
            Score matchingScore = null;
            for (String entry : scoreboard.getEntries()) {
                if (objective.getScore(entry).getScore() == score) matchingScore = objective.getScore(entry);
            }
            if (matchingScore == null) {
                objective.getScore(line.getText()).setScore(score);
            } else {
                if (!line.getText().equals(matchingScore.getEntry())) {
                    scoreboard.resetScores(matchingScore.getEntry());
                    objective.getScore(line.getText()).setScore(score);
                }
            }
            score++;
        }
        for (String entry : scoreboard.getEntries()) {
            boolean contains = false;
            for (Text text : lines) {
                if (text.getText().equals(entry)) {
                    contains = true;
                    break;
                }
            }
            if (!contains) scoreboard.resetScores(entry);
        }
    }
}
