package me.anatomic.divictus.interfaceplugin.network;

import org.json.simple.JSONObject;

public class ChatMessage {

    public JSONObject jsonObj = new JSONObject();

    public ChatMessage(String author, String message){
        JSONObject innerObj = new JSONObject();
        innerObj.put("author", author);
        innerObj.put("text", message);
        jsonObj.put("message", innerObj);
    }

}
