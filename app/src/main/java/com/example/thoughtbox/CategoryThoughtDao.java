package com.example.thoughtbox;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.widget.LinearLayout;

import java.util.List;

@Dao
public interface CategoryThoughtDao {

    @Insert
    void insert(CategoryThought categoryThought);

    @Query("Select * from CategoryThought where CategoryId is :categoryId")
    List<CategoryThought> getByCategoryId(int categoryId);

    @Query("Select * from CategoryThought where ThoughtId is :thoughtId")
    List<CategoryThought> getByThoughtId(int thoughtId);

    @Query("Delete from CategoryThought where CategoryId is :categoryId and ThoughtId is :thoughtId")
    void delete(int categoryId, int thoughtId);
}
