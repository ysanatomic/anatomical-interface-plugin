package me.anatomic.divictus.interfaceplugin.network;

import org.json.simple.JSONObject;

public class GetNotesRequest {
    public JSONObject jsonObj = new JSONObject();

    public GetNotesRequest(String username, String requestedByUUID, Integer page){
        JSONObject innerObj = new JSONObject();
        innerObj.put("username", username);
        innerObj.put("requestedByUUID", requestedByUUID);
        innerObj.put("page", page);
        jsonObj.put("getNoteRequest", innerObj);
    }
}
