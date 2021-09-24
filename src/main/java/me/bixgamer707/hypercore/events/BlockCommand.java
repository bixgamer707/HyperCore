package me.bixgamer707.hypercore.events;

import me.bixgamer707.hypercore.HyperCore;
import me.bixgamer707.hypercore.utils.YamlFile;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;

public class BlockCommand implements Listener {
    private final HyperCore plugin;
    public BlockCommand(HyperCore plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event){
        YamlFile events = plugin.getEvents();
        Player sender = event.getPlayer();
        String command = event.getMessage();
        String[] parts = command.split(" ");
        String part1 = parts[0];
        String[] parts2 = part1.split("/");
        String cmd = parts2[1];
        if (!sender.hasPermission("hypercore.useblockedcommands") || !sender.hasPermission("hypercore.*")) {
            for (final String s : events.getStringList("BlockedCommands")) {
                if (cmd.equalsIgnoreCase(s)) {
                    final List<String> msg = (List<String>) PlaceholderAPI.setPlaceholders(sender, events.getStringList("BlockCommands.MessageBlocked"));
                    for (final String s2 : msg) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', s2).replace("%player%", sender.getDisplayName()));
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
}
