package me.anatomic.divictus.interfaceplugin.Server;

import me.anatomic.divictus.interfaceplugin.network.ChatMessage;
import me.anatomic.divictus.interfaceplugin.network.IssuedCommand;
import me.anatomic.divictus.interfaceplugin.network.PlayerOnlineOfflineStatus;
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
        ChatMessage msg = new ChatMessage("Server", event.getPlayer().getName() + " joined the server!");
        wsclient.ws.sendText(msg.jsonObj.toString());
        PlayerOnlineOfflineStatus toSend = new PlayerOnlineOfflineStatus(event.getPlayer().getName(), true, event.getPlayer().getUniqueId().toString());
        wsclient.ws.sendText(toSend.jsonObj.toString());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        ChatMessage msg = new ChatMessage("Server", event.getPlayer().getName() + " left the server!");
        wsclient.ws.sendText(msg.jsonObj.toString());
        wsclient.ws.sendText(String.format(event.getPlayer().getName() + " left the server!"));
        PlayerOnlineOfflineStatus toSend = new PlayerOnlineOfflineStatus(event.getPlayer().getName(), false, event.getPlayer().getUniqueId().toString());
        wsclient.ws.sendText(toSend.jsonObj.toString());
    }

    @EventHandler
    public void onPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event){
        IssuedCommand cmd = new IssuedCommand(event.getPlayer().getName(), event.getMessage());
        wsclient.ws.sendText(cmd.jsonObj.toString());

    }

}
