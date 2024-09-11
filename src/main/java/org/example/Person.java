package org.example;
public class Person {

    private String name;
    private int roomNu;
    private String phoneNu;

    public Person() {
    }

    public Person(String name, int roomNu, String phoneNu) {
        this.name = name;
        this.roomNu = roomNu;
        this.phoneNu = phoneNu;
    }

    public String getName() {
        return name;
    }

    public int getRoomNu() {
        return roomNu;
    }

    public String getPhoneNu() {
        return phoneNu;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoomNu(int roomNu) {
        this.roomNu = roomNu;
    }

    public void setPhoneNu(String phoneNu) {
        this.phoneNu = phoneNu;
    }
}
