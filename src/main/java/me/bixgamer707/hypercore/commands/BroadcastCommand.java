package me.bixgamer707.hypercore.commands;

import me.bixgamer707.hypercore.HyperCore;
import me.bixgamer707.hypercore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BroadcastCommand implements CommandExecutor {
    private final HyperCore plugin;

    public BroadcastCommand(HyperCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        } else {
            if (args.length > 0) {
                if (sender.hasPermission("hypercore.broadcast")) {
                    String message = "";
                    for (int i = 0; i <= args.length - 1; i++) {
                        message = message + args[i] + " ";
                    }
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        players.sendMessage(Utils.color("&8&l[&c&lBroadcast&8&l] &r" + message));
                    }
                }
            }
        }
        return true;
    }
}

