package com.example.thoughtbox;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ShowAllThoughts extends AppCompatActivity {

    private ThoughtViewModel mThoughtViewModel;
    private List<Thought> mAllThoughts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_thoughts);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mThoughtViewModel = ViewModelProviders.of(this).get(ThoughtViewModel.class);
        mAllThoughts = mThoughtViewModel.getAllThoughts();

//         This apparently speeds loading.
        RecyclerView aViewRef = findViewById(R.id.all_thoughts_view);
//        aViewRef.setHasFixedSize(true);

        RecyclerView.LayoutManager aLayoutManager = new LinearLayoutManager(this);
        ((LinearLayoutManager) aLayoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        aViewRef.setLayoutManager(aLayoutManager);

        ThoughtRecyclerAdapter anAdapter = new ThoughtRecyclerAdapter(this);
        aViewRef.setAdapter(anAdapter);
    }

}
