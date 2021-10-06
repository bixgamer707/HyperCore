package me.bixgamer707.hypercore.commands;

import me.bixgamer707.hypercore.HyperCore;
import me.bixgamer707.hypercore.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {
    private final HyperCore plugin;
    public FlyCommand(HyperCore plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }else{
            Player player = (Player) sender;
            if(!player.getAllowFlight()){
                player.setAllowFlight(true);
                player.sendMessage(Utils.colorize(plugin.getMessages(), plugin.getMessages().getString("fly.commandon")));
            }else{
                player.setAllowFlight(false);
                player.sendMessage(Utils.colorize(plugin.getMessages(), plugin.getMessages().getString("fly.commandoff")));
            }
        }
        return true;
    }
}
