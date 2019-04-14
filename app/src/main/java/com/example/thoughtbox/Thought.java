package com.example.thoughtbox;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Thought {

//  Constructor
    public Thought(int Id, @NonNull String mContent)
    {
        this.mContent = mContent;
        this.Id = Id;
    }

//  Returns the content of the thought
    public String getContent()
    {
        return  mContent;
    }

    public void setContent(String mContent)
    {
        this.mContent = mContent;
    }

    public int getId() {
        return Id;
    }

    public void setId(int ignoredId) {
        Id = Id;
    }

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int Id;

    @NonNull
    private String mContent;
}
