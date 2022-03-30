package me.anatomic.anatomical.interfaceplugin.Server;

import org.bukkit.Bukkit;
import java.util.UUID;

public class CommandExecutor {

    public CommandExecutor(String cmd){
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), cmd);
    }

}
