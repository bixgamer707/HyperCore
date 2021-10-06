package me.bixgamer707.hypercore.utils;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import me.bixgamer707.hypercore.HyperCore;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class YamlFile extends YamlConfiguration {

    private final String fileName;

    private final HyperCore plugin;

    private File file;

    private File folder;

    public YamlFile(HyperCore plugin, String fileName, File folder) {
        this.folder = folder;
        this.plugin = plugin;
        this.fileName = fileName + (fileName.endsWith(".yml") ? "" : ".yml");
        createFile();
    }

    public YamlFile(HyperCore plugin, String fileName) {
        this(plugin, fileName, plugin.getDataFolder());
    }
    private void createFile() {
        try {
            this.file = new File(this.folder, this.fileName);
            if (file.exists()) {
                load(this.file);
                save(this.file);
                return;
            }
            if (this.plugin.getResource(this.fileName) != null) {
                this.plugin.saveResource(this.fileName, false);
            } else {
                save(this.file);
            }
            load(this.file);
        } catch (InvalidConfigurationException | IOException e) {
            this.plugin.getLogger().log(Level.SEVERE, "Creation of Configuration '" + this.fileName + "' failed.", e);
        }
    }

    public void delete() {
        if (!this.file.delete()) {
            this.plugin.getLogger().log(Level.SEVERE, "Error on delete the file '" + this.fileName + "'.");
        }
    }

    public void save() {

        if (folder == null) {
            folder = this.plugin.getDataFolder();
        }
        File file = new File(folder, this.fileName);
        try {
            save(file);
        } catch (IOException e) {
            this.plugin.getLogger().log(Level.SEVERE, "Save of the file '" + this.fileName + "' failed.", e);
        }
    }

    public void reload() {
        File folder = this.plugin.getDataFolder();
        File file = new File(folder, this.fileName);
        try {
            load(file);
        } catch (IOException | InvalidConfigurationException e) {
            this.plugin.getLogger().log(Level.SEVERE, "Reload of the file '" + this.fileName + "' failed.", e);
        }
    }
    public String getColoredString(String path) {
        return Utils.color(getString(path));
    }

    public List<String> getColoredStringList(String path) {

        List<String> stringList = getStringList(path);
        stringList.replaceAll(Utils::color);

        return stringList;
    }
}