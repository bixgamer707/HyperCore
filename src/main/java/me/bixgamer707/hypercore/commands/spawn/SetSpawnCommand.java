package me.bixgamer707.hypercore.commands.spawn;

import me.bixgamer707.hypercore.HyperCore;
import me.bixgamer707.hypercore.methods.Spawn;
import me.bixgamer707.hypercore.utils.ConfirmManager;
import me.bixgamer707.hypercore.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetSpawnCommand implements CommandExecutor {
    private final HyperCore plugin;
    public SetSpawnCommand(HyperCore plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }else{
            Player player = (Player) sender;
            Spawn spawn = plugin.getSpawn();
            if(!spawn.getSetSpawn().containsSpawn()){
                spawn.getSetSpawn().setSpawn(player.getLocation());
                player.sendMessage(Utils.colorize(plugin.getMessages(), "&aHas establecido el spawn correctamente!"));
                return true;
            }else{
                if(!ConfirmManager.containsPlayer(player)){
                    ConfirmManager.addPlayer(player);
                }
                player.sendMessage(Utils.colorize(plugin.getMessages(), "&cYa existe un spawn definido!, &7Si quieres cambiarlo debes escribir en el chat '&aConfirm&7'"));
                player.sendMessage(Utils.colorize(plugin.getMessages(), "&7Si no quieres confirmar escribe '&cCancel&7'"));
            }
        }
        return true;
    }
}
