package com.example.thoughtbox;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import java.util.List;

public class ThoughtClickListener implements View.OnClickListener {
    private Thought clickedThought;
    private ThoughtBoxRoomDatabase db;
    private ThoughtDao thoughtDao;

    ThoughtClickListener(Thought aThought) {
        clickedThought = aThought;
    }

    @Override
    public void onClick(final View v) {
        db = ThoughtBoxRoomDatabase.getDatabase(v.getContext());
        thoughtDao = db.thoughtDao();
        final Context currentContext = v.getContext();
        final ViewParent parentView = (ViewGroup) v.getParent().getParent();

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(v.getContext());
        alertBuilder.setTitle("What would you like to do with this thought?");
        alertBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                thoughtDao.deleteThought(clickedThought);
                List<Thought> allThoughts = thoughtDao.getAllThoughts();
                ThoughtRecyclerAdapter newAdapter = new ThoughtRecyclerAdapter(currentContext, allThoughts);
                RecyclerView theView = ((ViewGroup) parentView).findViewById(R.id.all_thoughts_list);
                theView.setAdapter(newAdapter);
            }
        });
        alertBuilder.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent edit = new Intent(currentContext, CreateThoughtActivity.class);
                edit.putExtra("ThoughtId", clickedThought.getId());
                currentContext.startActivity(edit);
            }
        });

        AlertDialog dialog = alertBuilder.create();
        dialog.show();
    }
}