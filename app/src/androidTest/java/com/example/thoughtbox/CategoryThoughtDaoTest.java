package com.example.thoughtbox;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

@RunWith(JUnit4.class)
public class CategoryThoughtDaoTest {
    private ThoughtBoxRoomDatabase mDb;
    private CategoryThoughtDao mCategoryThoughtDao;
    private ThoughtDao mThoughtDao;
    private CategoryDao mCategoryDao;

    private final int TestThought0Id = 123;
    private final int TestCategory0Id = 234;
    private final int TestThought1Id = 345;
    private final int TestCategory1Id = 456;

    private final int ExpectedListSize = 2;
    private final int ExpectedPostDeleteSize = 1;

    public CategoryThoughtDaoTest() {
        Context currentContext = InstrumentationRegistry.getContext();
        mDb = Room.inMemoryDatabaseBuilder(currentContext, ThoughtBoxRoomDatabase.class).build();
        mCategoryThoughtDao = mDb.categoryThoughtDao();
        mThoughtDao = mDb.thoughtDao();
        mCategoryDao = mDb.categoryDao();
    }

    @Before
    public void setUp() {
        CategoryThought aCategoryThought = new CategoryThought(TestCategory0Id, TestThought0Id);
        mCategoryThoughtDao.insert(aCategoryThought);
        CategoryThought anotherCategoryThought = new CategoryThought(TestCategory0Id, TestThought1Id);
        mCategoryThoughtDao.insert(anotherCategoryThought);
        CategoryThought aThirdCategoryThought = new CategoryThought(TestCategory1Id, TestThought0Id);
        mCategoryThoughtDao.insert(aThirdCategoryThought);
        CategoryThought aFourthCategoryThought = new CategoryThought(TestCategory1Id, TestThought1Id);
        mCategoryThoughtDao.insert(aFourthCategoryThought);
    }

    @After
    public void tearDown() {
        mDb.close();
    }

    @Test
    public void RetrieveCategoryThoughtByCategory() throws Exception {
        List<CategoryThought> theList = mCategoryThoughtDao.getByCategoryId(TestCategory0Id);
        assertEquals(ExpectedListSize, theList.size());
        assertEquals(TestThought0Id, theList.get(0).getThoughtId());
        assertEquals(TestThought1Id, theList.get(1).getThoughtId());
    }

    @Test
    public void RetrieveCategoryThoughtByThought() throws Exception {
        List<CategoryThought> theList = mCategoryThoughtDao.getByThoughtId(TestThought0Id);
        assertEquals(ExpectedListSize, theList.size());
        assertEquals(TestCategory0Id, theList.get(0).getCategoryId());
        assertEquals(TestCategory1Id, theList.get(1).getCategoryId());
    }

    @Test
    public void DeleteCategoryThought() throws Exception {
        mCategoryThoughtDao.delete(TestCategory0Id, TestThought0Id);
        List<CategoryThought> theList = mCategoryThoughtDao.getByCategoryId(TestCategory0Id);
        assertEquals(ExpectedPostDeleteSize, theList.size());
    }

}
