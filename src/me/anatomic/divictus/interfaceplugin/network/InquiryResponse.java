package me.anatomic.divictus.interfaceplugin.network;

import org.json.simple.JSONObject;

public class InquiryResponse {

    public JSONObject jsonObj = new JSONObject();

    public InquiryResponse(String ticket, String response){
        JSONObject innerObj = new JSONObject();
        innerObj.put("ticket", ticket);
        innerObj.put("response", response);
        jsonObj.put("inquiryResponse", innerObj);
    }

}
