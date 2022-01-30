package me.bixgamer707.hypercore.events;

import me.bixgamer707.hypercore.HyperCore;
import me.bixgamer707.hypercore.utils.Utils;
import me.bixgamer707.hypercore.utils.YamlFile;
import org.bukkit.event.EventHandler;

import java.util.List;

import org.bukkit.entity.Player;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.Listener;

public class JoinEvent implements Listener {
    private final HyperCore plugin;

    public JoinEvent(HyperCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void joinPlayer(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        YamlFile events = plugin.getEvents();
        if (!events.getBoolean("JoinMessage.enable")) {
            return;
        }
        List<String> list = PlaceholderAPI.setPlaceholders(player, events.getStringList("JoinMessage.message"));
        for (String s : list) {
            player.sendMessage(Utils.color(s).replace("%player%", player.getName()).replace("%bullet_point%", "•"));
        }
    }

    @EventHandler
    public void joinTitle(PlayerJoinEvent event) {
        YamlFile events = plugin.getEvents();
        Player player = event.getPlayer();
        if (!events.getBoolean("TitleJoin.enable")) {
            return;
        }
        player.sendTitle(Utils.sanitizeString(player, events.getString("TitleJoin.title")), Utils.sanitizeString(player, events.getString("TitleJoin.subtitle")));

    }
}
