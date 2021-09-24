// 
// Decompiled by Procyon v0.5.36
// 

package me.bixgamer707.hypercore.events;

import me.bixgamer707.hypercore.HyperCore;
import me.bixgamer707.hypercore.utils.Utils;
import me.bixgamer707.hypercore.utils.YamlFile;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.GameMode;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.Listener;

public class BlockEvent implements Listener
{
    public HyperCore plugin;
    public BlockEvent(HyperCore plugin) {
        this.plugin = plugin;
    }

    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        YamlFile events = plugin.getEvents();
        Player p = e.getPlayer();
        if (events.getBoolean("disable-block-break.enable") && events.getStringList("disable-block-break.world").contains(e.getBlock().getWorld().getName()) && !e.isCancelled()) {
            if (!e.getPlayer().isOp() && !e.getPlayer().hasPermission("BukkitShield.staff")) {
                e.setCancelled(true);
                p.sendMessage(Utils.colorize(plugin.getMessages(),events.getString("disable-block-break.message")));
                return;
            }
            if (e.getPlayer().getGameMode() == GameMode.CREATIVE) {
                e.setCancelled(false);
            }else {
                e.setCancelled(true);
            }
        }
    }
    
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        YamlFile events = plugin.getEvents();
        if (!events.getBoolean("disable-block-place.enable")) {
            return;
        }
        if(!events.getStringList("disable-block-place.world").contains(e.getBlock().getWorld().getName()) || events.getStringList("disable-block-place.world").contains("ALL")) {
            return;
        }
        if(e.isCancelled()) {
            return;
        }
        if(!e.getPlayer().isOp() || !e.getPlayer().hasPermission("HyperCore.placeblocks")) {
            e.setCancelled(true);
            return;
        }
        if(e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
            return;
        }
        e.setCancelled(true);
    }
    
    @EventHandler
    public void onBlockFire(BlockBurnEvent e) {
        YamlFile events = plugin.getEvents();
        if (events.getBoolean("disable-block-burn.enable") && events.getStringList("disable-block-burn.world").contains(e.getBlock().getWorld().getName()) && !e.isCancelled()) {
            e.setCancelled(true);
        }
    }
}
