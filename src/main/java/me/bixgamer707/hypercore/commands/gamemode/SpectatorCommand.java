package me.bixgamer707.hypercore.commands.gamemode;

import me.bixgamer707.hypercore.HyperCore;
import me.bixgamer707.hypercore.managers.GamemodeManager;
import me.bixgamer707.hypercore.utils.Utils;
import me.bixgamer707.hypercore.utils.YamlFile;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpectatorCommand implements CommandExecutor {

    private final YamlFile messages;
    public SpectatorCommand(HyperCore plugin){
        this.messages = plugin.getMessages();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;
        if(!(args.length > 0)) {
            if(!player.hasPermission("hypercore.gamemode.3") || !player.hasPermission("hypercore.gamemode.*")) {
                player.sendMessage(Utils.colorize(messages, messages.getString("no-permission"))
                        .replaceAll("%player%",player.getName()));
                return true;
            }
            GamemodeManager.change(player, GameMode.SPECTATOR);
            return true;
        }
        if(!player.hasPermission("hypercore.gamemode.3") || !player.hasPermission("hypercore.gamemode.*")) {
            player.sendMessage(Utils.colorize(messages, messages.getString("no-permission"))
                    .replaceAll("%player%",player.getName()));
            return true;
        }
        GamemodeManager.change(player, args[0], GameMode.SPECTATOR);
        return true;
    }
}
