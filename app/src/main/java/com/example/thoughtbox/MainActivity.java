package com.example.thoughtbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Class LaunchActivity = ShowAllThoughts.class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent startUpIntent = new Intent(this, LaunchActivity);
        startActivity(startUpIntent);
    }
    
}
