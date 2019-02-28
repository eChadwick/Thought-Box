package com.example.thoughtbox;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ThoughtViewModel mThoughtViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent startUpIntent = new Intent(this, ShowAllThoughts.class);
        startActivity(startUpIntent);
    }
    
}
