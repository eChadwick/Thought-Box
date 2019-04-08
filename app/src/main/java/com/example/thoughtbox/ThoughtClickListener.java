package com.example.thoughtbox;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;

public class ThoughtClickListener implements View.OnClickListener {
    private Thought clickedThought;

    ThoughtClickListener(Thought aThought) {
        clickedThought = aThought;
    }

    @Override
    public void onClick(View v) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(v.getContext());
        alertBuilder.setTitle("What would you like to do with this thought?");
        alertBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

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