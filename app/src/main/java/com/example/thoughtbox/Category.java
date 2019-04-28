package com.example.thoughtbox;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;
import android.provider.MediaStore;
import android.support.annotation.NonNull;

import java.util.List;

@Entity
public class Category {

    public Category(@NonNull String Name) {
        this.Name = Name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

//    public List<Thought> getThoughts() {
//        return mThoughtsList;
//    }
//
//    public void setThoughtsList(List<Thought> mThoughtsList) {
//        this.mThoughtsList = mThoughtsList;
//    }

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int Id;

    @NonNull
    private String Name;

//    List<Thought> mThoughtsList;
}
