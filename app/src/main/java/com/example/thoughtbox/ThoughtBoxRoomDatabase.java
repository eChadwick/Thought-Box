package com.example.thoughtbox;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Thought.class, Category.class}, version = 1, exportSchema = false)
public abstract class ThoughtBoxRoomDatabase extends RoomDatabase {
    public abstract ThoughtDao thoughtDao();
    public abstract CategoryDao categoryDao();

    private static volatile ThoughtBoxRoomDatabase INSTANCE;

    static ThoughtBoxRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    ThoughtBoxRoomDatabase.class, "ThoughtBoxDb")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
