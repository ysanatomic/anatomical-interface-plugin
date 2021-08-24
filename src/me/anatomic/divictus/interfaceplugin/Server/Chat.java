package me.anatomic.divictus.interfaceplugin.Server;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.UUID;

public class Chat implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event){
        String eventMessage = event.getMessage();
        Player eventPlayer = event.getPlayer();
//        Bukkit.getLogger().info(String.format("[*] <%s> %s", eventPlayer.getPlayerListName(), eventMessage));
//
//        // testing issuing commands and messages
//
//        Bukkit.getPlayer( UUID.fromString("7f80c317-9ff9-4e8b-b9f8-adc61f100111")).sendMessage("/help");


    }

}
