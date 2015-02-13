package inv;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Created by Mark on 13/02/2015.
 */
public class IInventory {
    Player player;
    Inventory inventory;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
