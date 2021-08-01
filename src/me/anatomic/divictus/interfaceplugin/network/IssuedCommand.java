package me.anatomic.divictus.interfaceplugin.network;

import org.json.simple.JSONObject;

public class IssuedCommand {
    public JSONObject jsonObj = new JSONObject();

    public IssuedCommand(String author, String command){
        JSONObject innerObj = new JSONObject();
        innerObj.put("issuer", author);
        innerObj.put("command", command);
        jsonObj.put("issued_command", innerObj);
    }
}
