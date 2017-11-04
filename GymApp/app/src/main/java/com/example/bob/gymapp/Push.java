package com.example.bob.gymapp;

/**
 * Created by Bob on 27-10-2017.
 */

public class Push {

    int id;
    String push_name;

    // constructors
    public Push() {

    }

    public Push(String push_name) {
        this.push_name = push_name;
    }

    public Push(int id, String push_name) {
        this.id = id;
        this.push_name = push_name;
    }

    // setter
    public void setId(int id) {
        this.id = id;
    }

    public void setPushName(String push_name) {
        this.push_name = push_name;
    }

    // getter
    public int getId() {
        return this.id;
    }

    public String getPushName() {
        return this.push_name;
    }
}