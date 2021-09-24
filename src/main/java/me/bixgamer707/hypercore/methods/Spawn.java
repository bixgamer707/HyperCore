package me.bixgamer707.hypercore.methods;

import org.bukkit.Location;

public class Spawn {
    private final Setspawn setspawn;
    private final DelSpawn delSpawn;

    public Spawn(){
        this.setspawn = new Setspawn();
        this.delSpawn = new DelSpawn(getSpawn());
    }

    public Location getSpawn(){
        return this.setspawn.getLocationSpawn();
    }
    public Setspawn getSetSpawn(){
        return this.setspawn;
    }
    public DelSpawn getDelSpawn(){
        return this.delSpawn;
    }
}
