package me.bixgamer707.hypercore.events;

import me.bixgamer707.hypercore.HyperCore;
import me.bixgamer707.hypercore.utils.YamlFile;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.weather.WeatherChangeEvent;

public class ChangeEvents implements Listener {
    private final HyperCore plugin;

    public ChangeEvents(HyperCore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onItemDamage(EntityDamageEvent e) {
        YamlFile events = plugin.getEvents();
        if (e.getEntity() instanceof Item && events.getBoolean("disable-item-damage.enable") && events.getStringList("disable-item-damage.world").contains(e.getEntity().getWorld().getName()) && !e.isCancelled()) {
            e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onEntityExplode(EntityExplodeEvent e) {
        YamlFile events = plugin.getEvents();
        if (events.getBoolean("disable-entity-explode.enable") && events.getStringList("disable-entity-explode.world").contains(e.getEntity().getWorld().getName()) && !e.isCancelled()) {
            e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onEntityDamage(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        YamlFile events = plugin.getEvents();
        if (events.getBoolean("disable-damage.enable") && events.getStringList("disable-damage.world").contains(e.getEntity().getWorld().getName()) && !e.isCancelled()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        YamlFile events = plugin.getEvents();
        if (events.getBoolean("disable-hunger.enable") && events.getStringList("disable-hunger.world").contains(e.getEntity().getWorld().getName()) && !e.isCancelled()) {
            e.setCancelled(true);
            e.getEntity().setFoodLevel(20);
        }
    }

    @EventHandler
    public void onPlayerDeathMessage(PlayerDeathEvent e) {
        YamlFile events = plugin.getEvents();
        if (events.getBoolean("disable-death-message.enable") && events.getStringList("disable-death-message.world").contains(e.getEntity().getWorld().getName())) {
            e.setDeathMessage(null);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onCreatureSpawn(CreatureSpawnEvent e) {
        YamlFile events = plugin.getEvents();
        if (e.isCancelled()) {
            return;
        }
        if (e.getEntity() == null) {
            return;
        }
        if (e.getSpawnReason() != CreatureSpawnEvent.SpawnReason.NATURAL) {
            return;
        }
        if (events.getBoolean("disable-creature-spawn.enable") && events.getStringList("disable-creature-spawn.world").contains(e.getEntity().getWorld().getName())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDeathDrops(PlayerDeathEvent e) {
        YamlFile events = plugin.getEvents();
        if (events.getBoolean("clear-drops-on-death.enable") && events.getStringList("clear-drops-on-death.world").contains(e.getEntity().getWorld().getName())) {
            e.getDrops().clear();
        }
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e) {
        YamlFile events = plugin.getEvents();
        if (events.getBoolean("weather.enable") && events.getStringList("weather.world").contains(e.getWorld().getName())) {
            e.setCancelled(true);
        }
    }
}
