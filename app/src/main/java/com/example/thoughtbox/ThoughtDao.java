package com.example.thoughtbox;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface ThoughtDao {
    @Insert
    void insert(Thought thought);

    @Query("Select * From Thought Where mContent Is :thought")
    Thought getThought( String thought );
}
