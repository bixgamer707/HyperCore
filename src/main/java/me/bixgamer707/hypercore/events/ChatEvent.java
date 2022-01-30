package me.bixgamer707.hypercore.events;

import me.bixgamer707.hypercore.HyperCore;
import me.bixgamer707.hypercore.methods.Spawn;
import me.bixgamer707.hypercore.managers.ConfirmManager;
import me.bixgamer707.hypercore.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {
    private final HyperCore plugin;

    public ChatEvent(HyperCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (ConfirmManager.containsPlayer(player.getUniqueId())) {
            String msg = event.getMessage();
            if (msg.contains("Confirm")) {
                Spawn spawn = plugin.getSpawn();
                spawn.getSetSpawn().setSpawn(player.getLocation());
                ConfirmManager.removePlayer(player.getUniqueId());
            } else if (msg.contains("Cancel")) {
                ConfirmManager.removePlayer(player.getUniqueId());
                player.sendMessage(Utils.colorize(plugin.getMessages(), "&7Has rechazado el remplazo del spawn!"));
            } else {
                player.sendMessage(Utils.colorize(plugin.getMessages(), "&7Recuerda que debes escribir '&aConfirmar&7'"));
                player.sendMessage(Utils.colorize(plugin.getMessages(), "para confirmar o '&cCancel&7' para rechazar"));
            }
        }
    }
}
