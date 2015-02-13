package listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import me.bukkit.verocrate.Crates;


public class PlayerJoinListener implements Listener {
    Crates instance = Crates.getInstance();
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        instance.getKeyManager().addDefaultKeys(event.getPlayer());
    }
}