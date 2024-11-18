package com.felantry.tcentury;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class GuiManager {

    public Inventory getCenturyMenu(Player player) {
        // Создаем инвентарь с размером 9
        Inventory menu = Bukkit.createInventory(null, 9, "Century Menu");

        // Добавляем предметы в инвентарь (например, Diamond)
        menu.addItem(new ItemStack(Material.DIAMOND));

        return menu;
    }
}