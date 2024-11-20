package com.felantry.tcentury;

import com.felantry.tcentury.commands.TCenturyCommand;
import com.felantry.tcentury.listeners.TCenturyListener;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;

public class TCenturyPlugin extends JavaPlugin {

    private static Economy economy = null;

    private File menuFile;
    private File craftingFile;
    private FileConfiguration menuConfig;
    private FileConfiguration craftingConfig;

    @Override
    public void onEnable() {
        getLogger().info("TCentury Plugin включен!");

        if (!setupEconomy()) {
            getLogger().severe("Vault не найден! Экономика отключена.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        createConfigFiles();

        getCommand("tcentury").setExecutor(new TCenturyCommand(this));
        getServer().getPluginManager().registerEvents(new TCenturyListener(this), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("TCentury Plugin отключен!");
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return economy != null;
    }

    public static Economy getEconomy() {
        return economy;
    }

    private void createConfigFiles() {
        menuFile = new File(getDataFolder(), "menu.yml");
        craftingFile = new File(getDataFolder(), "crafting.yml");

        if (!menuFile.exists()) {
            saveResource("menu.yml", false);
        }
        if (!craftingFile.exists()) {
            saveResource("crafting.yml", false);
        }

        menuConfig = YamlConfiguration.loadConfiguration(menuFile);
        craftingConfig = YamlConfiguration.loadConfiguration(craftingFile);
    }

    public FileConfiguration getMenuConfig() {
        return menuConfig;
    }

    public FileConfiguration getCraftingConfig() {
        return craftingConfig;
    }

    public void reloadConfigs() {
        menuConfig = YamlConfiguration.loadConfiguration(menuFile);
        craftingConfig = YamlConfiguration.loadConfiguration(craftingFile);
    }

    public void openCenturyMenu(Player player) {
        Inventory gui = Bukkit.createInventory(null, 27, menuConfig.getString("menu.title", "Century Menu"));
        int currentCentury = getCurrentCentury(player);

        for (String key : menuConfig.getConfigurationSection("menu.slots").getKeys(false)) {
            int slot = menuConfig.getInt("menu.slots." + key + ".id");
            Material material = Material.valueOf(menuConfig.getString("menu.slots." + key + ".material", "STONE"));
            String name = menuConfig.getString("menu.slots." + key + ".name", "Век " + key);

            ItemStack item = new ItemStack(material);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(name);
            meta.setLore(List.of("ПКМ: Сдать предметы", "ЛКМ: Проверить оставшееся"));
            item.setItemMeta(meta);

            if (Integer.parseInt(key) == currentCentury) {
                meta.setDisplayName("§a" + name + " (Текущий век)");
                item.setItemMeta(meta);
            }

            gui.setItem(slot, item);
        }

        player.openInventory(gui);
    }

    public int getCurrentCentury(Player player) {
        return player.getMetadata("century").isEmpty() ? 1 : player.getMetadata("century").get(0).asInt();
    }

    public boolean isItemAllowedInCentury(ItemStack item, int century) {
        List<String> allowedItems = craftingConfig.getStringList("centuries." + century + ".allowed-items");
        return allowedItems.contains(item.getType().name());
    }

    public void advanceCentury(Player player) {
        int currentCentury = getCurrentCentury(player);
        player.setMetadata("century", new FixedMetadataValue(this, currentCentury + 1));
        player.sendMessage("Поздравляем! Вы перешли в новый век: " + (currentCentury + 1));
    }
}