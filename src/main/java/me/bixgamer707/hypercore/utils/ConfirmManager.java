package me.bixgamer707.hypercore.utils;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ConfirmManager {
    public static List<Player> players = new ArrayList<Player>();

    public static boolean containsPlayer(Player player) {
        return players.contains(player);
    }
    public static void addPlayer(Player player) {
        if(!containsPlayer(player)) {
            players.add(player);
        }
    }
    public static void removePlayer(Player player) {
        if(containsPlayer(player)) {
            players.remove(player);
        }
    }
    public static List<Player> getPlayers(){
        return players;
    }
}
