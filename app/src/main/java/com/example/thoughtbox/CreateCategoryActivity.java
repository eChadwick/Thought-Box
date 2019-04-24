package com.example.thoughtbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class CreateCategoryActivity extends AppCompatActivity {

    ThoughtBoxRoomDatabase mDb;
    CategoryDao mCategoryDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_category);

        Toolbar theToolbar = findViewById(R.id.create_category_toolbar);
        theToolbar.setTitle("Add a Category");

        mDb = ThoughtBoxRoomDatabase.getDatabase(this);
        mCategoryDao = mDb.categoryDao();
    }

    public void onSaveCategory(View view) {
        TextView newCategoryBox = findViewById(R.id.NewCategoryBox);
        String categoryName = newCategoryBox.getText().toString();
        Category aCategory = new Category(categoryName);
        mCategoryDao.insert(aCategory);
        finish();
    }

    public void OnCancelCategory(View view) {
        finish();
    }
}