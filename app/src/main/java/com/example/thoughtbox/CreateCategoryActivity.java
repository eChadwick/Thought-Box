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
    Intent mCallingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_category);

        Toolbar theToolbar = findViewById(R.id.create_category_toolbar);
        theToolbar.setTitle("Add a Category");

        mDb = ThoughtBoxRoomDatabase.getDatabase(this);
        mCategoryDao = mDb.categoryDao();
        mCallingIntent = getIntent();

        int callingId = mCallingIntent.getIntExtra( "CategoryId", -1);
        if(-1 != callingId) {
            String categoryName = mCategoryDao.getCategory(callingId).getName();
            TextView theView = findViewById(R.id.category_name_field);
            theView.setText(categoryName);
        }
    }

    public void onSaveCategory(View view) {
        TextView newCategoryBox = findViewById(R.id.category_name_field);
        String categoryName = newCategoryBox.getText().toString();
        int callingId = mCallingIntent.getIntExtra("CategoryId", -1);
        if(-1 == callingId) {
            Category aCategory = new Category(categoryName);
            mCategoryDao.insert(aCategory);
        } else {
            Category categoryModel = mCategoryDao.getCategory(callingId);
            categoryModel.setName(categoryName);
            mCategoryDao.update(categoryModel);
        }
        Intent categoryListIntent = new Intent(this, CategoryListActivity.class);
        startActivity(categoryListIntent);
    }

    public void OnCancelCategory(View view) {
        finish();
    }
}