package me.solitude.bedrocktridents;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main main;

    private double damagePerLevel;

    @Override
    public void onEnable() {
        main = this;

        saveDefaultConfig();
        loadConfigData();

        getServer().getPluginManager().registerEvents(new ImpaleListener(), this);
        getCommand("bedrocktridentsreload").setExecutor(new ReloadCommand(this));
    }

    @Override
    public void onDisable() {

    }

    public void loadConfigData() {
        reloadConfig();
        damagePerLevel = getConfig().getDouble("impaling-damage-per-level", 2.5);
    }

    public double getDamagePerLevel() {
        return damagePerLevel;
    }

    public static Main getInstance() {
        return main;
    }
}
