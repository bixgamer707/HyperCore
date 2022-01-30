package me.bixgamer707.hypercore.commands;

import me.bixgamer707.hypercore.HyperCore;
import me.bixgamer707.hypercore.managers.SocialSpyManager;
import me.bixgamer707.hypercore.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SocialSpy implements CommandExecutor {
    public static boolean toggle;
    private final HyperCore plugin;

    public SocialSpy(HyperCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        } else {
            Player player = (Player) sender;
            if (player.hasPermission("hypercore.socialspy")) {
                if (getToggle()) {
                    setToggle(false);
                    if (SocialSpyManager.containsPlayer(player)) {
                        SocialSpyManager.removePlayer(player);
                    }
                    sender.sendMessage(Utils.colorize(plugin.getMessages(), plugin.getMessages().getString("SocialSpy.socialspy-deactivate")));
                } else {
                    setToggle(true);
                    if (!SocialSpyManager.containsPlayer(player)) {
                        SocialSpyManager.addPlayer(player);
                    }
                    sender.sendMessage(Utils.colorize(plugin.getMessages(), plugin.getMessages().getString("SocialSpy.socialspy-activate")));
                }
            }
        }
        return true;
    }

    public static boolean getToggle() {
        return toggle;
    }

    public static void setToggle(boolean toggle) {
        SocialSpy.toggle = toggle;
    }
}
