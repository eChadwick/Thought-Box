package com.example.thoughtbox;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class SelectThoughtListAdapter extends ArrayAdapter<Thought> {

    SelectThoughtListAdapter(Context context, List<Thought> thoughtList) {
        super(context, 0, thoughtList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Thought theThought = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.select_thought_list_item, parent, false);
        }
        TextView thoughtContent = (TextView) convertView.findViewById(R.id.thought_list_item_content);
        TextView thoughtId = (TextView) convertView.findViewById(R.id.thought_list_item_id);

        thoughtContent.setText(theThought.getContent());
        thoughtId.setText(Integer.toString(theThought.getId()));

        return convertView;
    }
}
