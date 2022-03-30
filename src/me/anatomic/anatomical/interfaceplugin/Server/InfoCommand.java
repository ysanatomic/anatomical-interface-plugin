package me.anatomic.anatomical.interfaceplugin.Server;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class InfoCommand implements CommandExecutor {



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Bukkit.getServer().dispatchCommand(
                Bukkit.getConsoleSender(),
                "tellraw " + sender.getName() +
                        " {text:\"Interface allowing Staff Members to manage and monitor the network. \", \"color\": \"blue\", \"bold\": \"true\"}");
        Bukkit.getServer().dispatchCommand(
                Bukkit.getConsoleSender(),
                "tellraw " + sender.getName() +
                        " {text:\"Made by Anatomic. \", \"color\": \"red\", \"bold\": \"true\"}");
        return true;
    }
}