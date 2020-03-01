package dev.arantes.lib;

import org.apache.commons.io.FileUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class YamlConfig {
    private JavaPlugin plugin;
    private Logger logger;
    private String filename;
    private FileConfiguration fileConfiguration;
    private File file;

    public YamlConfig(String filename, JavaPlugin plugin) {
        this.plugin = plugin;
        this.logger = plugin.getLogger();
        this.filename = filename;

        this.init(null);
    }

    public YamlConfig(String filename, JavaPlugin plugin, File defaultResource) {
        this.plugin = plugin;
        this.logger = plugin.getLogger();
        this.filename = filename;

        this.init(defaultResource);
    }

    private void init(File defaultResource){
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        file = new File(plugin.getDataFolder(), filename);

        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();

                if (defaultResource != null && defaultResource.exists()){
                    FileUtils.copyFile(defaultResource, file);
                    logger.info(filename + " was copied from default resource.");
                }else {
                    file.createNewFile();
                    logger.info(filename + " was created.");
                }
            } catch (IOException e) {
                logger.warning("Couldn't create file " + filename);
            }
        }

        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getConfig() {
        return fileConfiguration;
    }

    public void save() {
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
        logger.info(filename + " was reloaded.");

    }

}