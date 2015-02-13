package Util;

import org.bukkit.ChatColor;

import me.bukkit.verocrate.Crates;


public class MessageUtil {

    private static String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + "VeroCrates" + ChatColor.DARK_GRAY + "] ";
    private static String[] cratesMessage = {ChatColor.DARK_GRAY + "------------------", ChatColor.GREEN +""+ChatColor.BOLD+ "VeroCrates", ChatColor.GRAY + "You are running version " + ChatColor.BOLD+Crates.getInstance().getDescription().getVersion(), ChatColor.AQUA+"Msg xX_AyReSy_Xx In Game or Skype!", ChatColor.GRAY + "For help do /crates help", ChatColor.DARK_GRAY+"------------------"};


    public static String getPrefix() {
        return prefix;
    }

    public static String[] getCratesMessage() {
        return cratesMessage;
    }
}