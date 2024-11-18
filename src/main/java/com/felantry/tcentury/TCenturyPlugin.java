package com.felantry.tcentury;

import org.bukkit.plugin.java.JavaPlugin;

public class TCenturyPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Регистрируем команду
        this.getCommand("tcenturies").setExecutor(new TCenturyCommand(this));

        // Регистрируем слушателя событий
        getServer().getPluginManager().registerEvents(new TCenturyListener(), this);
    }

    @Override
    public void onDisable() {
        // Логика выключения плагина (если необходимо)
    }
}