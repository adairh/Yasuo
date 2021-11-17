package net.wdlvn.yasuo;
 
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.EquipmentSlot;


 public class ComboEvent
   implements Listener
 {
   public static List<Player> isCombo = new ArrayList<Player>();
   
  @EventHandler
   public void onInteract(PlayerInteractEvent e) {
     if (!isPlayerComboed(e.getPlayer())) {
       return;
     }
     if (e.getHand() != EquipmentSlot.HAND) {
       return;
     }
     
     e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_WOOD_BUTTON_CLICK_ON, 0.5F, 0.5F);
     if ((e.getAction() == Action.LEFT_CLICK_AIR) || (e.getAction() == Action.LEFT_CLICK_BLOCK)) {
       ComBodo.addOne(e.getPlayer(), 0);
       ComBodo.sendTitleCombo(e.getPlayer());
     }
     else if ((e.getAction() == Action.RIGHT_CLICK_AIR) || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
       ComBodo.addOne(e.getPlayer(), 1);
       ComBodo.sendTitleCombo(e.getPlayer());
     }
   }
   
   @EventHandler
   public void onSneak(PlayerToggleSneakEvent e) {
     if (e.isSneaking()) {
       addCombo(e.getPlayer());
     } else {
       ComBodo.doIt(e.getPlayer());
       huyCombo(e.getPlayer());
    }
   }
			
   public static boolean isPlayerComboed(Player player)
   {
    if (isCombo.contains(player))
      return true;
     return false;
   }
   
   public static void addCombo(Player player) {
     if (!isPlayerComboed(player)) {
       isCombo.add(player);
     }
   }
   
   public static void huyCombo(Player player) {
     if (isPlayerComboed(player)) {
      isCombo.remove(player);
     }
     ComBodo.finishCombo(player);
   }
 }
