package com.example.thoughtbox;

import android.arch.persistence.room.Room;
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
    private final String ExpectedCategoryName1 = "Test Category 1";
    private final String ExpectedCategoryName2 = "Test Category 2";
    private final String ExpectedCategoryName3 = "Test Category 3";
    private final String UpdatedCategoryName = "Updated Category";

    public CategoryDaoTest() {
        Context context = InstrumentationRegistry.getContext();
        mDb = Room.inMemoryDatabaseBuilder(context, ThoughtBoxRoomDatabase.class).build();
        mCategoryDao = mDb.categoryDao();
    }

    @Before
    public void setup() throws Exception {
        for(int i = 1; i <= 3; i++) {
            mCategoryDao.insert(new Category("Test Category " + Integer.toString(i)));
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
        assertEquals(ExpectedCategoryName1 ,testCategory.getName());
    }

    @Test
    public void getAllCategories() throws Exception {
        List<Category> allCategories = mCategoryDao.getAllCategories();
        assertEquals( ExpectedNumEntries, allCategories.size());
        assertEquals( ExpectedCategoryName1, allCategories.get(0).getName());
        assertEquals( ExpectedCategoryName2, allCategories.get(1).getName());
        assertEquals( ExpectedCategoryName3, allCategories.get(2).getName());
    }

    @Test
    public void deleteCategory() throws Exception {
        mCategoryDao.delete( mCategoryDao.getCategory(1));
        assertEquals(null, mCategoryDao.getCategory(1));
    }

    @Test
    public void updateCategory() throws Exception {
        Category testCategory = mCategoryDao.getCategory(1);
        testCategory.setName(UpdatedCategoryName);
        mCategoryDao.update(testCategory);
        testCategory = mCategoryDao.getCategory(1);
        assertEquals(UpdatedCategoryName, testCategory.getName());
    }
}
