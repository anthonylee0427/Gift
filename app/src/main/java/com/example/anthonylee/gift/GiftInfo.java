package com.example.anthonylee.gift;

/**
 * Created by AnthonyLee on 4/11/17.
 */

public class GiftInfo {
    public String ruid;
    public String gtype;
    public String guid;

    public GiftInfo(){

    }

    public GiftInfo(String ruid, String gtype, String guid) {
        this.ruid = ruid;
        this.gtype = gtype;
        this.guid = guid;
    }

    public String getRuid() {
        return ruid;
    }

    public void setRuid(String ruid) {
        this.ruid = ruid;
    }

    public String getGtype() {
        return gtype;
    }

    public void setGtype(String gtype) {
        this.gtype = gtype;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }
}
