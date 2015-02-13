package Cmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import me.bukkit.verocrate.Crates;

import java.util.concurrent.ExecutionException;


public class CmdKeys implements CommandExecutor {
    private Crates instance = Crates.getInstance();
    @EventHandler
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("keys")) {
            if(sender.hasPermission("verocrates.admin")) { 
                if (args.length > 3) {
                    try {
                        String addTo = args[1];
                        if (args[0].equalsIgnoreCase("give")) {
                            instance.getKeyManager().addKeys(addTo, args[2], Integer.parseInt(args[3]));
                            sender.sendMessage("Added keys successfully.");
                        }
                        if (args[0].equalsIgnoreCase("take")) {
                            instance.getKeyManager().takeKeys(addTo, args[2], Integer.parseInt(args[3]));
                            sender.sendMessage("Taken keys successfully.");
                        }
                        if (args[0].equalsIgnoreCase("set")) {
                            instance.getKeyManager().setKeys(addTo, args[2], Integer.parseInt(args[3]));
                            sender.sendMessage("Set keys successfully.");
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
                if(sender instanceof Player){
                    Player player = (Player) sender;
                    if(args.length == 0){
                        if(player.hasPermission("verocrates.checkkeys")) {
                            player.sendMessage(ChatColor.RED+"Keys Remaining:");
                            for(String key: instance.getKeyManager().getLoadedKeys()){
                                player.sendMessage(ChatColor.RED+key+": " + instance.getKeyManager().getKeys(player.getName(), key));
                            }
                        }else{
                            player.sendMessage(ChatColor.RED+"You do not have permission to check keys.");
                        }
                    }
                }
            }
        }
        return false;
    }
}