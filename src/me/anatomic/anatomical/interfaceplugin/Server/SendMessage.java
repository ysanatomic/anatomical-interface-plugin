package me.anatomic.anatomical.interfaceplugin.Server;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.io.Console;
import java.util.UUID;

public class SendMessage {

    public SendMessage(UUID uuid, String msg){
        if(msg.startsWith("/")){
            // execute command
        } else{
            String playerName = Bukkit.getOfflinePlayer(uuid).getName();
            Bukkit.getServer().dispatchCommand(
                    Bukkit.getConsoleSender(),
                    "tellraw @a" +
                            " {text:\"[" + playerName + "] " + msg + "\", \"color\": \"green\", \"bold\": \"true\"}");
//            if(Bukkit.getServer().getPlayer(uuid).isOnline()){
//                Player player = Bukkit.getPlayer(uuid);
//                Bukkit.getServer().dispatchCommand(
//                        Bukkit.getConsoleSender(),
//                        "tellraw @a" +
//                                " {text:\"[" + player.getName() + "] " + msg + "\", \"color\": \"blue\", \"bold\": \"true\"}");
//            }
//            else{
//                OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
//                Bukkit.getServer().dispatchCommand(
//                        Bukkit.getConsoleSender(),
//                        "tellraw @a" +
//                                " {text:\"[" + player.getName() + "] " + msg + "\", \"color\": \"blue\", \"bold\": \"true\"}");
//            }
        }
    }

}
