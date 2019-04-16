package com.example.thoughtbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CreateThought extends AppCompatActivity {

    ThoughtRoomDatabase mDb;
    ThoughtDao mThoughtDao;
    Intent mCallingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_thought);
        android.support.v7.widget.Toolbar theToolbar = findViewById(R.id.add_thought_toolbar);
        theToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        theToolbar.setTitle("Add a Thought");
        setSupportActionBar(theToolbar);

        mDb = ThoughtRoomDatabase.getDatabase(this);
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
            mThoughtDao.insert(new Thought(0, theThought));
        } else {
            Thought thoughtModel = mThoughtDao.getThought(callingThoughtId);
            thoughtModel.setContent(theThought);
            mThoughtDao.updateThought(thoughtModel);
        }
        returnToList();
    }

    public void onCancelNewThought(View view) {
        returnToList();
    }

    private void returnToList() {
        Intent thoughtListIntent = new Intent(this, ShowAllThoughts.class);
        startActivity(thoughtListIntent);
    }
}
