package me.bixgamer707.hypercore.setup;

import me.bixgamer707.hypercore.HyperCore;
import me.bixgamer707.hypercore.commands.*;
import me.bixgamer707.hypercore.commands.gamemode.*;
import me.bixgamer707.hypercore.commands.spawn.DelSpawnCommand;
import me.bixgamer707.hypercore.commands.spawn.SetSpawnCommand;
import me.bixgamer707.hypercore.commands.spawn.SpawnCommand;
import me.bixgamer707.hypercore.enums.Color;
import me.bixgamer707.hypercore.events.*;
import me.bixgamer707.hypercore.methods.Spawn;
import me.bixgamer707.hypercore.motd.ServerMotd;
import me.bixgamer707.hypercore.tabcomplete.*;
import me.bixgamer707.hypercore.utils.YamlFile;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.Arrays;
import java.util.logging.Logger;

@SuppressWarnings("all")
public class SetupManager {

    private final HyperCore plugin;

    public SetupManager(HyperCore plugin){
        this.plugin = plugin;
    }

    public void registerCommands(){
        plugin.getCommand("clearchat").setExecutor(new ChatClear(plugin));
        plugin.getCommand("msg").setExecutor(new MsgCommand(plugin));
        plugin.getCommand("hypercore").setExecutor(new MainCommand(plugin));
        plugin.getCommand("broadcast").setExecutor(new BroadcastCommand(plugin));
        plugin.getCommand("socialspy").setExecutor(new SocialSpy(plugin));
        plugin.getCommand("gamemode0").setExecutor(new SurvivalCommand(plugin));
        plugin.getCommand("gamemode1").setExecutor(new CreativeCommand(plugin));
        plugin.getCommand("gamemode2").setExecutor(new AdventureCommand(plugin));
        plugin.getCommand("gamemode3").setExecutor(new SpectatorCommand(plugin));
        plugin.getCommand("fly").setExecutor(new FlyCommand(plugin));
        plugin.getCommand("gamemode").setExecutor(new MainGameModeCommand(plugin));
        plugin.getCommand("setspawn").setExecutor(new SetSpawnCommand(plugin));
        plugin.getCommand("delspawn").setExecutor(new DelSpawnCommand(plugin));
        plugin.getCommand("spawn").setExecutor(new SpawnCommand(plugin));
    }
    public void registerEvents(){
        Arrays.asList(
                new BlockCommand(plugin),
                new BlockEvent(plugin),
                new JoinEvent(plugin),
                new JumpPads(plugin),
                new ChangeEvents(plugin),
                new ServerMotd(plugin),
                new ChatEvent(plugin)
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, plugin));
    }
    public void registerTabComplete(){
        plugin.getCommand("msg").setTabCompleter(new TabMsg());
        plugin.getCommand("hypercore").setTabCompleter(new Tab());
        plugin.getCommand("gamemode").setTabCompleter(new TabGamemode());
        plugin.getCommand("gamemode0").setTabCompleter(new TabSurvival());
        plugin.getCommand("gamemode1").setTabCompleter(new TabCreative());
        plugin.getCommand("gamemode2").setTabCompleter(new TabAdventure());
        plugin.getCommand("gamemode3").setTabCompleter(new TabSpectator());
    }
    public void registerAll(){
        getLogger().info(Color.WHITE+"----------------------------------"+Color.RESET);
        getLogger().info(Color.GREEN+"Has been activated "+Color.GRAY+"("+Color.BLUE+"version:"+Color.YELLOW+plugin.getDescription().getVersion()+Color.GRAY+")"+Color.RESET);
        getLogger().info(Color.CYAN+"Thanks for using the plugin, "+Color.AQUA+"~bixgamer707, xlsm4"+Color.RESET);
        getLogger().info(Color.WHITE+"----------------------------------"+Color.RESET);
        registerEvents();
        registerCommands();
        registerTabComplete();
        loadSpawn();
    }
    public void unRegisterAll(){
        getLogger().info(Color.WHITE+"----------------------------------"+Color.RESET);
        getLogger().info(Color.YELLOW+"Has been deactivate"+Color.RESET);
        getLogger().info(Color.RED+"Thanks for using the plugin!!"+Color.RESET);
        getLogger().info(Color.WHITE+"----------------------------------"+Color.RESET);
        saveSpawn();
    }
    public Logger getLogger(){
        return plugin.getLogger();
    }

    public void saveSpawn(){
        YamlFile data = new YamlFile(plugin,"data.yml");
        Spawn spawn = plugin.getSpawn();
        if(spawn.getSpawn() != null){
            data.set("Spawn.x",spawn.getSpawn().getX());
            data.set("Spawn.y",spawn.getSpawn().getY());
            data.set("Spawn.z",spawn.getSpawn().getZ());
            data.set("Spawn.pitch",spawn.getSpawn().getPitch());
            data.set("Spawn.yaw",spawn.getSpawn().getYaw());
            if(spawn.getSpawn().getWorld() == null){
                return;
            }
            data.set("Spawn.world",spawn.getSpawn().getWorld().getName());
        }
    }
    public void loadSpawn(){
        YamlFile data = new YamlFile(plugin,"data.yml");
        if(data.contains("Spawn.x")){
            double x = data.getDouble("Spawn.x");
            double y = data.getDouble("Spawn.y");
            double z = data.getDouble("Spawn.z");
            float pitch = data.getLong("Spawn.pitch");
            float yaw = data.getLong("Spawn.yaw");
            World world = Bukkit.getWorld(data.getString("Spawn.world"));
            plugin.getSpawn().getSetSpawn().setSpawn(new Location(world,x,y,z,yaw,pitch));
        }
    }
}
