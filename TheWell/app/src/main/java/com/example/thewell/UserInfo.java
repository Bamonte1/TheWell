package com.example.thewell;

public class UserInfo {

    private int id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String phone;
    private String phoneType;
    private String note;
    private String delivery;
    private String date;

    public UserInfo() {
        super();
    }

    public UserInfo(String name, String address, String city, String state, String phone, String phoneType, String note, String delivery, String date) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.phone = phone;
        this.phoneType = phoneType;
        this.note = note;
        this.delivery = delivery;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", phone='" + phone + '\'' +
                ", phoneType='" + phoneType + '\'' +
                ", note='" + note + '\'' +
                ", delivery='" + delivery + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
