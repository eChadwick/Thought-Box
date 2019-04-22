package com.example.thoughtbox;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class CategoryDaoTest {
    private CategoryDao mCategoryDao;
    private ThoughtBoxRoomDatabase mDb;

    private final int ExpectedNumEntries = 3;

    public CategoryDaoTest() {
        Context context = InstrumentationRegistry.getContext();
        mDb = Room.inMemoryDatabaseBuilder(context, ThoughtBoxRoomDatabase.class).build();
        mCategoryDao = mDb.categoryDao();
    }

    @Before
    public void setup() throws Exception {
        for(int i = 0; i < 3; i++) {
            mCategoryDao.insert(new Category());
        }
    }

    @After
    public void tearDown() throws Exception {
        mDb.close();
    }

    @Test
    public void getSingleCategories() throws Exception {
        Category testCategory = mCategoryDao.getCategory(1);
        assertNotNull(testCategory);
    }

    @Test
    public void getAllCategories() throws Exception {
        List<Category> allCategories = mCategoryDao.getAllCategories();
        assertEquals( ExpectedNumEntries, allCategories.size());
    }

    @Test
    public void deleteCategory() throws Exception {
        mCategoryDao.delete( mCategoryDao.getCategory(1));
        assertEquals(null, mCategoryDao.getCategory(1));
    }
}
