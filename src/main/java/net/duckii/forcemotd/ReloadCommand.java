package net.duckii.forcemotd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import java.util.Arrays;

public class ReloadCommand implements CommandExecutor {

    private final ForceMOTD plugin;

    public ReloadCommand(ForceMOTD plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("forcemotd.admin")) {
                sender.sendMessage("§cYou do not have permission to execute this command.");
                return true;
            }

            plugin.loadSettings();
            sender.sendMessage("§aForceMOTD config reloaded!");
            
            if (plugin.isLogOnReload()) {
                plugin.getLogger().info("Configuration reloaded by " + sender.getName());
            }
            return true;
        }

        if (args.length > 1 && args[0].equalsIgnoreCase("set")) {
            if (!sender.hasPermission("forcemotd.admin")) {
                sender.sendMessage("§cYou do not have permission to execute this command.");
                return true;
            }

            String newMotd = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
            plugin.getConfig().set("MOTD", newMotd);
            plugin.saveConfig();
            plugin.loadSettings();

            sender.sendMessage("§aForceMOTD updated.");
            return true;
        }
        
        sender.sendMessage("§eForceMOTD v" + plugin.getDescription().getVersion());
        sender.sendMessage("§7Usage: /forcemotd reload");
        sender.sendMessage("§7Usage: /forcemotd set <motd>");
        return true;
    }
}
