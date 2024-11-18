package com.felantry.tcentury;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;  // Импортируем правильный пакет

public class TCenturyCommand implements CommandExecutor {

    private final TCenturyPlugin plugin;

    public TCenturyCommand(TCenturyPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            // Создаём инвентарь с названием "Century Menu"
            Inventory menu = createCenturyMenu();

            // Открываем инвентарь для игрока
            player.openInventory(menu);
            return true;
        }
        return false;
    }

    // Метод для создания инвентаря с нужными предметами
    private Inventory createCenturyMenu() {
        Inventory inv = Bukkit.createInventory(null, 9, "Century Menu");

        // Пример добавления предмета с кастомными метками
        ItemStack item = new ItemStack(Material.DIAMOND);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            NamespacedKey key = new NamespacedKey("tcentury", "menu");
            meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "century_menu");
            item.setItemMeta(meta);
        }

        inv.addItem(item);
        return inv;
    }
}