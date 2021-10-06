package me.bixgamer707.hypercore.managers;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ConfirmManager {
    public static List<UUID> players = new ArrayList<UUID>();

    public static boolean containsPlayer(UUID player) {
        return players.contains(player);
    }
    public static void addPlayer(UUID player) {
        if(!containsPlayer(player)) {
            players.add(player);
        }
    }
    public static void removePlayer(UUID player) {
        if(containsPlayer(player)) {
            players.remove(player);
        }
    }
    public static List<UUID> getPlayers(){
        return players;
    }
}
