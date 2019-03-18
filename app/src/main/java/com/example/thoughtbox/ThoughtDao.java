package com.example.thoughtbox;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ThoughtDao {
    @Insert
    void insert(Thought thought);

    @Query("Select * From Thought Where mContent Is :thought")
    Thought getThought( String thought );

    @Query("Select * From Thought")
    List<Thought> getAllThoughts();
}
