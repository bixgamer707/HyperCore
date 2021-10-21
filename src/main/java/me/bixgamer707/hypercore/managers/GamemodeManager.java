package me.bixgamer707.hypercore.managers;

import me.bixgamer707.hypercore.HyperCore;
import me.bixgamer707.hypercore.utils.Utils;
import me.bixgamer707.hypercore.utils.YamlFile;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class GamemodeManager {

    private static YamlFile messages;

    public GamemodeManager(HyperCore plugin) {
        messages = plugin.getMessages();
    }

    public static void change(Player player, GameMode gameMode) {
        player.setGameMode(gameMode);
        player.sendMessage(Utils.colorize(messages, messages.getString("gamemode.message"))
                .replaceAll("%gamemode%", String.valueOf(player.getGameMode())));
    }

    public static void change(Player player, String targetString, GameMode gameMode) {
        Player target = Bukkit.getPlayerExact(targetString);
        if (target == null) {
            player.sendMessage(Utils.colorize(messages, messages.getString("gamemode.playeroffline"))
                    .replaceAll("%playeroffline%", targetString));
            return;
        }
        target.setGameMode(gameMode);
        player.sendMessage(Utils.colorize(messages, messages.getString("gamemode.playermessage"))
                .replaceAll("%player%", player.getName())
                .replaceAll("%gamemode%", String.valueOf(player.getGameMode())));

        target.sendMessage(Utils.colorize(messages, messages.getString("gamemode.sendermessage"))
                .replaceAll("%player%", player.getName())
                .replaceAll("%gamemode%", String.valueOf(player.getGameMode())));

    }
}
