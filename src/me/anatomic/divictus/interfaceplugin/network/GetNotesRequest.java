package me.anatomic.divictus.interfaceplugin.network;

import org.json.simple.JSONObject;

public class GetNotesRequest {
    public JSONObject jsonObj = new JSONObject();

    public GetNotesRequest(String username, String requestedByUUID){
        JSONObject innerObj = new JSONObject();
        innerObj.put("username", username);
        innerObj.put("requestedByUUID", requestedByUUID);
        jsonObj.put("getNoteRequest", innerObj);
    }
}
