package com.joneikholm.basiclistdemo.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.joneikholm.basiclistdemo.Note;
import com.joneikholm.basiclistdemo.R;

import java.util.List;
import java.util.Map;

public class MyAdapter extends BaseAdapter {
    private List<Note> items;
    private LayoutInflater layoutInflater;

    public MyAdapter(Context context, List<Note> items) {
        this.items = items;
        layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return items.size();
    }
    @Override
    public Object getItem(int i) {
        return items.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = layoutInflater.inflate(R.layout.myrow, null);
        }
        TextView textviewParagraph = view.findViewById(R.id.textViewParagraph);
        textviewParagraph.setText(items.get(i).getNote());

        return view;
    }
}
