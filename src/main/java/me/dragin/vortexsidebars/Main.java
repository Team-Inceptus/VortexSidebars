package me.dragin.vortexsidebars;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private static Main instance;

    @Override
    public void onEnable() { instance = this; }

    public static Main getInstance() { return instance; }
}
