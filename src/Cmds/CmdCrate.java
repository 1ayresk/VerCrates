package Cmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import me.bukkit.verocrate.Crates;
import Util.MessageUtil;

import java.util.UUID;


public class CmdCrate implements CommandExecutor {
    Crates instance = Crates.getInstance();
    MessageUtil messageUtil = new MessageUtil();
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("crates")) { //Yeah /crate give {name} {key} {amount}
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length == 0) {
                    for(String cratesMessages: messageUtil.getCratesMessage()){
                        player.sendMessage(cratesMessages);
                    }
                }
                if(args.length > 0) {
                    if(args[0].equalsIgnoreCase("help")){
                        player.sendMessage(messageUtil.getPrefix()+"Do /crates to view your options");
                    }
                    if (args[0].equalsIgnoreCase("create")) {
                        if (player.hasPermission("verocrates.create")) {  //Crate create {keyname}
                            if (instance.getKeyManager().keyExists(args[1]) && !instance.getCrateManager().isInCrateCreateMode(player)) {
                                player.sendMessage(ChatColor.RED + "You have received a chest in your inventory." + ChatColor.GREEN + " Every chest you place will be treated and saved as a crate." + ChatColor.RED + " Do /crates exit to get out of crate mode!");
                                instance.getCrateManager().addToCrateMode(player, args[1]);
                                player.getInventory().addItem(new ItemStack(Material.CHEST));
                            } else {
                                player.sendMessage(messageUtil.getPrefix() + ChatColor.RED + "Error:You cannot create a crate!");
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("exit")) {
                        if (instance.getCrateManager().isInCrateCreateMode(player)) {
                            instance.getCrateManager().removeFromCrateCreateMode(player);
                            player.sendMessage(messageUtil.getPrefix() + ChatColor.RED + "You have been removed from crate mode.");
                        }
                    }
                }
            }
        }
        return false;
    }
}