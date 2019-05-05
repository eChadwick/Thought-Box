package com.example.thoughtbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CreateCategoryActivity extends AppCompatActivity {

    ThoughtBoxRoomDatabase mDb;
    CategoryDao mCategoryDao;
    CategoryThoughtDao mCategoryThoughtDao;
    ThoughtDao mThoughtDao;
    Intent mCallingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_category);

        Toolbar theToolbar = findViewById(R.id.create_category_toolbar);

        mDb = ThoughtBoxRoomDatabase.getDatabase(this);
        mCategoryDao = mDb.categoryDao();
        mCategoryThoughtDao = mDb.categoryThoughtDao();
        mThoughtDao = mDb.thoughtDao();
        mCallingIntent = getIntent();

        int callingId = mCallingIntent.getIntExtra( "CategoryId", -1);
        if(-1 != callingId) {
            theToolbar.setTitle("Edit Category");
            String categoryName = mCategoryDao.getCategory(callingId).getName();
            TextView theView = findViewById(R.id.category_name_field);
            theView.setText(categoryName);

            List<CategoryThought> categoryThoughts = mCategoryThoughtDao.getByCategoryId(callingId);
            List<Thought> memberThoughts = new ArrayList<Thought>();

            for(int i = 0; i < categoryThoughts.size(); i++){
                memberThoughts.add(mThoughtDao.getThought(categoryThoughts.get(i).getThoughtId()));
            }
            ListView theList = findViewById(R.id.member_thoughts_list);
            SelectThoughtListAdapter adapter = new SelectThoughtListAdapter(this, memberThoughts);

            theList.setAdapter(adapter);
        }
        else {
            theToolbar.setTitle("Add a Category");
            Button addThoughtsButton = (Button) findViewById(R.id.add_thoughts_to_category_button);
            addThoughtsButton.setVisibility(View.GONE);
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

    public void OnAddThoughts(View view) {
        Intent intent = new Intent(this, SelectThoughtsActivity.class);
        intent.putExtra("CategoryId", mCallingIntent.getIntExtra("CategoryId", -1));
        startActivity(intent);
    }
}