package me.bukkit.verocrate;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;


public class CrateManager {
    private Crates instance;
    public CrateManager(Crates instance){
        this.instance = instance;
    }
    private static Map<String, String> crateCreate = new HashMap<String, String>();
    private static Map<Location, String> loadedCrates = new HashMap<Location, String>();
    
    public void removeFromCrateCreateMode(Player p){
        crateCreate.remove(p.getName());
    }

    
    public void addToCrateMode(Player p, String keytype){
        if(instance.getKeyManager().keyExists(keytype)){
            crateCreate.put(p.getName(), keytype);
        }
    }
    public String getTypeToCreate(Player p){
        return  crateCreate.get(p.getName());
    }
    
    public boolean isInCrateCreateMode(Player p){
        if(crateCreate.get(p.getName()) != null){
            return true;
        }else{
            return false;
        }
    }
    public String[] getCrates(){
        instance.saveConfig();
        String[] arr = instance.getConfig().getConfigurationSection("crates").getKeys(false).toArray(new String[instance.getConfig().getConfigurationSection("crates").getKeys(false).size()]);
        return arr;
    }

    public void addNewCrate(Location loc, String keytype){
        int size = this.getCrates().length+1;
        instance.getConfig().addDefault("crates.crate"+size+".world", loc.getWorld().getName());
        instance.getConfig().addDefault("crates.crate"+size+".X", loc.getX());
        instance.getConfig().addDefault("crates.crate"+size+".Y", loc.getY());
        instance.getConfig().addDefault("crates.crate"+size+".Z", loc.getZ());
        instance.getConfig().addDefault("crates.crate"+size+".type", keytype);
        instance.saveConfig();
    }
    public static Map<String, String> getCrateCreate() {
        return crateCreate;
    }
    public void loadCrates(){
        for(int i = 1; instance.getConfig().getConfigurationSection("crates").getKeys(false).size() >= i; i++){
            System.out.println(instance.getConfig().getString("crates.crate" + i + ".world"));
            String world = instance.getConfig().getString("crates.crate" + i + ".world");
            double x = instance.getConfig().getDouble("crates.crate"+i+".X");
            double y = instance.getConfig().getDouble("crates.crate"+i+".Y");
            double z = instance.getConfig().getDouble("crates.crate"+i+".Z");
            Location location = new Location(Bukkit.getWorld(world), x, y, z);
            String crateType = instance.getConfig().getString("crates.crate" + i + ".type");
            loadedCrates.put(location, crateType);
        }
        instance.getLogger().log(Level.INFO, loadedCrates.size()+" crates have been loaded.");
    }
    public static Map<Location, String> getLoadedCrates() {
        return loadedCrates;
    }
    public String getKeyType(Location loc){
        return this.getLoadedCrates().get(loc);
    }
    
    public void addTemporaryCrate(Location loc, String keytype){
        this.loadedCrates.put(loc, keytype);
    }

    public String getCratesKey(Location loc){
        return getLoadedCrates().get(loc);
    }

    
    public void runRandomCrateTask(String keytype, Player p){
        Random random = new Random();
        int loadedtasks = instance.getConfig().getConfigurationSection("keys."+keytype).getKeys(false).size();
        int taskid = random.nextInt(loadedtasks);
        for(String task: instance.getConfig().getStringList("keys."+keytype+".task"+taskid+".commands")){
            task = task.replace("{name}", p.getName());
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), task);
        }
    }
}