package com.nsk.naversearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    private ArrayList<ItemVO> list;

    public CustomAdapter() {
        list = new ArrayList<ItemVO>();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Context context = viewGroup.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.custom_list_layout,viewGroup,false);

        TextView txtTitle = view.findViewById(R.id.txt_title);
        TextView txtDescription = view.findViewById(R.id.txt_description);

        txtTitle.setText(list.get(i).getTitle());
        txtDescription.setText(list.get(i).getDescription());

        return view;
    }

    public void addItem(ItemVO vo){
        list.add(vo);
    }

    public void clear() {
        list.clear();
    }
}
