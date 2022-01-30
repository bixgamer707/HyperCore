package me.bixgamer707.hypercore.methods;

import org.bukkit.Location;

public class Spawn {
    private final Setspawn setspawn;

    public Spawn() {
        this.setspawn = new Setspawn();
    }

    public Location getSpawn() {
        return this.setspawn.getLocationSpawn();
    }

    public Setspawn getSetSpawn() {
        return this.setspawn;
    }
}
