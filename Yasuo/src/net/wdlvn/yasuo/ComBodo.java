package net.wdlvn.yasuo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.scheduler.BukkitRunnable;

import com.connorlinfoot.actionbarapi.ActionBarAPI;

import net.md_5.bungee.api.ChatColor;

public class ComBodo implements Listener{
	private static main plugin;
	  
	  public ComBodo(main plugin)
	  {
	    this.plugin = plugin;
	  }
	  
	public static HashMap<Player, List<Integer>> combo = new HashMap<Player, List<Integer>>();
	   
	   public int getTimeOfClick(Player player) {
	     if (!combo.containsKey(player)) {
	       return 0;
	    }
	     return (combo.get(player)).size();
	   }
	   
	   public static void addOne(Player player, int i) {
	     if (combo.containsKey(player)) {
	       List<Integer> list = combo.get(player);
	       list.add(Integer.valueOf(i));
	       combo.put(player, list);
	     }
	     else {
	       List<Integer> list = new ArrayList<Integer>();
	       list.add(Integer.valueOf(i));
	       combo.put(player, list);
	     }
	   }
	  
	public static void finishCombo(Player player)
	  {
	     combo.remove(player);
	     ActionBarAPI.sendActionBar(player, colorDecomplier(" "));
	   }
	  
	   public static boolean balancingCombo(Player player, int[] i) {
	    if (!combo.containsKey(player)) {
	      return false;
	    }
	     
	     if ((combo.get(player)).size() != i.length) {
	      return false;
	    }
	     boolean check = true;
	     for (int i2 = 0; i2 < (combo.get(player)).size(); i2++) {
	       if (i[i2] != ((Integer)(combo.get(player)).get(i2)).intValue()) {
	        check = false;
	        break;
	      }
	    }
	    return check;
	  }
	  
	public static void sendTitleCombo(Player player)
	   {
	     List<String> comboList = new ArrayList<String>();
	     for (Iterator<?> localIterator1 = (combo.get(player)).iterator(); localIterator1.hasNext();) { int i = ((Integer)localIterator1.next()).intValue();
	       if (i == 0) {
	         comboList.add("&6TRÁI" + ChatColor.GREEN);
	       } else
	         comboList.add("&6PHẢI" + ChatColor.GREEN);
	    }
	    String mess = "";
	    for (String s : comboList) {
	      mess = mess + " - " + s;
	     }
	     mess = mess + " - ";
	     mess = "&a" + mess;
	     ActionBarAPI.sendActionBar(player, colorDecomplier(mess));
	   }
	
	  public static String colorDecomplier(String s) {
		   return ChatColor.translateAlternateColorCodes('&', s);
		}
	  
	  public static void comboSkill(Player player)
	   {
		  if (balancingCombo(player, Skill.getCombo())) {
			  if (player.hasPermission("yasuo.use")) {
				  if (!isPLayerHasCooldown(player.getUniqueId()).booleanValue()){
		  				createCooldown(player.getUniqueId());
						Skill.useSkill(player,plugin.getConfig().getString("Message"),plugin.getConfig().getInt("Damage"));
						fall.add(player.getName());
						finishCombo(player);
				       System.out.println("Yasuo - " + player.getName());
				  }
				  else{
			    		player.sendMessage(ChatColor.translateAlternateColorCodes('&',
			    				plugin.getConfig().getString("CoolDown.Message").replace("%s%", String.valueOf(plugin.getConfig().getInt("CoolDown.Duration")))));
			    	}
			    }
			}           
		}
	  private static List<UUID> cooldown = new ArrayList<UUID>();
	    public static Boolean isPLayerHasCooldown(UUID u)
		  {
		    Boolean c = Boolean.valueOf(false);
		    for (UUID uo : cooldown) {
		      if (uo.equals(u))
		      {
		        c = Boolean.valueOf(true);
		        System.currentTimeMillis();
		        
		        break;
		      }
		    }
		    return c;
		  }
		private static void createCooldown(final UUID uuid)

		  {
		    cooldown.add(uuid);
		    new BukkitRunnable()
		    {
		      public void run()
		      {
		        cooldown.remove(uuid);
		        Bukkit.getPlayer(uuid).sendMessage(ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("CoolDown.End")));
		      }
		    }
		    
		      .runTaskLaterAsynchronously(plugin, plugin.getConfig().getInt("CoolDown.Duration") * 20);
		  }
	  public static void doIt(Player player)
		  {
		    comboSkill(player);
		   }
	  @EventHandler
		public void onFall(EntityDamageEvent e) {
			if (e.getEntity() instanceof Player) {
				Player p = (Player) e.getEntity();
				if (e.getCause() == DamageCause.FALL) {
					if (fall.contains(p.getName())) {
						e.setCancelled(true);
						fall.remove(p.getName());
					}
				}
			}
		}
		
		 
			public static List<String> fall = new ArrayList<String>();
		
		
		
}
