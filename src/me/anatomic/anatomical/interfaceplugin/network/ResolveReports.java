package me.anatomic.anatomical.interfaceplugin.network;

import org.json.simple.JSONObject;

public class ResolveReports {
    public JSONObject jsonObj = new JSONObject();

    public ResolveReports(String player, String made_by){
        JSONObject innerObj = new JSONObject();
        innerObj.put("player", player);
        innerObj.put("made_by", made_by);
        jsonObj.put("resolveReports", innerObj);
    }
}
