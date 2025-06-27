package me.solitude.bedrocktridents;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main main;

    private double damagePerLevel;
    private boolean debugLogs;

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
        debugLogs = getConfig().getBoolean("debug-logs", false);
    }

    public double getDamagePerLevel() {
        return damagePerLevel;
    }

    public boolean isDebugMode() {
        return debugLogs;
    }

    public static Main getInstance() {
        return main;
    }
}
