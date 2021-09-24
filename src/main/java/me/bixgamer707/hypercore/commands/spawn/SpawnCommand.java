package me.bixgamer707.hypercore.commands.spawn;

import me.bixgamer707.hypercore.HyperCore;
import me.bixgamer707.hypercore.methods.Spawn;
import me.bixgamer707.hypercore.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpawnCommand implements CommandExecutor {
    private HyperCore plugin;
    public SpawnCommand(HyperCore plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }else{
            Player player = (Player) sender;
            Spawn spawn = plugin.getSpawn();
            if(spawn.getSetSpawn().containsSpawn()){
                player.teleport(spawn.getSpawn());
                player.sendMessage(Utils.colorize(plugin.getMessages(), "&aHas sido teletransportado al spawn"));
                return true;
            }else{
                player.sendMessage(Utils.colorize(plugin.getMessages(), "&cEl spawn no esta definido!"));
            }
        }
        return true;
    }
}
