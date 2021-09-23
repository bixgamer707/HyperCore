package me.bixgamer707.hypercore;

import me.bixgamer707.hypercore.setup.SetupManager;
import me.bixgamer707.hypercore.utils.YamlFile;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;

public class HyperCore extends JavaPlugin {
    private YamlFile config;
    private YamlFile messages;
    private YamlFile events;
    public static HyperCore instace;
    public static HyperCore getInstace() {
        return instace;
    }
    public static HashMap<Player, Integer> cooldownTime;
    public static HashMap<Player, BukkitRunnable> cooldownTask;
    @Override
    public void onEnable(){
        this.config = new YamlFile(this,"config.yml");
        this.messages = new YamlFile(this,"messages.yml");
        this.events = new YamlFile(this,"events.yml");
        SetupManager sm = new SetupManager(this);
        sm.registerAll();
    }
    @Override
    public void onDisable(){
        SetupManager sm = new SetupManager(this);
        sm.unRegisterAll();
    }
    public YamlFile getConfig() {
        return config;
    }
    public YamlFile getMessages() {
        return messages;
    }
    public YamlFile getEvents() {
        return events;
    }
}
