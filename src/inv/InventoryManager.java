package inv;

import me.bukkit.verocrate.Crates;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Random;

public class InventoryManager {

    public static ArrayList<IInventory> inventories = new ArrayList<IInventory>();
    static Random rand = new Random();

    public InventoryManager() {

    }

    public static void openInv(final Player player){
        IInventory inv = new IInventory();
        inv.setPlayer(player);
        final Inventory inventory = Bukkit.createInventory(null, 27, "Creat");
        inv.setInventory(inventory);
        player.openInventory(inventory);
        inventories.add(inv);

        new BukkitRunnable() {
            @Override
            public void run() {
                ItemStack temp = inventory.getItem(inventory.getSize() - 1);
                for (int i = 0; i < 27; i++) {
                    inventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) rand.nextInt(15)));
                }
                inventory.setItem(13, new ItemStack(Material.APPLE));
                player.updateInventory();
            }
        }.runTaskTimer(Crates.getInstance(), 5, 5);
    }


}
