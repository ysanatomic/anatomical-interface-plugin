package me.anatomic.divictus.interfaceplugin.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.neovisionaries.ws.client.*;
import me.anatomic.divictus.interfaceplugin.InterfacePlugin;

import javax.naming.Context;
import java.io.IOException;

public class Websockets {

    String path = "ws://localhost:8888";

    Gson gson =  new GsonBuilder().create();
    public WebSocket ws = null; // actual websocket
    boolean runningSocket = false;
    boolean runningListener = false;
    boolean wsPermitted = true;

    boolean isPermitted(){
        return wsPermitted;
    }

    public void startWebSocket(InterfacePlugin context){
        (new Thread(new Runnable(){
            public void run(){
                while(true) {
                    if((ws == null || ws.getState() == WebSocketState.CLOSED || ws.getState() == null) && wsPermitted == true) {
                        try {
                            ws = new WebSocketFactory().createSocket(path, 5000).connect();
                            runningSocket = true;
                        } catch (WebSocketException e) {
                            runningSocket = false;
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if(!wsPermitted){
                        break;
                    }
                    try {
                        Thread.sleep(750);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        })).start();
    }

    public void startMainWebSocketListener(InterfacePlugin context){
        (new Thread(new Runnable(){
            public void run(){
                while(true) {
                    if(runningSocket == true && runningListener == false) {
                        runningListener = true;
                        initiateWebSocketListener(context);
                    } else if(!wsPermitted) {
                        break;
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        })).start();
    }

    public void initiateWebSocketListener(InterfacePlugin context){
        ws.addListener(new WebSocketAdapter(){

            public void onConnectionError(WebSocket websocket, WebSocketException exception) throws Exception {
                super.onConnectError(websocket, exception);
                    runningListener = false;
            }

            public void onError(WebSocket websocket, WebSocketException cause) throws Exception {
                super.onError(websocket, cause);
            }

            public void onDisconnect(
                    WebSocket websocket,
                    WebSocketFrame serverCloseFrame,
                    WebSocketFrame clientCloseFrame,
                    Boolean closedByServer
            ) throws Exception {
                super.onDisconnected(websocket, serverCloseFrame, clientCloseFrame, closedByServer);
                System.out.println("Disconnected");
                runningSocket = false;
                runningListener = false;
            }

            public void onTextManager(WebSocket websocket, String text) throws Exception {
                super.onTextMessage(websocket, text);
                System.out.println(text);
            }

        });
    }

    public boolean isWSRunning() {
        return runningSocket;
    }

}