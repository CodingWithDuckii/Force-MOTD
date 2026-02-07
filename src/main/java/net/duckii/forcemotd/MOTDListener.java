package net.duckii.forcemotd;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class MOTDListener implements Listener {

    private final ForceMOTD plugin;

    public MOTDListener(ForceMOTD plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onServerPing(ServerListPingEvent event) {
        String motd = plugin.getCachedMotd();
        if (motd != null) {
            event.setMotd(motd);
        }
    }
}
