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

@SuppressWarnings("all")
public class MainGameModeCommand implements CommandExecutor {

    private final YamlFile messages;

    public MainGameModeCommand(HyperCore plugin) {
        this.messages = plugin.getMessages();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        String noPermsions = Utils.colorize(
                messages, messages.getString("no-permission")
        ).replaceAll("%player%", player.getName());

        if (args.length < 1) {
            player.sendMessage(Utils.colorize(messages, messages.getString("gamemode.help")));
            return false;
        }

        if (!player.hasPermission("hypercore.gamemode.*")) {
            player.sendMessage(noPermsions);
            return true;
        }

        if (args[0].equalsIgnoreCase("0")) {
            if (!player.hasPermission("hypercore.gamemode.0")) {
                player.sendMessage(noPermsions);
                return true;
            }
            if (args.length > 1) {
                GamemodeManager.change(player, args[1], GameMode.SURVIVAL);
                return true;
            }
            GamemodeManager.change(player, GameMode.SURVIVAL);
            return true;
        }

        if (args[0].equalsIgnoreCase("1")) {
            if (!player.hasPermission("hypercore.gamemode.1")) {
                player.sendMessage(noPermsions);
                return true;
            }
            if (args.length > 1) {
                GamemodeManager.change(player, args[1], GameMode.CREATIVE);
                return true;
            }
            GamemodeManager.change(player, GameMode.CREATIVE);
            return true;
        }

        if (args[0].equalsIgnoreCase("2")) {
            if (!player.hasPermission("hypercore.gamemode.2")) {
                player.sendMessage(noPermsions);
                return true;
            }
            if (args.length > 1) {
                GamemodeManager.change(player, args[1], GameMode.ADVENTURE);
                return true;
            }
            GamemodeManager.change(player, GameMode.ADVENTURE);
            return true;
        }

        if (args[0].equalsIgnoreCase("3")) {
            if (!player.hasPermission("hypercore.gamemode.3")) {
                player.sendMessage(noPermsions);
                return true;
            }
            if (args.length > 1) {
                GamemodeManager.change(player, args[1], GameMode.SPECTATOR);
                return true;
            }
            GamemodeManager.change(player, GameMode.SPECTATOR);
            return true;
        }
        return true;
    }
}
