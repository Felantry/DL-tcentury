package com.felantry.tcentury;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class TCenturyListener implements Listener {

    // Этот метод будет срабатывать при клике по инвентарю
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory menu = event.getInventory();
        Player player = (Player) event.getWhoClicked();

        // Проверяем, что это наш GUI
        if (menu.getCustomName() != null && menu.getCustomName().equals("Century Menu")) {
            event.setCancelled(true);  // Отменяем стандартное поведение

            if (event.getClick().isLeftClick()) {
                player.sendMessage("Вы выбрали предмет для левый клик.");
            } else if (event.getClick().isRightClick()) {
                player.sendMessage("Вы выбрали предмет для правый клик.");
            }
        }
    }
}