package com.example.bob.gymapp;


public class Legs {

    int id;
    String legs_name;

    // constructors
    public Legs() {

    }

    public Legs(String legs_name) {
        this.legs_name = legs_name;
    }

    public Legs(int id, String legs_name) {
        this.id = id;
        this.legs_name = legs_name;
    }

    // setter
    public void setId(int id) {
        this.id = id;
    }

    public void setLegsName(String legs_name) {
        this.legs_name = legs_name;
    }

    // getter
    public int getId() {
        return this.id;
    }

    public String getLegsName() {
        return this.legs_name;
    }
}
