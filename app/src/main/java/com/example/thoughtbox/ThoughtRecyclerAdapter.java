package com.example.thoughtbox;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ThoughtRecyclerAdapter extends RecyclerView.Adapter {

    class ThoughtViewHolder extends RecyclerView.ViewHolder {
        private TextView mThoughtHolderItem;

        ThoughtViewHolder(View itemView) {
            super(itemView);
            mThoughtHolderItem = itemView.findViewById(R.id.thought_list_item);
        }

    }

    private final LayoutInflater mInflater;
    private List<Thought> mThoughts;

    ThoughtRecyclerAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ThoughtViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.thought_list_item, parent);
        return new ThoughtViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder (RecyclerView.ViewHolder holder, int position) {
        if ( mThoughts != null ) {
            Thought aThought = mThoughts.get(position);
            ((ThoughtViewHolder) holder).mThoughtHolderItem.setText(aThought.getContent());
        } else {
            ((ThoughtViewHolder)holder).mThoughtHolderItem.setText("No saved thoughts to show.");
        }
    }

    @Override
    public int getItemCount() {
        if(mThoughts != null) {
            return mThoughts.size();
        } else {
            return 0;
        }
    }
}
