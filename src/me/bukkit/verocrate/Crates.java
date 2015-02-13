package me.bukkit.verocrate;

import org.bukkit.plugin.java.JavaPlugin;
import Cmds.CmdCMessage;
import Cmds.CmdCrate;
import Cmds.CmdKeys;
import config.KeyStatsConfig;
import Keys.KeyManager;
import listeners.BlockPlaceListener;
import listeners.PlayerInteractListener;
import listeners.PlayerJoinListener;

import java.util.ArrayList;
import java.util.List;


public class Crates extends JavaPlugin{

    private static Crates instance;
    private static List<String> loadedKeyNames = new ArrayList<String>();
    KeyStatsConfig ksc = new KeyStatsConfig(this);
    private KeyManager keymanager = new KeyManager(this);
    private CrateManager crateManager = new CrateManager(this);
    public void onEnable(){
        instance = this;
        this.saveDefaultConfig();
        this.getConfig().options().copyDefaults(true);
        getCommand("crates").setExecutor(new CmdCrate());
        getCommand("cmessage").setExecutor(new CmdCMessage());
        getCommand("keys").setExecutor(new CmdKeys());
        ksc.saveDefaultKeyConfig();
        ksc.getConfig().options().copyDefaults(true);
        this.getCrateManager().loadCrates();
        this.registerEvents();
    }

    public void onDisable(){
        instance = null;
    }
    public static Crates getInstance() {
        return instance;
    }
    public void registerEvents(){
        this.getServer().getPluginManager().registerEvents(new PlayerInteractListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        this.getServer().getPluginManager().registerEvents(new BlockPlaceListener(this), this);
    }

    public static List<String> getLoadedKeyNames() {
        return loadedKeyNames;
    }

    public KeyStatsConfig getKeyConfig() {
        return ksc;
    }

    public KeyManager getKeyManager() {
        return keymanager;
    }

    public CrateManager getCrateManager() {
        return crateManager;
    }
}