package com.example.bob.gymapp;


/**
 * Created by marmm on 9/4/17.
 */

public class Reminder {

    String mReminderText;


    @Override
    public String toString() {
        return mReminderText;
    }

    public String getmReminderText() {
        return mReminderText;
    }

    public void setmReminderText(String mReminderText) {
        this.mReminderText = mReminderText;
    }

    public Reminder(String mReminderText) {

        this.mReminderText = mReminderText;
    }
}