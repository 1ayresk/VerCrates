package listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import me.bukkit.verocrate.Crates;


public class PlayerInteractListener implements Listener {

    private Crates instance;
    public PlayerInteractListener(Crates instance){
        this.instance = instance;
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(event.getClickedBlock().getType() == Material.CHEST) {
                if (event.getClickedBlock().getState() instanceof Chest) {
                    if (instance.getCrateManager().getLoadedCrates().containsKey(event.getClickedBlock().getLocation())) {
                        String keytype = instance.getCrateManager().getLoadedCrates().get(event.getClickedBlock().getLocation());
                        event.setCancelled(true);
                        if(instance.getKeyManager().getKeys(player.getName(), keytype) > 0){
                            instance.getKeyManager().takeKeys(player.getName(), keytype, 1);
                            instance.getCrateManager().runRandomCrateTask(instance.getCrateManager().getCratesKey(event.getClickedBlock().getLocation()), event.getPlayer());
                            player.sendMessage(ChatColor.GREEN+"You have successfully used a "+keytype + " key to use a crate. You now have "+instance.getKeyManager().getKeys(player.getName(), keytype)+" keys left.");
                        }else{
                            player.sendMessage(ChatColor.RED+"Not enough keys!");
                        }
                    }
                }
            }
        }
    }
}