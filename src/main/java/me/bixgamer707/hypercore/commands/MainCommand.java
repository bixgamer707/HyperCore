package me.bixgamer707.hypercore.commands;

import me.bixgamer707.hypercore.HyperCore;
import me.bixgamer707.hypercore.utils.Utils;
import me.bixgamer707.hypercore.utils.YamlFile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor {
    private HyperCore plugin;
    public MainCommand(HyperCore plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }else{
            Player player = (Player) sender;
            YamlFile config = plugin.getConfig();
            YamlFile events = plugin.getEvents();
            YamlFile messages = plugin.getMessages();
            if(args.length > 0){
                if(args[0].equalsIgnoreCase("reload")){
                    if(player.hasPermission("hypercore.reload")){
                        config.reload();
                        events.reload();
                        messages.reload();
                        player.sendMessage(Utils.colorize(messages,messages.getString("reload-message")));
                    }else{
                        player.sendMessage(Utils.colorize(messages,messages.getString("no-permission")));
                    }

                }
            }
        }
        return true;
    }
}
