package com.felantry.tcentury;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.FileConfiguration;

public class TCenturyGUI {

    private final TCenturyPlugin plugin;

    public TCenturyGUI(TCenturyPlugin plugin) {
        this.plugin = plugin;
    }

    public void openRequirementsMenu(Player player) {
        FileConfiguration guiConfig = plugin.getConfig(); // Используем главный конфиг

        String menuTitle = guiConfig.getString("menu_title");
        int menuSize = guiConfig.getInt("size");

        // Создаем инвентарь с заданным размером
        Inventory inventory = Bukkit.createInventory(null, menuSize, menuTitle);

        // Пример добавления предмета из конфигурации
        String materialName = guiConfig.getString("gold_block.material");
        Material material = Material.getMaterial(materialName);
        int slot = guiConfig.getInt("gold_block.slot");

        if (material != null) {
            ItemStack item = new ItemStack(material);
            ItemMeta meta = item.getItemMeta();

            if (meta != null) {
                meta.setDisplayName(guiConfig.getString("gold_block.lore"));
                item.setItemMeta(meta);
            }

            inventory.setItem(slot, item);
        }

        // Открыть меню игроку
        player.openInventory(inventory);
    }
}