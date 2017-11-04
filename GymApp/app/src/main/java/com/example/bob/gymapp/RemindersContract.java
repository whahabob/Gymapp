package com.example.bob.gymapp;


import android.provider.BaseColumns;

/**
 * Created by marmm on 10/2/17.
 */

public final class RemindersContract {

    private RemindersContract() {}

    /* Inner class that defines the table contents */
    public static class ReminderEntry implements BaseColumns {
        public static final String TABLE_NAME_WORKOUT = "Workouts";
        public static final String COLUMN_NAME_WORKOUT = "workout";

    }

    public static class PushEntry implements BaseColumns {

        public static final String TABLE_NAME_PUSH = "PushWorkouts";
        public static final String COLUMN_NAME_PUSH = "push";
    }

    public static class PullhEntry implements BaseColumns {

        public static final String TABLE_NAME_PULL = "PullWorkouts";
        public static final String COLUMN_NAME_PULL = "pull";

    }
    public static class LegshEntry implements BaseColumns {

        public static final String TABLE_NAME_LEGS = "LegsWorkouts";
        public static final String COLUMN_NAME_LEGS = "legs";

    }
}
