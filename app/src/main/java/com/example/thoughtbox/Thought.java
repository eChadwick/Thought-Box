package com.example.thoughtbox;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Thought {

//  Constructor
    public Thought(@NonNull String mContent)
    {
        this.mContent = mContent;
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

//  Content of the thought
    @PrimaryKey
    @NonNull
    private String mContent;
}
