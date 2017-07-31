package com.app.simon.rxsample.models;

/**
 * desc: Place
 * date: 2017/7/31
 *
 * @author xw
 */
public class Place {

    /**
     * id : 201
     * city : ChengDu
     * address : software park
     */

    private int id;
    private String city;
    private String address;

    public Place(int id, String city) {
        this.id = id;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
