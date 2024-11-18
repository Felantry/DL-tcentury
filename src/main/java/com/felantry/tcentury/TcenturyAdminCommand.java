package com.felantry.tcentury;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TcenturyAdminCommand implements CommandExecutor {

    private final TCenturyPlugin plugin;

    public TcenturyAdminCommand(TCenturyPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Usage: /tcentury reload or /tcentury version");
            return false;
        }

        String subCommand = args[0].toLowerCase();

        switch (subCommand) {
            case "reload":
                plugin.reloadConfig();
                sender.sendMessage("Конфигурация перезагружена.");
                break;
            case "version":
                sender.sendMessage("Текущая версия плагина: " + plugin.getDescription().getVersion());
                break;
            default:
                sender.sendMessage("Неизвестная команда. Используйте /tcentury reload или /tcentury version.");
                break;
        }

        return true;
    }
}