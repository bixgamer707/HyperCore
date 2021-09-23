package me.bixgamer707.hypercore.events;

import me.bixgamer707.hypercore.HyperCore;
import me.bixgamer707.hypercore.enums.Color;
import me.bixgamer707.hypercore.utils.YamlFile;
import org.bukkit.event.EventHandler;
import org.bukkit.util.Vector;
import org.bukkit.entity.Player;
import org.bukkit.Sound;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.Listener;

public class JumpPads implements Listener{
    private final HyperCore plugin;
    public JumpPads(HyperCore plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        YamlFile configuration = plugin.getEvents();
        if(!configuration.getBoolean("LAUNCH_PAD.enable")){
            return;
        }
        if(!configuration.getStringList("LAUNCH_PAD.enable-worlds").contains(player.getWorld().getName())){
            return;
        }
        if (player.getLocation().getBlock().getType() != Material.matchMaterial(configuration.getString("LAUNCH_PAD.MATERIAL"))) {
            return;
        }
        Vector vectah = player.getLocation().getDirection().multiply(2.0).setY(1.0);
        player.setVelocity(vectah);
        String[] split = configuration.getString("LAUNCH_PAD.SOUND").split(";");
        try{
            Sound sound = Sound.valueOf(split[0]);
            int vol = Integer.valueOf(split[1]);
            float pitch = Float.valueOf(split[2]);
            player.playSound(player.getLocation(),sound,vol,pitch);
        }catch (IllegalArgumentException e){
            plugin.getLogger().warning(Color.RED+"Warn!, "+Color.ORANGE+"The "+split[0]+" sound"+Color.RED+" is invalid.");
        }
    }
}
