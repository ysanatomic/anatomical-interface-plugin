package me.anatomic.divictus.interfaceplugin.network;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.json.simple.JSONObject;

public class ReportAbilityChange {
    public JSONObject jsonObj = new JSONObject();

    public ReportAbilityChange(String player, Boolean allow){
        JSONObject innerObj = new JSONObject();
        innerObj.put("player", player);
        innerObj.put("allow", allow);
        jsonObj.put("reportAbilityChange", innerObj);
    }
}
