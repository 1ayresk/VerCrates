package Util;

import org.bukkit.Location;

import me.bukkit.verocrate.Crates;

import java.util.HashMap;
import java.util.Map;


public class CrateUtil {
    private static Map<Location, String> loadedCrates = new HashMap<Location, String>();
    private Crates instance;
    private CrateUtil(Crates instance){
        this.instance = instance;
    }

}