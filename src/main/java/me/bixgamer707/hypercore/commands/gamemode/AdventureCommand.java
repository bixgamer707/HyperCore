package me.bixgamer707.hypercore.commands.gamemode;

import me.bixgamer707.hypercore.HyperCore;
import me.bixgamer707.hypercore.utils.Utils;
import me.bixgamer707.hypercore.utils.YamlFile;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdventureCommand implements CommandExecutor {

    private final YamlFile messages;

    public AdventureCommand(HyperCore plugin){
        this.messages = plugin.getMessages();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;
        if(!(args.length > 0)) {
            if(!player.hasPermission("hypercore.gamemode.2") || !player.hasPermission("hypercore.gamemode.*")) {
                player.sendMessage(Utils.colorize(messages, messages.getString("no-permission"))
                        .replaceAll("%player%",player.getName()));
                return true;
            }
            player.setGameMode(GameMode.ADVENTURE);
            player.sendMessage(Utils.colorize(messages, messages.getString("gamemode.message"))
                    .replaceAll("%gamemode%", String.valueOf(player.getGameMode())));
            return true;
        }
        if(!player.hasPermission("hypercore.gamemode.2") || !player.hasPermission("hypercore.gamemode.*")) {
            player.sendMessage(Utils.colorize(messages, messages.getString("no-permission"))
                    .replaceAll("%player%",player.getName()));
            return true;
        }
        Player target = Bukkit.getPlayerExact(args[0]);
        if(target == null){
            player.sendMessage(Utils.colorize(messages, messages.getString("gamemode.playeroffline"))
                    .replaceAll("%playeroffline%",args[0]));
            return true;
        }
        target.setGameMode(GameMode.ADVENTURE);
        player.sendMessage(Utils.colorize(messages, messages.getString("gamemode.playermessage"))
                .replaceAll("%player%",player.getName()));

        target.sendMessage(Utils.colorize(messages, messages.getString("gamemode.sendermessage"))
                .replaceAll("%player%",player.getName()));
        return true;
    }
}
