package me.solitude.bedrocktridents;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {

    private final Main main;

    public ReloadCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!commandSender.hasPermission("bedrocktridents.reload")) {
            commandSender.sendMessage("§cYou do not have permission to use that command.");
            return true;
        }

        main.loadConfigData();
        commandSender.sendMessage("§aBedrockTridents config reloaded! Damage Per Level: §b" + main.getDamagePerLevel());

        return true;
    }
}
