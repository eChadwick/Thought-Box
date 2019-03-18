package com.example.thoughtbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CreateThought extends AppCompatActivity {

    ThoughtRoomDatabase mDb;
    ThoughtDao mThoughtDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_thought);
        mDb = ThoughtRoomDatabase.getDatabase(this);
        mThoughtDao = mDb.thoughtDao();
    }

    public void onSaveThought(View view) {
        TextView thoughtBox = findViewById(R.id.NewThoughtBox);
        String theThought = thoughtBox.getText().toString();
        mThoughtDao.insert(new Thought(theThought));
//        mThoughtViewModel.insert(new Thought(thought));

    }
}
