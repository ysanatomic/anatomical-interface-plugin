package me.anatomic.divictus.interfaceplugin.Server;

import me.anatomic.divictus.interfaceplugin.network.ChatMessage;
import me.anatomic.divictus.interfaceplugin.network.Websockets;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class Players implements Listener {

    Websockets wsclient = null;

    public Players(Websockets client){
        wsclient = client;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Bukkit.getLogger().info(event.getPlayer().getName() + " joined the server!");
        System.out.println(wsclient);
        ChatMessage msg = new ChatMessage("", event.getPlayer().getName() + " joined the server!");
        wsclient.ws.sendText(msg.jsonObj.toString());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        Bukkit.getLogger().info(event.getPlayer().getName() + " left the server!");
        System.out.println(wsclient);
        ChatMessage msg = new ChatMessage("", event.getPlayer().getName() + " left the server!");
        wsclient.ws.sendText(msg.jsonObj.toString());
        wsclient.ws.sendText(String.format(event.getPlayer().getName() + " left the server!"));
    }

    @EventHandler
    public void onPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event){
        Bukkit.getLogger().info(String.format("[***] <%s> used the command %s", event.getPlayer().getPlayerListName(), event.getMessage()));
        wsclient.ws.sendText(String.format("[***] <%s> used the command %s", event.getPlayer().getPlayerListName(), event.getMessage()));

    }

}
