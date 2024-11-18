package com.felantry.tcentury;

import org.bukkit.entity.Player;

public class CenturyManager {

    private TCenturyPlugin plugin;

    public CenturyManager(TCenturyPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean canMoveToNextCentury(Player player, String century) {
        // Логика проверки, может ли игрок перейти в следующий век
        // Например, проверка на наличие нужных предметов в инвентаре
        return true;
    }

    public void moveToNextCentury(Player player, String century) {
        // Логика перехода на новый век, например, сохранение нового состояния в базе данных или конфигурации
        player.sendMessage("Поздравляем, вы перешли в " + century + " век!");
    }
}