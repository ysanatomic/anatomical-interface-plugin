package me.anatomic.divictus.interfaceplugin.network;

import org.json.simple.JSONObject;

public class GetPlayerInfoRequest {

    public JSONObject jsonObj = new JSONObject();

    public GetPlayerInfoRequest(String player, String requesterUUID){
        JSONObject innerObj = new JSONObject();
        innerObj.put("player", player);
        innerObj.put("requesterUUID", requesterUUID);
        jsonObj.put("getPlayerInfoRequest", innerObj);
    }

}
