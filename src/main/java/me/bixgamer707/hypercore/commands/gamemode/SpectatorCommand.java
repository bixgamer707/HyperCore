package me.bixgamer707.hypercore.commands.gamemode;
import me.bixgamer707.hypercore.HyperCore;
import me.bixgamer707.hypercore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpectatorCommand implements CommandExecutor {
    private final HyperCore plugin;
    public SpectatorCommand(HyperCore plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }else{
            Player player = (Player) sender;
            if(args.length > 0){
                if(player.hasPermission("hypercore.gamemode.3") || player.hasPermission("hypercore.gamemode.*")){
                    Player tarjet = Bukkit.getPlayerExact(args[0]);
                    if(tarjet != null){
                        tarjet.setGameMode(GameMode.SPECTATOR);
                        player.sendMessage(Utils.colorize(plugin.getMessages(), plugin.getMessages().getString("gamemode.playermessage")).replaceAll("%player%",player.getName()));
                        tarjet.sendMessage(Utils.colorize(plugin.getMessages(), plugin.getMessages().getString("gamemode.sendermessage")).replaceAll("%player%",player.getName()));
                    }else{
                        player.sendMessage(Utils.colorize(plugin.getMessages(), plugin.getMessages().getString("gamemode.playeroffline")).replaceAll("%playeroffline%",args[0]));
                    }
                }else{
                    player.sendMessage(Utils.colorize(plugin.getMessages(), plugin.getMessages().getString("no-permission")).replaceAll("%player%",player.getName()));
                }
            }else{
                if(player.hasPermission("hypercore.gamemode.3") || player.hasPermission("hypercore.gamemode.*")){
                    player.setGameMode(GameMode.SPECTATOR);
                    player.sendMessage(Utils.colorize(plugin.getMessages(), plugin.getMessages().getString("gamemode.message")).replaceAll("%gamemode%", String.valueOf(player.getGameMode())));
                }else{
                    player.sendMessage(Utils.colorize(plugin.getMessages(), plugin.getMessages().getString("no-permission")).replaceAll("%player%",player.getName()));
                }
            }
        }
        return true;
    }
}
