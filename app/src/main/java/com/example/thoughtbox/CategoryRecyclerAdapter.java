package com.example.thoughtbox;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.CategoryViewHolder> {

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mCategoryHolderItem;

        CategoryViewHolder(View view) {
            super(view);
            mCategoryHolderItem = view.findViewById(R.id.category_list_item);
        }
    }

    private final LayoutInflater mInflater;
    private List<Category> mCategoryList;

    CategoryRecyclerAdapter(Context context, List<Category> aCategoryList) {
        mInflater = LayoutInflater.from(context);
        mCategoryList = aCategoryList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.category_list_item, viewGroup, false);
        CategoryViewHolder aViewHolder = new CategoryViewHolder(itemView);
        return aViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        if(mCategoryList != null) {
            Category theCategory = mCategoryList.get(position);

            TextView categoryContent = holder.mCategoryHolderItem.findViewById(R.id.category_list_item_content);
            categoryContent.setText(theCategory.getName());
            categoryContent.setOnClickListener(new CategoryClickListener(theCategory));
        }
    }

    @Override
    public int getItemCount() {
        if(mCategoryList != null)
            return mCategoryList.size();
        else
            return 0;
    }
}
