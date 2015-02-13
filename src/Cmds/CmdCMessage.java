package Cmds;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdCMessage implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("cmessage")) {
            if (args.length > 2) {
                if (Bukkit.getServer().getPlayer(args[0]) != null) {
                    Player sendTo = Bukkit.getServer().getPlayer(args[0]);
                    sendTo.sendMessage(ChatColor.translateAlternateColorCodes('&', StringUtils.join(args, ' ', 1, args.length)));
                    return true;
                } else {
                    sender.sendMessage("Player not online.");
                }
            }
        }
        return false;
    }
}