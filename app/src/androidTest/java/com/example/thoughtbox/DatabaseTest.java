package com.example.thoughtbox;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    private ThoughtDao mThoughtDao;
    private ThoughtRoomDatabase mDb;

    @Before
    public void setUp() throws Exception { ;
        Context context = InstrumentationRegistry.getContext();
        mDb = Room.inMemoryDatabaseBuilder(context, ThoughtRoomDatabase.class).build();
        mThoughtDao = mDb.thoughtDao();
    }

    @After
    public void tearDown() throws Exception {
        mDb.close();
    }

    @Test
    public void SaveAndRetrieveThought() throws  Exception {
        Thought newThought = new Thought("Just work please");
        mThoughtDao.insert(newThought);
        Thought retrievedThought = mThoughtDao.getThought("Just work please");
        assertEquals(newThought.getContent(), retrievedThought.getContent());
    }
}