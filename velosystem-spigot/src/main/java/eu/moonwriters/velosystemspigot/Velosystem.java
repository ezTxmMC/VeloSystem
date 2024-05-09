package eu.moonwriters.velosystemspigot;

import org.bukkit.plugin.java.JavaPlugin;

public final class VeloSystem extends JavaPlugin {
    private static VeloSystem instance;

    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static VeloSystem getInstance() {
        return instance;
    }
}
