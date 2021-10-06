package me.bixgamer707.hypercore;

import me.bixgamer707.hypercore.enums.Color;
import me.bixgamer707.hypercore.methods.Spawn;
import me.bixgamer707.hypercore.setup.SetupManager;
import me.bixgamer707.hypercore.utils.YamlFile;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HyperCore extends JavaPlugin {
    PluginDescriptionFile pdf = getDescription();
    public String version = pdf.getVersion();
    public String latestVersion;
    public static HyperCore instace;
    public static HyperCore getInstace() {
        return instace;
    }
    private YamlFile config;
    private YamlFile messages;
    private YamlFile events;
    private final Spawn spawn = new Spawn();
    public String getVersion() {
        return this.version;
    }
    public String latestVersion(){
        return this.latestVersion;
    }
    @Override
    public void onEnable(){
        this.config = new YamlFile(this,"config.yml");
        this.messages = new YamlFile(this,"messages.yml");
        this.events = new YamlFile(this,"events.yml");
        SetupManager sm = new SetupManager(this);
        sm.registerAll();
        checkUpdate();
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

    public Spawn getSpawn() {
        return this.spawn;
    }
    public void checkUpdate() {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(
                    "https://api.spigotmc.org/legacy/update.php?resource=96652").openConnection();
            int timed_out = 1250;
            con.setConnectTimeout(timed_out);
            con.setReadTimeout(timed_out);
            latestVersion = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
            if (latestVersion.length() <= 7) {
                if(!version.equals(latestVersion)){
                    getLogger().info(Color.RED+"There is a version available of");
                    getLogger().info(Color.YELLOW+"You can download it at:");
                    getLogger().info(Color.AQUA+"https://www.spigotmc.org/resources/sternal-hypercore-very-necessary.96652/");
                }
            }
        } catch (Exception ex) {
            getLogger().info(Color.RED+"Error while checking update.");
        }
    }
}
