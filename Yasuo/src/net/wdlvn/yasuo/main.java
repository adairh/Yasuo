package net.wdlvn.yasuo;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin implements Listener{
	public final String ANSI_RESET = "\033[0m";
	  public final String ANSI_BLACK = "\033[30m";
	  public final String ANSI_RED = "\033[31;1m";
	  public final String ANSI_GREEN = "\033[32;1m";
	  public final String ANSI_YELLOW = "\033[33m";
	  public final String ANSI_BLUE = "\033[34;1m";
	  public final String ANSI_PURPLE = "\033[35m";
	  public final String ANSI_CYAN = "\033[36m";
	  public final String ANSI_BRIGHT_CYAN = "\033[36;1m";
	  public final String ANSI_WHITE = "\033[37m";
	  public final String ANSI_BOLD = "\033[1m";
	public void onEnable() {
		getConfig().options().copyDefaults(true);
		 getServer().getPluginManager().registerEvents(this, this);
		 saveConfig();
		 getCommand("yasuo").setExecutor(new command(this));
		Bukkit.getServer().getLogger().info(ANSI_GREEN+"Enabled!"+ANSI_RESET);
		Bukkit.getServer().getLogger().info(ANSI_YELLOW+"Code by adairh"+ANSI_RESET);
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new ComboEvent(), this);
		pm.registerEvents(new Skill(this), this);
		pm.registerEvents(new ComBodo(this), this);
		
	}
}
