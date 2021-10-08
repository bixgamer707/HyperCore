package me.bixgamer707.hypercore.commands;

import me.bixgamer707.hypercore.HyperCore;
import me.bixgamer707.hypercore.utils.Utils;
import me.bixgamer707.hypercore.utils.YamlFile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor {

    private final YamlFile config;
    private final YamlFile events;
    private final YamlFile messages;

    public MainCommand(HyperCore plugin){
        this.config = plugin.getConfig();
        this.events = plugin.getEvents();
        this.messages = plugin.getMessages();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;
        if(!(args.length > 0)) {
            for(String line : messages.getColoredStringList("HyperCore.Help")) {
                player.sendMessage(line);
            }
            return false;
        }

        if(args[0].equalsIgnoreCase("reload")) {

            if(!player.hasPermission("hypercore.reload")) {
                player.sendMessage(Utils.colorize(messages,messages.getString("no-permission")));
                return true;
            }

            config.reload();
            events.reload();
            messages.reload();
            player.sendMessage(Utils.colorize(messages,messages.getString("reload-message")));
            return true;
        }
        for(String line : messages.getColoredStringList("HyperCore.Help")) {
            player.sendMessage(line);
        }
        return true;
    }
}
