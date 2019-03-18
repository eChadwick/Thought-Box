package com.example.thoughtbox;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Thought.class}, version = 1, exportSchema = false)
public abstract class ThoughtRoomDatabase extends RoomDatabase {
    public abstract ThoughtDao thoughtDao();

    private static volatile ThoughtRoomDatabase INSTANCE;

    static ThoughtRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    ThoughtRoomDatabase.class, "thoughts_db")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
