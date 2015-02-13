package Keys;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import me.bukkit.verocrate.Crates;
import Exceptions.NotValidKeyException;
import Exceptions.UserNotFoundException;

import java.util.Arrays;


public class KeyManager {
    private static Crates instance;

    public KeyManager(Crates instance){
        this.instance = instance;
    }

 
    public String[] getLoadedKeys(){
        String[] arr = instance.getConfig().getConfigurationSection("keys").getKeys(false).toArray(new String[instance.getConfig().getConfigurationSection("keys").getKeys(false).size()]);
        return arr;
    }
    
    public void addDefaultKeys(Player p){
        for(String keyname: getLoadedKeys()){
            if(instance.getKeyConfig().getConfig().getString(p.getUniqueId()+"."+keyname) == null){
                instance.getKeyConfig().getConfig().addDefault(p.getUniqueId()+"."+keyname, "0");
                instance.getKeyConfig().saveKeyConfig();
            }
        }
    }



    public int getKeys(String playername, String keytype){
        return instance.getKeyConfig().getConfig().getInt( Bukkit.getOfflinePlayer(playername).getUniqueId()+"."+keytype);
    }

   
    public void addKeys(String playerName, String keyType, int amount){
        if(Arrays.asList(getLoadedKeys()).contains(keyType)){
            if(instance.getKeyConfig().getConfig().contains(Bukkit.getOfflinePlayer(playerName).getUniqueId().toString())){
                instance.getKeyConfig().getConfig().set(Bukkit.getOfflinePlayer(playerName).getUniqueId()+"."+keyType, instance.getKeyConfig().getConfig().getInt(Bukkit.getOfflinePlayer(playerName).getUniqueId()+"."+keyType)+amount);
                instance.getKeyConfig().saveKeyConfig();
            }else{
                throw new UserNotFoundException("User not found.");
            }
        }else{
            throw new NotValidKeyException("Not a valid key type!");
        }
    }
    public void takeKeys(String playername, String keytype, int amount){
        if(Arrays.asList(getLoadedKeys()).contains(keytype)){
            if(instance.getKeyConfig().getConfig().contains(Bukkit.getOfflinePlayer(playername).getUniqueId().toString())){
                instance.getKeyConfig().getConfig().set(Bukkit.getOfflinePlayer(playername).getUniqueId()+"."+keytype, instance.getKeyConfig().getConfig().getInt(Bukkit.getOfflinePlayer(playername).getUniqueId()+"."+keytype)-amount);
                instance.getKeyConfig().saveKeyConfig();
            }else{
                throw new UserNotFoundException("User not found.");
            }
        }else{
            throw new NotValidKeyException("Not a valid key type!");
        }
    }
    public void setKeys(String playername, String keytype, int amount){
        if(Arrays.asList(getLoadedKeys()).contains(keytype)){
            if(instance.getKeyConfig().getConfig().contains(Bukkit.getOfflinePlayer(playername).getUniqueId().toString())){
                instance.getKeyConfig().getConfig().set(Bukkit.getOfflinePlayer(playername).getUniqueId()+"."+keytype, amount);
                instance.getKeyConfig().saveKeyConfig();
            }else{
                throw new UserNotFoundException("User not found.");
            }
        }else{
            throw new NotValidKeyException("Not a valid key type!");
        }
    }
    public boolean keyExists(String keytype){
        if(Arrays.asList(getLoadedKeys()).contains(keytype)) {
            return true;
        }else{
            return false;
        }
    }
}