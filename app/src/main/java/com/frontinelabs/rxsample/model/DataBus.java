package com.frontinelabs.rxsample.model;

/**
 * Created by EBaba on 11/10/2017.
 */

public class DataBus {
    String id, data= "";
    int position = 0;

    public DataBus(int position, String id, String data) {
        this.position = position;
        this.id = id;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
