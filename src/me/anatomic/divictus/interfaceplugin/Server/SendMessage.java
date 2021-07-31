package me.anatomic.divictus.interfaceplugin.Server;

import org.bukkit.Bukkit;

import java.util.UUID;

public class SendMessage {

    public SendMessage(UUID uuid, String msg){
        if(msg.startsWith("/")){

        }
        Bukkit.getPlayer(uuid).sendMessage("msg");

    }

}
