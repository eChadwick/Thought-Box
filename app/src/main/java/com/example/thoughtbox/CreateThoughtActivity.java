package com.example.thoughtbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CreateThoughtActivity extends AppCompatActivity {

    ThoughtBoxRoomDatabase mDb;
    ThoughtDao mThoughtDao;
    Intent mCallingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_thought);
        android.support.v7.widget.Toolbar theToolbar = findViewById(R.id.add_thought_toolbar);
        theToolbar.setTitle("Add a Thought");
        setSupportActionBar(theToolbar);

        mDb = ThoughtBoxRoomDatabase.getDatabase(this);
        mThoughtDao = mDb.thoughtDao();
        mCallingIntent = getIntent();

        int callingThoughtId = mCallingIntent.getIntExtra("ThoughtId", -1);
        if( callingThoughtId != -1) {
            String thoughtContent = mThoughtDao.getThought(callingThoughtId).getContent();
            TextView thoughtView = (TextView) findViewById(R.id.NewThoughtBox);
            thoughtView.setText(thoughtContent);
        }
    }

    public void onSaveThought(View view) {
        TextView thoughtBox = findViewById(R.id.NewThoughtBox);
        String theThought = thoughtBox.getText().toString();
        int callingThoughtId = mCallingIntent.getIntExtra("ThoughtId", -1);
        if(callingThoughtId == -1) {
            mThoughtDao.insert(new Thought(theThought));
        } else {
            Thought thoughtModel = mThoughtDao.getThought(callingThoughtId);
            thoughtModel.setContent(theThought);
            mThoughtDao.updateThought(thoughtModel);
        }
        finish();
    }

    public void onCancelNewThought(View view) {
        finish();
    }

}
