package me.anatomic.anatomical.interfaceplugin;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.reflect.StructureModifier;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import me.anatomic.anatomical.interfaceplugin.Server.*;
import me.anatomic.anatomical.interfaceplugin.network.Websockets;
import me.anatomic.anatomical.interfaceplugin.Server.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.ProtocolLibrary;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;


public class InterfacePlugin extends JavaPlugin implements Listener {

    private ProtocolManager protocolManager;
    private JSONParser parser = new org.json.simple.parser.JSONParser();
    public ArrayList<Player> reportPlayersOnTimeout = new ArrayList<Player>();




    @Override
    public void onEnable() {
        System.out.println("[AnatomicalInterfacePlugin] Plugin is enabled.");
        System.out.println("[AnatomicalInterfacePlugin] Developed by Anatomic.");
        this.saveConfig();
        if(!this.getConfig().isSet("interfaceURL")){
            this.getConfig().set("interfaceURL", "localhost:8000");
        }
        if(!this.getConfig().isSet("authToken")){
            this.getConfig().set("authToken", "insertyourtokenhere");
        }
        if(!this.getConfig().isSet("HttpOrHttps")){
            this.getConfig().set("HttpOrHttps", "http");
        }
        if(!this.getConfig().isSet("WsOrWss")){
            this.getConfig().set("WsOrWss", "ws");
        }
        this.saveConfig();
        protocolManager = ProtocolLibrary.getProtocolManager();
//        this.getCommand("kit").setExecutor(new TestCommand());

//        new WSWrapper(clientPointer);s

//        client = new WebSocketClient({})

        Websockets ws = new Websockets();
        ws.startWebSocket(this);
        ws.startMainWebSocketListener(this);

        getServer().getPluginManager().registerEvents(new Chat(), this);
        getServer().getPluginManager().registerEvents(new Players(ws), this);
        ChatFeed chatfeed = new ChatFeed(protocolManager, this, ws);

        this.getCommand("notes").setExecutor(new ViewInfoCommand(ws, this));
        this.getCommand("newnote").setExecutor(new NewNoteCommand(ws));
        this.getCommand("report").setExecutor(new ReportCommand(ws, this));
        this.getCommand("blockreports").setExecutor(new BlockReportsCommand(ws));
        this.getCommand("allowreports").setExecutor(new AllowReportsCommand(ws));
        this.getCommand("pinfo").setExecutor(new PlayerInfoCommand(ws));
        this.getCommand("resolvereports").setExecutor(new ResolveReportsCommand(ws));
        this.getCommand("interfaceinfo").setExecutor(new InfoCommand());


        protocolManager.addPacketListener(new PacketAdapter(this,
                ListenerPriority.NORMAL,
                PacketType.Play.Client.CHAT) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                if (event.getPacketType() == PacketType.Play.Client.CHAT) {
                    PacketContainer packet = event.getPacket();
                    String message = packet.getStrings().read(0);
                    if (event.getPlayer() != null) {
                    }
                    if (message.contains("shit")
                            || message.contains("damn")) {
                        event.setCancelled(true);
                        event.getPlayer().sendMessage("Bad manners!");
                    }
                }
            }
        });

        protocolManager.addPacketListener(new PacketAdapter(this,
                ListenerPriority.NORMAL,
                PacketType.Play.Server.CHAT) {
            @Override
            public void onPacketSending(PacketEvent event) {
                if(event.getPacketType() == PacketType.Play.Server.CHAT) {
                    try {
                        String message = "";
                        try {
                            String jsonMessage = event.getPacket().getChatComponents().getValues().get(0).getJson();
                            StructureModifier<WrappedChatComponent> componets = event.getPacket().getChatComponents();
                            try{
                                JSONObject json =(JSONObject) parser.parse(componets.read(0).getJson());
                                //Congratulations! You now have a json object, you can also get the player who receives this message using event.getPlayer()!
                            }catch(ParseException e){
                                e.printStackTrace();
                            }
                            if (jsonMessage != null)
                                message = jsonMessage;
                        } catch (Throwable ignore) {
                        }


                    } catch(Exception e) {

                        System.err.println("error");
                        e.printStackTrace();

                    }
                }
            }
        });
    }


}
