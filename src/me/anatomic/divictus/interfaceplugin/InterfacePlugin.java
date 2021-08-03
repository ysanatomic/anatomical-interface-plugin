package me.anatomic.divictus.interfaceplugin;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.reflect.StructureModifier;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import me.anatomic.divictus.interfaceplugin.Server.*;
import me.anatomic.divictus.interfaceplugin.network.Websockets;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.ProtocolLibrary;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class InterfacePlugin extends JavaPlugin implements Listener {

    private ProtocolManager protocolManager;
    private JSONParser parser = new org.json.simple.parser.JSONParser();




    @Override
    public void onEnable() {
        System.out.println("[DivictusInterfacePlugin] Plugin is enabled.");
        System.out.println("[DivictusInterfacePlugin] Developed by Anatomic.");

        protocolManager = ProtocolLibrary.getProtocolManager();

//        this.getCommand("kit").setExecutor(new TestCommand());

//        new WSWrapper(clientPointer);

//        client = new WebSocketClient({})

        Websockets ws = new Websockets();
        ws.startWebSocket(this);
        ws.startMainWebSocketListener(this);

        getServer().getPluginManager().registerEvents(new Chat(), this);
        getServer().getPluginManager().registerEvents(new Players(ws), this);
        ChatFeed chatfeed = new ChatFeed(protocolManager, this, ws);

        this.getCommand("test").setExecutor(new TestCommand());
        this.getCommand("pinfo").setExecutor(new ViewInfoCommand());


        protocolManager.addPacketListener(new PacketAdapter(this,
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
                                System.out.println(json);
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
