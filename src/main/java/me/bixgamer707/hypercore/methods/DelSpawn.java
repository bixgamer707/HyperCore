package me.bixgamer707.hypercore.methods;

import org.bukkit.Location;

public class DelSpawn {

    private Location spawn;
    public DelSpawn(Location spawn){
        this.spawn = spawn;
    }

    public void delSpawn(){
        this.spawn = null;
    }
}
