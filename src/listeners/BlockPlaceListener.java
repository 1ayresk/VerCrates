package listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import me.bukkit.verocrate.Crates;


public class BlockPlaceListener implements Listener {
    private static Crates instance;
    public BlockPlaceListener(Crates instance){
        this.instance = instance;
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        Player player = event.getPlayer();
        if(instance.getCrateManager().isInCrateCreateMode(player)){
            if(event.getBlockPlaced().getType() == Material.CHEST) {
                instance.getCrateManager().addNewCrate(event.getBlock().getLocation(), instance.getCrateManager().getTypeToCreate(player));
                player.sendMessage(ChatColor.RED + "You have created a " + instance.getCrateManager().getTypeToCreate(player) + " crate at this location. Breaking it will not remove it! Remove it from the config(Making a command for it soon!).");
                instance.getCrateManager().addTemporaryCrate(event.getBlock().getLocation(), instance.getCrateManager().getTypeToCreate(player));
            }else{
                player.sendMessage(ChatColor.RED+"You are in crate creation mode. Please do /crate exit to place blocks.");
                event.setCancelled(true);
            }
        }
    }
}
