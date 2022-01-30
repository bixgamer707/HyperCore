package me.bixgamer707.hypercore.commands;

import me.bixgamer707.hypercore.HyperCore;
import me.bixgamer707.hypercore.managers.SocialSpyManager;
import me.bixgamer707.hypercore.utils.Utils;
import me.bixgamer707.hypercore.utils.YamlFile;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MsgCommand implements CommandExecutor {
    private final YamlFile config;

    public MsgCommand(HyperCore plugin) {
        this.config = plugin.getConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;
        if (args.length > 0) {
            return false;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        if (target != null) {
            return false;
        }

        StringBuilder message = new StringBuilder();
        for (int i = 1; i <= args.length - 1; i++) {
            message.append(args[i]).append(" ");
        }
        player.sendMessage(Utils.color(config.getString("Msg.send") + " &7" + message)
                .replaceAll("%player%", target.getName()));
        target.sendMessage(Utils.color(config.getString("Msg.received") + " &7" + message)
                .replaceAll("%player%", player.getName()));
        for (Player players : SocialSpyManager.getPlayers()) {
            players.sendMessage(Utils.color(config.getString("SocialSpy.msg"))
                    .replaceAll("%target%", target.getName())
                    .replaceAll("%player%", player.getName())
                    .replaceAll("%msg%", message.toString()));
        }
        return true;
    }
}
