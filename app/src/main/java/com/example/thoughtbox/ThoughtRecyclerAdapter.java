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

public class ThoughtRecyclerAdapter extends RecyclerView.Adapter<ThoughtRecyclerAdapter.ThoughtViewHolder> {

    class ThoughtViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mThoughtHolderItem;

        ThoughtViewHolder(View itemView) {
            super(itemView);
            mThoughtHolderItem = itemView.findViewById(R.id.thought_list_item);
        }
    }

    private final LayoutInflater mInflater;
    private List<Thought> mThoughts;

    ThoughtRecyclerAdapter(Context context, List<Thought> allThoughts) {
        mInflater = LayoutInflater.from(context);
        mThoughts = allThoughts;
    }

    @Override
    public ThoughtViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.thought_list_item, parent, false);
        ThoughtViewHolder aViewHolder = new ThoughtViewHolder(itemView);
        return aViewHolder;
    }

    @Override
    public void onBindViewHolder (@NonNull ThoughtViewHolder holder, int position) {
        if ( mThoughts != null ) {
            Thought aThought = mThoughts.get(position);

            TextView thoughtContent = holder.mThoughtHolderItem.findViewById(R.id.thought_list_item_content);
            thoughtContent.setText(aThought.getContent());

            thoughtContent.setOnClickListener(new ThoughtClickListener(aThought));
        } else {
            TextView thoughtContent = holder.mThoughtHolderItem.findViewById(R.id.thought_list_item_content);
            thoughtContent.setText(R.string.no_saved_thoughts);
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
