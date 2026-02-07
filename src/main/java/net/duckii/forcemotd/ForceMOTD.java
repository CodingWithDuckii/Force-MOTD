package net.duckii.forcemotd;

import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Level;

public class ForceMOTD extends JavaPlugin {

    private static ForceMOTD instance;
    private volatile String cachedMotd;
    private boolean logOnReload;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        loadSettings();

        var cmd = getCommand("forcemotd");
        if (cmd != null) {
            cmd.setExecutor(new ReloadCommand(this));
        } else {
            getLogger().warning("Command 'forcemotd' is missing from plugin.yml.");
        }

        getServer().getPluginManager().registerEvents(new MOTDListener(this), this);
    }

    @Override
    public void onDisable() {
        instance = null;
        getLogger().info("ForceMOTD has been disabled!");
    }

    public void loadSettings() {
        try {
            reloadConfig();
            
            String rawMotd = getConfig().getString("MOTD");
            if (rawMotd == null) {
                rawMotd = getConfig().getString("motd");
            }
            this.logOnReload = getConfig().getBoolean("log-on-reload", true);
    
            if (rawMotd != null) {
                // Support both & and §
                String coloredMotd = org.bukkit.ChatColor.translateAlternateColorCodes('&', rawMotd);
                
                // Handle Centering
                if (coloredMotd.contains("<center>")) {
                    // Split, center lines with tag, rejoin
                    coloredMotd = coloredMotd.replace("<center>", ""); // Simple approach: assume global center if tag present
                    // Or improved: split by newline and center only lines starting with <center>?
                    // User probably wants the whole thing centered if they ask for "Centered support".
                    // But let's check for the tag.
                    this.cachedMotd = MOTDUtils.center(coloredMotd.replace("\\n", "\n"));
                } else if (getConfig().getBoolean("center-motd", false)) {
                     this.cachedMotd = MOTDUtils.center(coloredMotd.replace("\\n", "\n"));
                } else {
                    this.cachedMotd = coloredMotd.replace("\\n", "\n");
                }
            } else {
                this.cachedMotd = "§cForceMOTD: MOTD not set in config.yml";
                getLogger().warning("MOTD is missing in config.yml (expected key: MOTD). Using a safe default.");
            }
        } catch (Exception e) {
            getLogger().log(Level.WARNING, "Could not load config.yml. Using a safe default MOTD.", e);
            this.cachedMotd = "§cForceMOTD: Config load failed";
        }
    }

    public String getCachedMotd() {
        return cachedMotd;
    }

    public boolean isLogOnReload() {
        return logOnReload;
    }

    public static ForceMOTD getInstance() {
        return instance;
    }
}
