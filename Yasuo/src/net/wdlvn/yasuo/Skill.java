package net.wdlvn.yasuo;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.connorlinfoot.titleapi.TitleAPI;


public class Skill implements Listener{

	private static main plugin;
    public Skill(main plugin){
        this.plugin = plugin;
    }
	@SuppressWarnings("deprecation")
	public static void useSkill(Player p, String message, double dmg) {
		Location loc = p.getLocation();
		TitleAPI.sendFullTitle(p, 10, 10, 10, ComBodo.colorDecomplier(message), "");
		p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ENDERDRAGON_HURT, 100, 100);
		loc.add(0.0D, 1.100000023841858D, 0.0D);
		for (int i = 0; i <= 10; i++)
	    {
	      loc.add(loc.getDirection().getX(), loc.getDirection().getY(), loc.getDirection().getZ());
	     int count = 0;
	      for (Entity en : getEntitiesByLocation(loc, 0 + 1.0F)) {
	        if ((en instanceof LivingEntity))
	        {
	        	LivingEntity target = (LivingEntity) en;
	          if (target != p)
	          {
		    	    		p.teleport(target);
	            count++;
	            new BukkitRunnable() {
	    	    	public void run() {
	    	    		target.damage(dmg, p);
	    	    		p.teleport(target);
	    	    	}
	    	    }.runTaskLater(plugin, 20);
	    	    new BukkitRunnable() {
	    	    	public void run() {
	    	    		target.damage(dmg, p);
	    	    		p.teleport(target);
	    	    	}
	    	    }.runTaskLater(plugin, 30);
	            target.damage(dmg, p);
	            p.getWorld().playEffect(loc, Effect.EXPLOSION, 152);
	            target.setVelocity(new Vector(target.getVelocity().getX(), 1D, 0.0D));
	            p.teleport(target);
	    	    new BukkitRunnable() {
	    	    	public void run() {
	    	    		p.teleport(target);
	    	    	}
	    	    }.runTaskLater(plugin, 10);
	    	    new BukkitRunnable() {
	    	    	public void run() {
	    	    		target.damage(dmg, p);
	    	    		p.teleport(target);
	    	    	}
	    	    }.runTaskLater(plugin, 20);
	    	    new BukkitRunnable() {
	    	    	public void run() {
	    	    		target.damage(dmg, p);
	    	    		p.teleport(target);
	    	    	}
	    	    }.runTaskLater(plugin, 30);
	    	    p.teleport(target);
	            target.damage(dmg, p);
	            p.getWorld().playEffect(loc, Effect.SMOKE, 152);
	            target.setVelocity(new Vector(target.getVelocity().getX(), 2D, 0.0D));
	            
	    		
	          }
	        }
	      }
	      if (count > 0) {
	        break;
	      }
	      if (loc.getBlock().getType().isSolid()) {
	        break;
	      }
	    }
		
		p.setVelocity(p.getLocation().getDirection().multiply(3));
		p.getWorld().playEffect(loc, Effect.ENDER_SIGNAL, 152);
	    
	  
	}
	public static List<Entity> getEntitiesByLocation(Location lo, float r) {
		List<Entity> listEn = new ArrayList<Entity>();
		for (Entity e : lo.getWorld().getEntities()) {
			Location locE = e.getLocation().add(0.0D, 0.75D, 0.0D);
			if (locE.distanceSquared(lo) <= r) {
				listEn.add(e);
			}
		}
		return listEn;
	}
	
	
	
	
	
private static  int[] combo = new int[3];
public static int[] getCombo()
   {
	 combo[0] = 0;
	 combo[1] = 1;
	 combo[2] = 0;
	 
	 return combo;
   }
}
