package me.bixgamer707.hypercore.motd;

import me.bixgamer707.hypercore.HyperCore;
import me.bixgamer707.hypercore.utils.Utils;
import me.bixgamer707.hypercore.utils.YamlFile;
import org.bukkit.event.EventHandler;
import org.bukkit.ChatColor;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.event.Listener;

import java.util.List;

public class ServerMotd implements Listener {
    private final HyperCore plugin;

    public ServerMotd(HyperCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void servermotd(ServerListPingEvent event) {
        YamlFile config = plugin.getConfig();
        if (config.getBoolean("Motd.enable")) {
            List<String> list = config.getStringList("Motd.motd");
            event.setMotd(Utils.color(list.get(0) + "\n" + list.get(1)));
        }
    }
}
