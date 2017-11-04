package com.example.bob.gymapp;

/**
 * Created by Bob on 27-10-2017.
 */

public class Pull {

    int id;
    String pull_name;

    // constructors
    public Pull() {

    }

    public Pull(String pull_name) {
        this.pull_name = pull_name;
    }

    public Pull(int id, String pull_name) {
        this.id = id;
        this.pull_name = pull_name;
    }

    // setter
    public void setId(int id) {
        this.id = id;
    }

    public void setPullName(String pull_name) {
        this.pull_name = pull_name;
    }

    // getter
    public int getId() {
        return this.id;
    }

    public String getPullName() {
        return this.pull_name;
    }
}