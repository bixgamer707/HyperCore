package me.bixgamer707.hypercore.events;

import me.bixgamer707.hypercore.HyperCore;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {
    private final HyperCore plugin;
    public PlayerListener(HyperCore plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if(player.isOp() && !(plugin.getVersion().equals(plugin.latestVersion))){
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l---------------"+plugin.getMessages().getColoredString("Prefix")+"&c&l---------------"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aThere is a version available of"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Your version is &e" + plugin.latestVersion));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7and the new one is "+plugin.version));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eYou can download it at:"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bhttps://www.spigotmc.org/resources/sternal-hypercore-very-necessary.96652/"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l---------------"+plugin.getMessages().getColoredString("Prefix")+"&c&l---------------"));
        }
    }
}
