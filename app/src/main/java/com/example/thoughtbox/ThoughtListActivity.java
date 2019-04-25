package com.example.thoughtbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import java.util.List;

public class ThoughtListActivity extends AppCompatActivity {
    ThoughtBoxRoomDatabase mDb;
    ThoughtDao mThoughtDao;
    List<Thought> mAllThoughts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thought_list);

        Toolbar theToolbar = findViewById(R.id.thought_list_toolbar);
        theToolbar.setTitle("All Thoughts");
        setSupportActionBar(theToolbar);

        mDb = ThoughtBoxRoomDatabase.getDatabase(this);
        mThoughtDao = mDb.thoughtDao();
        mAllThoughts = mThoughtDao.getAllThoughts();

        RecyclerView theListView = (RecyclerView) findViewById(R.id.all_thoughts_list);
        theListView.setHasFixedSize(true);

        ThoughtRecyclerAdapter adpater = new ThoughtRecyclerAdapter(this, mAllThoughts);
        theListView.setAdapter(adpater);

        theListView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.thought_list_menu, menu);
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
            case R.id.view_categories_button:
                Intent viewCategoriesIntent = new Intent(this, CategoryListActivity.class);
                startActivity(viewCategoriesIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        mAllThoughts = mThoughtDao.getAllThoughts();
        ThoughtRecyclerAdapter newDataAdapter = new ThoughtRecyclerAdapter(this, mAllThoughts);
        RecyclerView theListView = (RecyclerView) findViewById(R.id.all_thoughts_list);
        theListView.setAdapter(newDataAdapter);
        super.onResume();
    }
}
