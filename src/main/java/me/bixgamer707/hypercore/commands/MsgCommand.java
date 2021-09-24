package me.bixgamer707.hypercore.commands;

import me.bixgamer707.hypercore.HyperCore;
import me.bixgamer707.hypercore.utils.SocialSpyManager;
import me.bixgamer707.hypercore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MsgCommand implements CommandExecutor {
    private final HyperCore plugin;
    public MsgCommand(HyperCore plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender,Command command,String label,String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }else{
            Player player = (Player) sender;
            if(args.length > 0){
                Player tarjet = Bukkit.getPlayerExact(args[0]);
                if(tarjet != null){
                    StringBuilder message = new StringBuilder();
                    for(int i=1;i<=args.length-1;i++){
                        message.append(args[i]).append(" ");
                    }
                    player.sendMessage(Utils.color(plugin.getConfig().getString("Msg.send")+" &7"+message).replaceAll("%player%",tarjet.getName()));
                    tarjet.sendMessage(Utils.color(plugin.getConfig().getString("Msg.received")+" &7"+message).replaceAll("%player%",player.getName()));
                    for(Player players : SocialSpyManager.getPlayers()){
                        players.sendMessage(Utils.color(plugin.getConfig().getString("SocialSpy.msg")).replaceAll("%tarjet%",tarjet.getName()).replaceAll("%player%",player.getName())
                        .replaceAll("%msg%", message.toString()));
                    }
                }
            }
        }
        return true;
    }
}
