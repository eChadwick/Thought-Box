package com.example.thoughtbox;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;

public class ThoughtClickListener implements View.OnClickListener {
    private Thought clickedThought;
    private ThoughtRoomDatabase db;
    private ThoughtDao thoughtDao;

    ThoughtClickListener(Thought aThought) {
        clickedThought = aThought;
    }

    @Override
    public void onClick(final View v) {
        db = ThoughtRoomDatabase.getDatabase(v.getContext());
        thoughtDao = db.thoughtDao();

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(v.getContext());
        alertBuilder.setTitle("What would you like to do with this thought?");
        alertBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                thoughtDao.deleteThought(clickedThought);
                Context currentContext = v.getContext();
                Intent relaunch = new Intent(currentContext, ShowAllThoughts.class);
                currentContext.startActivity(relaunch);
            }
        });
        alertBuilder.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = alertBuilder.create();
        dialog.show();
    }
}