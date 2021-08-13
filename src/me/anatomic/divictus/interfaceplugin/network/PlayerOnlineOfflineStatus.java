package me.anatomic.divictus.interfaceplugin.network;

import org.json.simple.JSONObject;

public class PlayerOnlineOfflineStatus {
    public JSONObject jsonObj = new JSONObject();

    public PlayerOnlineOfflineStatus(String playerNickname, Boolean isOnline, String uuid){
        JSONObject innerObj = new JSONObject();
        innerObj.put("playerNickname", playerNickname);
        innerObj.put("isOnline", isOnline);
        innerObj.put("uuid", uuid);
        jsonObj.put("playerStatusChanged", innerObj);
    }
}
