package com.felantry.tcentury.commands;

import com.felantry.tcentury.TCenturyPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TCenturyCommand implements CommandExecutor {

    private final TCenturyPlugin plugin;

    public TCenturyCommand(TCenturyPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                plugin.openCenturyMenu(player);
                return true;
            }
            sender.sendMessage("§cЭта команда доступна только игрокам.");
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("tcentury.reload")) {
                plugin.reloadConfigs();
                sender.sendMessage("§aКонфигурация плагина перезагружена!");
            } else {
                sender.sendMessage("§4У вас нет прав на выполнение этой команды.");
            }
            return true;
        }

        sender.sendMessage("§cНеизвестная команда.");
        return true;
    }
}