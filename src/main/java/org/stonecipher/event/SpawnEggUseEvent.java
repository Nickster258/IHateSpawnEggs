package org.stonecipher.event;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SpawnEggUseEvent implements Listener {

    private List<Material> eggTypes = new ArrayList<>();

    public SpawnEggUseEvent() {
        determineEggMaterials();
    }

    @EventHandler
    public void onSpawnEggUse(PlayerInteractEvent e){
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK && (e.getItem() != null) && eggTypes.contains(e.getItem().getType()) && e.getItem().hasItemMeta()) {
            e.setCancelled(true);
            ItemStack replacement = new ItemStack(e.getItem().getType(), e.getItem().getAmount());
            if (eggTypes.contains(e.getPlayer().getInventory().getItemInMainHand().getType())) {
                e.getPlayer().getInventory().setItemInMainHand(replacement);
            } else {
                e.getPlayer().getInventory().setItemInOffHand(replacement);
            }
            e.getPlayer().sendMessage(ChatColor.RED + "The spawn eggs you were attempting to use have been replaced.");
        }
    }

    private void determineEggMaterials() {
        for (Material type : Material.values()) {
            if (type.getKey().getKey().endsWith("_egg")) {
                eggTypes.add(type);
            }
        }
    }
}
