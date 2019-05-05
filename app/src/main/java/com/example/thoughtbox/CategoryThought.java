package com.example.thoughtbox;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(primaryKeys = {"CategoryId", "ThoughtId"})
public class CategoryThought {

    public CategoryThought(int CategoryId, int ThoughtId) {
        this.ThoughtId = ThoughtId;
        this.CategoryId = CategoryId;
    }

    public int getThoughtId() {
        return this.ThoughtId;
    }

    public void setThoughtId(int thoughtId) {
        this.ThoughtId = thoughtId;
    }

    public int getCategoryId() {
        return this.CategoryId;
    }

    public void setCategoryId(int categoryId) {
        this.CategoryId = categoryId;
    }

    @NonNull
    int ThoughtId;

    @NonNull
    int CategoryId;
}
