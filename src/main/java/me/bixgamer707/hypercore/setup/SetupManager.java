package me.bixgamer707.hypercore.setup;

import me.bixgamer707.hypercore.HyperCore;
import me.bixgamer707.hypercore.commands.*;
import me.bixgamer707.hypercore.commands.gamemode.*;
import me.bixgamer707.hypercore.enums.Color;
import me.bixgamer707.hypercore.events.*;
import me.bixgamer707.hypercore.motd.ServerMotd;
import me.bixgamer707.hypercore.tabcomplete.*;
import org.bukkit.Bukkit;
import java.util.Arrays;
import java.util.logging.Logger;

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
    }
    public void registerEvents(){
        Arrays.asList(
                new BlockCommand(plugin),
                new BlockEvent(plugin),
                new JoinEvent(plugin),
                new JumpPads(plugin),
                new ChangeEvents(plugin),
                new ServerMotd(plugin)
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
        registerCommands();
        registerTabComplete();
        registerEvents();
    }
    public void unRegisterAll(){
        getLogger().info(Color.WHITE+"----------------------------------"+Color.RESET);
        getLogger().info(Color.YELLOW+"Has been deactivate"+Color.RESET);
        getLogger().info(Color.RED+"Thanks for using the plugin!!"+Color.RESET);
        getLogger().info(Color.WHITE+"----------------------------------"+Color.RESET);
    }
    public Logger getLogger(){
        return plugin.getLogger();
    }
}
