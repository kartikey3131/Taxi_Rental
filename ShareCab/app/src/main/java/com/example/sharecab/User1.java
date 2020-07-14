package com.example.sharecab;

public class User1 {
    String name;
    String contact;
    String type;

    public User1(){

    }

    public User1(String name, String contact,String type) {
        this.name = name;
        this.contact = contact;
        this.type =type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
