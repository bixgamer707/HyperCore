package me.bixgamer707.hypercore.methods;

import org.bukkit.Location;

public class Setspawn {

    private Location setSpawn;
    public Setspawn(){
        this.setSpawn = null;
    }
    public Location getLocationSpawn(){
        return this.setSpawn;
    }
    public void setSpawn(Location loc){
        this.setSpawn = loc;
    }
    public boolean containsSpawn(){
        if(setSpawn != null){
            return true;
        }else{
            return false;
        }
    }
}
