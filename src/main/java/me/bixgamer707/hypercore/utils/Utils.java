package me.bixgamer707.hypercore.utils;

import me.bixgamer707.hypercore.HyperCore;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    private static final Pattern HEX_PATTERN = Pattern.compile("#[a-fA-F0-9]{6}}");

    public static String sanitizeString(Player player, String text) {
        if (HyperCore.getInstace().getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            return color(PlaceholderAPI.setPlaceholders(player, text));
        }
        else {
            return color(text);
        }
    }

    public static String color(String text) {
        if (Bukkit.getVersion().contains("1.16")) {
            Matcher match = HEX_PATTERN.matcher(text);
            while (match.find()) {
                String color = text.substring(match.start(), match.end());
                text = text.replace(color, ChatColor.of(color) + "");
                match = HEX_PATTERN.matcher(text);
            }
        }
        return ChatColor.translateAlternateColorCodes('&', text);
    }
    public static String colorize(YamlFile messages, String text) {
        if (Bukkit.getVersion().contains("1.16")) {
            Matcher match = HEX_PATTERN.matcher(text);

            while (match.find()) {
                String color = text.substring(match.start(), match.end());
                text = text.replace(color, ChatColor.of(color) + "");
                match = HEX_PATTERN.matcher(text);
            }
        }

        return ChatColor.translateAlternateColorCodes('&', messages.getString("Prefix")+" "+text);
    }
}
