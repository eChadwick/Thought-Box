package com.example.thoughtbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class ShowAllThoughts extends AppCompatActivity {
    ThoughtRoomDatabase mDb;
    List<Thought> mAllThoughts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thought_list);

        mDb = ThoughtRoomDatabase.getDatabase(this);
        ThoughtDao aThoughtDao = mDb.thoughtDao();
        mAllThoughts = aThoughtDao.getAllThoughts();

        RecyclerView theListView = (RecyclerView) findViewById(R.id.all_thoughts_list);
        theListView.setHasFixedSize(true);

        ThoughtRecyclerAdapter adpater = new ThoughtRecyclerAdapter(this, mAllThoughts);
        theListView.setAdapter(adpater);

        theListView.setLayoutManager(new LinearLayoutManager(this));
    }
}
