package com.example.thoughtbox;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface CategoryDao {

    @Insert
    void insert(Category category);

    @Query("Select * from Category where Id is :id")
    Category getCategory(int id);

    @Query("Select * from Category")
    List<Category> getAllCategories();
}
