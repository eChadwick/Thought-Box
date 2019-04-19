package com.example.thoughtbox;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    private ThoughtDao mThoughtDao;
    private ThoughtRoomDatabase mDb;
    private final List<String> mTestThoughts = Arrays.asList(
            "thought one",
            "thought two",
            "thought three"
    );
    private final String mUpdatedThoughtContent = "This has been updated";

    @Before
    public void setUp() throws Exception { ;
        Context context = InstrumentationRegistry.getContext();
        mDb = Room.inMemoryDatabaseBuilder(context, ThoughtRoomDatabase.class).build();
        mThoughtDao = mDb.thoughtDao();
        for(int i = 0; i < mTestThoughts.size(); i++) {
            Thought newThought = new Thought(mTestThoughts.get(i));
            mThoughtDao.insert(newThought);
        }
    }

    @After
    public void tearDown() throws Exception {
        mDb.close();
    }

    @Test
    public void RetrieveSingleThought() throws  Exception {
        Thought retrievedThought = mThoughtDao.getThought(1);
        assertEquals(retrievedThought.getContent(), mTestThoughts.get(0));
    }

    @Test
    public void RetrieveAllThoughts() throws  Exception {
        List<Thought> retrievedThoughts = mThoughtDao.getAllThoughts();
        assertEquals(mTestThoughts.size(), retrievedThoughts.size());
        for(int i = 0; i < mTestThoughts.size(); i++) {
            assertEquals(mTestThoughts.get(i), retrievedThoughts.get(i).getContent());
        }
    }

    @Test
    public void UpdateThought() throws  Exception {
        Thought aThought = mThoughtDao.getThought(1);
        aThought.setContent(mUpdatedThoughtContent);
        mThoughtDao.updateThought(aThought);
        assertEquals( mUpdatedThoughtContent, mThoughtDao.getThought(1).getContent());
    }
}