

package me.bixgamer707.hypercore.commands;

import java.util.List;

import me.bixgamer707.hypercore.HyperCore;
import me.bixgamer707.hypercore.utils.Utils;
import me.bixgamer707.hypercore.utils.YamlFile;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class ChatClear implements CommandExecutor{
    private final HyperCore plugin;
    public ChatClear(HyperCore plugin){
        this.plugin = plugin;
    }
    public boolean onCommand(final CommandSender sender, final Command cmd, final String s, final String[] strings) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player p = (Player)sender;
        YamlFile messages = plugin.getMessages();
        if (!p.hasPermission("hypercore.clearchat")) {
            sender.sendMessage(Utils.colorize(messages,messages.getString("HyperCore.ClearChat.Perms")));
            return true;
        }
        int i = 0;
        while (i<100) {
            for(Player players : Bukkit.getOnlinePlayers()){
                players.sendMessage(Utils.color("&7 "));
            }
            i++;
        }
        List<String> msg = PlaceholderAPI.setPlaceholders(p, messages.getStringList("HyperCore.ClearChat.Message"));
        for (String message1 : msg) {
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message1).replace("%player%", p.getName()));
        }
        return true;
    }
}
