
package net.wdlvn.yasuo;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;


public class command implements CommandExecutor, Listener{
		private main plugin;
	    public command(main plugin){
	        this.plugin = plugin;
	    }
	@Override
	 public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	  {
	    plugin.getCommand("yasuo").setExecutor(this);
	    if (label.equalsIgnoreCase("yasuo"))
	    {
	      if (args.length >= 1) {
	        if (args[0].equalsIgnoreCase("reload"))
	        {
	          if (sender.hasPermission("yasuo.reload"))
	          {
	            plugin.reloadConfig();
	            plugin.saveConfig();
	            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',  "&aReloaded"));
	          }
	          else
	          {
	            sender.sendMessage(
	              ChatColor.translateAlternateColorCodes('&', "&4Lacking permission"));
	          }
	        }
	        
	      }else{
	        	sender.sendMessage(
	                    ChatColor.translateAlternateColorCodes('&', "Use &e/yasuo reload&f to reload plugin"));
	        }
	      return false;
	    }
	    return false;
	  }

}
