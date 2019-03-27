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

public class ShowAllThoughts extends AppCompatActivity {
    ThoughtRoomDatabase mDb;
    List<Thought> mAllThoughts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thought_list);

        Toolbar theToolbar = findViewById(R.id.thought_list_toolbar);
        theToolbar.setTitle("All Thoughts");
        theToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(theToolbar);

        mDb = ThoughtRoomDatabase.getDatabase(this);
        ThoughtDao aThoughtDao = mDb.thoughtDao();
        mAllThoughts = aThoughtDao.getAllThoughts();

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
                Intent newThoughtIntent = new Intent(this, CreateThought.class);
                startActivity(newThoughtIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
