package me.anatomic.divictus.interfaceplugin.network;

import org.json.simple.JSONObject;

public class PlayerOnlineOfflineStatus {
    public JSONObject jsonObj = new JSONObject();

    public PlayerOnlineOfflineStatus(String playerNickname, Boolean isOnline){
        JSONObject innerObj = new JSONObject();
        innerObj.put("playerNickname", playerNickname);
        innerObj.put("isOnline", isOnline);
        jsonObj.put("playerStatusChanged", innerObj);
    }
}
