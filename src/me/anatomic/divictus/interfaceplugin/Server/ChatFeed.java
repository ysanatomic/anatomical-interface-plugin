package me.anatomic.divictus.interfaceplugin.Server;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import me.anatomic.divictus.interfaceplugin.InterfacePlugin;
import me.anatomic.divictus.interfaceplugin.network.Websockets;

public class ChatFeed {


    public ChatFeed(ProtocolManager protocolManager, InterfacePlugin ctx, Websockets client) {

        protocolManager.addPacketListener(new PacketAdapter(ctx,
                ListenerPriority.NORMAL,
                PacketType.Play.Client.CHAT) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                if (event.getPacketType() == PacketType.Play.Client.CHAT) {
                    PacketContainer packet = event.getPacket();
                    String message = packet.getStrings().read(0);
                    System.out.println(event);
                    if (event.getPlayer() != null) {
                        System.out.println(String.format("[***] <%s> %s", event.getPlayer().getName(), message));
                        client.ws.sendText(String.format("[***] <%s> %s", event.getPlayer().getName(), message));
                    }
                    if (message.contains("shit")
                            || message.contains("damn")) {
                        event.setCancelled(true);
                        event.getPlayer().sendMessage("Bad manners!");
                    }
                }
            }
        });
    }
}
