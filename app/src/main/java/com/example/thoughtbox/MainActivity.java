package com.example.thoughtbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSaveThought(View view) {
        TextView thoughtBox = findViewById(R.id.NewThoughtBox);
        String thought = thoughtBox.getText().toString();
        int puppies = 4;
    }
}
