package com.app.simon.rxsample.models;

/**
 * desc: Person
 * date: 2017/7/29
 *
 * @author xw
 */
public class Person {

    /**
     * name : laoli
     * idCard : 51068124112415
     * age : 1245152521
     * address : shijicheng
     */

    private String name;
    private String idCard;
    private int age;
    private String address;

    public Person(String name, int phone) {
        this.name = name;
        this.age = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
