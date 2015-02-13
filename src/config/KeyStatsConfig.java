package config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import me.bukkit.verocrate.Crates;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;


public class KeyStatsConfig {
    private static Crates instance;
    public KeyStatsConfig(Crates instance){
        this.instance = instance;
    }
    private FileConfiguration keyConfig = null;
    private File keyConfigFile = null;

    public void reloadConfig() {
        if (keyConfigFile == null) {
            keyConfigFile = new File(instance.getDataFolder(), "keys.yml");
        }
        keyConfig = YamlConfiguration.loadConfiguration(keyConfigFile);

        // Look for defaults in the jar
        InputStream defConfigStream = instance.getResource("keys.yml");
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            keyConfig.setDefaults(defConfig);
        }
    }
    public FileConfiguration getConfig() {
        if (keyConfig == null) {
            reloadConfig();
        }
        return keyConfig;
    }
    public void saveKeyConfig() {
        if (keyConfig == null || keyConfigFile == null) {
            return;
        }
        try {
            getConfig().save(keyConfigFile);
        } catch (IOException ex) {
            instance.getLogger().log(Level.SEVERE, "Could not save config to " + keyConfigFile, ex);
        }
    }
    public void saveDefaultKeyConfig() {
        System.out.println(instance);
        if (keyConfig == null) {
            keyConfigFile = new File(instance.getDataFolder(), "keys.yml");
        }
        if (!keyConfigFile.exists()) {
            instance.saveResource("keys.yml", false);
        }
    }
}