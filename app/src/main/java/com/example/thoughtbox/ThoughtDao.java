package com.example.thoughtbox;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

@Dao
public interface ThoughtDao {
    @Insert
    void insert(Thought thought);
}
