package me.anatomic.anatomical.interfaceplugin.Server;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import jdk.nashorn.internal.parser.JSONParser;
import me.anatomic.anatomical.interfaceplugin.network.Websockets;
import me.anatomic.anatomical.interfaceplugin.InterfacePlugin;
import me.anatomic.anatomical.interfaceplugin.network.ChatMessage;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChatFeed {


    public ChatFeed(ProtocolManager protocolManager, InterfacePlugin ctx, Websockets client) {

        Gson gson =  new GsonBuilder().create();

        protocolManager.addPacketListener(new PacketAdapter(ctx,
                ListenerPriority.NORMAL,
                PacketType.Play.Client.CHAT) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                if(client.ws != null){
                    if (event.getPacketType() == PacketType.Play.Client.CHAT) {
                        PacketContainer packet = event.getPacket();
                        String message = packet.getStrings().read(0);
                        if (event.getPlayer() != null) {
                            ChatMessage msg = new ChatMessage(event.getPlayer().getName(), message);
                            //  client.ws.sendText(String.format("[***] <%s> %s", event.getPlayer().getName(), message));
                            client.ws.sendText(msg.jsonObj.toString());
//                        client.ws.sendText(packet)
                        }
                    }
                }

            }
        });
    }
}
