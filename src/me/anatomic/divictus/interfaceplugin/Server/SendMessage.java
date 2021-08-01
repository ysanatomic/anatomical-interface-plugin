package me.anatomic.divictus.interfaceplugin.Server;

import org.bukkit.Bukkit;

import java.util.UUID;

public class SendMessage {

    public SendMessage(UUID uuid, String msg){
        System.out.println("Hello here");
        if(msg.startsWith("/")){
            // execute command
        } else{
            System.out.println("Hello here 2");
            Bukkit.getPlayer(uuid).chat(msg);
        }
    }

}
