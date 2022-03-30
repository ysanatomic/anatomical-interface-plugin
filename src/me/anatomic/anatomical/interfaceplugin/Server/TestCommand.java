package me.anatomic.anatomical.interfaceplugin.Server;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.UUID;

public class TestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//        Bukkit.getPlayer( UUID.fromString("7f80c317-9ff9-4e8b-b9f8-adc61f100111")).sendMessage("hey");
//        Bukkit.getPlayer( UUID.fromString("7f80c317-9ff9-4e8b-b9f8-adc61f100111")).performCommand("help");
        Bukkit.getServer().broadcastMessage(String.valueOf(Bukkit.getServer().getOnlinePlayers().size()));
        return true;
    }
}
