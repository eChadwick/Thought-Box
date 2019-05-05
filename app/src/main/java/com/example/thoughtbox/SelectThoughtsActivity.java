package com.example.thoughtbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;


public class SelectThoughtsActivity extends AppCompatActivity {
    private ThoughtBoxRoomDatabase mDb;
    private ThoughtDao mThoughtDao;
    private List<Thought> mThoughtList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_thoughts);

        Toolbar theToolbar = (Toolbar) findViewById(R.id.select_thoughts_toolbar);
        theToolbar.setTitle("Select Thoughts to Add");

        mDb = ThoughtBoxRoomDatabase.getDatabase(this);
        mThoughtDao = mDb.thoughtDao();
        mThoughtList = mThoughtDao.getAllThoughts();

        SelectThoughtListAdapter theAdapter = new SelectThoughtListAdapter(this, mThoughtList);
        ListView theList = findViewById(R.id.select_thought_list);
        theList.setAdapter(theAdapter);
    }

    public void onSubmit(View view) {
        ListView theList = findViewById(R.id.select_thought_list);
        SparseBooleanArray checkedItems = theList.getCheckedItemPositions();
        theList.getCheckedItemIds();
    }

    public void onThoughtClick(View view) {
        LinearLayout parent = (LinearLayout) view.getParent();
        ListView theList = (ListView) view.getParent().getParent();

        CheckBox checkBox = parent.findViewById(R.id.select_thought_list_item_checkbox);
        if(checkBox.isChecked()) {
            checkBox.setChecked(false);
        }
        else {
            checkBox.setChecked(true);
        }
        theList.setItemChecked(theList.getPositionForView(view), checkBox.isChecked());
    }
}
