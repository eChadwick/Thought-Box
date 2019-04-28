package com.example.thoughtbox;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import java.util.List;

public class CategoryClickListener implements View.OnClickListener {

    private Category mClickedCategory;
    private ThoughtBoxRoomDatabase mDb;
    private CategoryDao mCategoryDao;

    CategoryClickListener( Category category ) {
        this.mClickedCategory = category;
    }

    @Override
    public void onClick(View v) {
        mDb = ThoughtBoxRoomDatabase.getDatabase(v.getContext());
        mCategoryDao = mDb.categoryDao();
        final Context currentContext = v.getContext();
        final ViewParent parentView = (ViewGroup) v.getParent().getParent();

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(v.getContext());
        alertBuilder.setTitle("What would you like to do with this Category?");
        alertBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mCategoryDao.delete(mClickedCategory);
                List<Category> allCategories = mCategoryDao.getAllCategories();
                CategoryRecyclerAdapter newAdapter = new CategoryRecyclerAdapter(currentContext, allCategories);
                RecyclerView theView = ((ViewGroup) parentView).findViewById(R.id.category_list);
                theView.setAdapter(newAdapter);
            }
        });

        AlertDialog theDialog = alertBuilder.create();
        theDialog.show();
    }
}
