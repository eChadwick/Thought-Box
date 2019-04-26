package com.example.thoughtbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ListView;

import java.util.List;

public class CategoryListActivity extends AppCompatActivity {

    private ThoughtBoxRoomDatabase mDb;
    private CategoryDao mCategoryDao;
    private List<Category> mCategoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        Toolbar theToolbar = findViewById(R.id.category_list_toolbar);
        theToolbar.setTitle("All Categories");
        setSupportActionBar(theToolbar);

        mDb = ThoughtBoxRoomDatabase.getDatabase(this);
        mCategoryDao = mDb.categoryDao();
        mCategoryList = mCategoryDao.getAllCategories();

        RecyclerView theList = (RecyclerView) findViewById(R.id.category_list);
        theList.setHasFixedSize(true);

        CategoryRecyclerAdapter anAdapter = new CategoryRecyclerAdapter(this, mCategoryList);
        theList.setAdapter(anAdapter);

        theList.setLayoutManager( new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.category_list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_thought_button:
                Intent newThoughtIntent = new Intent(this, CreateThoughtActivity.class);
                startActivity(newThoughtIntent);
                break;
            case R.id.new_category_button:
                Intent newCategoryIntent = new Intent(this, CreateCategoryActivity.class);
                startActivity(newCategoryIntent);
                break;
            case R.id.view_thoughts_button:
                Intent viewThoughtsIntent = new Intent(this, ThoughtListActivity.class);
                startActivity(viewThoughtsIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
