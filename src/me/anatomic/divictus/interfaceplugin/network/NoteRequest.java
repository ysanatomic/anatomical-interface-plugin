package me.anatomic.divictus.interfaceplugin.network;

import org.json.simple.JSONObject;

public class NoteRequest {
    public JSONObject jsonObj = new JSONObject();

    public NoteRequest(String username, String madebyUUID, String note){
        JSONObject innerObj = new JSONObject();
        innerObj.put("note", note);
        innerObj.put("username", username);
        innerObj.put("madebyUUID", madebyUUID);
        jsonObj.put("noteContent", innerObj);
    }
}
