package com.example.anthonylee.gift;

/**
 * Created by AnthonyLee on 4/11/17.
 */

public class GiftInfo {
    private String Guid;
    private String Gtype;
    private String Ruid;

    public GiftInfo(String guid, String gtype, String ruid) {
        Guid = guid;
        Gtype = gtype;
        Ruid = ruid;
    }

    public GiftInfo(){

    }

    public String getGuid() {
        return Guid;
    }

    public void setGuid(String guid) {
        Guid = guid;
    }

    public String getGtype() {
        return Gtype;
    }

    public void setGtype(String gtype) {
        Gtype = gtype;
    }

    public String getRuid() {
        return Ruid;
    }

    public void setRuid(String ruid) {
        Ruid = ruid;
    }


}
