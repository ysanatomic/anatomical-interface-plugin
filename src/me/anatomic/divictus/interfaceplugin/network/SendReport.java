package me.anatomic.divictus.interfaceplugin.network;

import org.json.simple.JSONObject;

public class SendReport {
    public JSONObject jsonObj = new JSONObject();

    public SendReport(String made_by, String content, String target){
        JSONObject innerObj = new JSONObject();
        innerObj.put("made_by", made_by);
        innerObj.put("content", content);
        innerObj.put("target", target);
        jsonObj.put("reportContent", innerObj);
    }
}