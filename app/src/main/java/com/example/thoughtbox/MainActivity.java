package com.example.thoughtbox;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ThoughtViewModel mThoughtViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mThoughtViewModel = ViewModelProviders.of(this).get(ThoughtViewModel.class);
    }

    public void onSaveThought(View view) {
        TextView thoughtBox = findViewById(R.id.NewThoughtBox);
        String thought = thoughtBox.getText().toString();
        mThoughtViewModel.insert(new Thought(thought));
    }
}
