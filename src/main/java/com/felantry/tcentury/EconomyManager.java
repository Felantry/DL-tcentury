package com.felantry.tcentury;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

public class EconomyManager {

    private final TCenturyPlugin plugin;
    private Economy economy;

    public EconomyManager(TCenturyPlugin plugin) {
        this.plugin = plugin;
        setupEconomy();
    }

    // Настройка экономики через Vault
    private void setupEconomy() {
        if (plugin.getServer().getPluginManager().getPlugin("Vault") != null) {
            RegisteredServiceProvider<Economy> economyProvider = plugin.getServer().getServicesManager().getRegistration(Economy.class);
            if (economyProvider != null) {
                economy = economyProvider.getProvider();  // Получаем экономию
            }
        }
    }

    // Проверка, есть ли у игрока достаточно денег
    public boolean hasSufficientFunds(Player player, double amount) {
        if (economy != null) {
            double balance = economy.getBalance(player);  // Получаем баланс игрока
            return balance >= amount;  // Если денег достаточно, возвращаем true
        }
        return false;
    }

    // Списание денег с баланса игрока
    public void deductFunds(Player player, double amount) {
        if (economy != null) {
            economy.withdrawPlayer(player, amount);  // Списываем деньги
        }
    }

    // Добавление денег на баланс игрока
    public void addFunds(Player player, double amount) {
        if (economy != null) {
            economy.depositPlayer(player, amount);  // Добавляем деньги
        }
    }
}