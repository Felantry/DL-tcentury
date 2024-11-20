package com.felantry.tcentury.listeners;

import com.felantry.tcentury.TCenturyPlugin;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.entity.Player;

public class TCenturyListener implements Listener {

    private final TCenturyPlugin plugin;

    public TCenturyListener(TCenturyPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(plugin.getMenuConfig().getString("menu.title", "Century Menu"))) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            player.sendMessage("Вы выбрали " + event.getCurrentItem().getType());
        }
    }

    @EventHandler
    public void onCraft(PrepareItemCraftEvent event) {
        Player player = (Player) event.getView().getPlayer();
        if (!plugin.isItemAllowedInCentury(event.getRecipe().getResult(), plugin.getCurrentCentury(player))) {
            event.getInventory().setResult(null);
            player.sendMessage("Этот предмет недоступен в вашем веке.");
        }
    }
}