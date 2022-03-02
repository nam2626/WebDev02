package com.nsk.bookapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    ArrayList<BookVO> list;

    public CustomAdapter() {
        list = new ArrayList<BookVO>();
    }

    public void addItem(BookVO vo){
        list.add(vo);
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
        view = inflater.inflate(R.layout.custom_list_item,viewGroup,false);

        BookVO vo = list.get(i);
        ImageView thumnail = view.findViewById(R.id.img_thumnail);
        Glide.with(view).load(vo.getThumnail()).placeholder(R.drawable.book).into(thumnail);
        TextView txtTitle = view.findViewById(R.id.txt_title);
        txtTitle.setText(vo.getTitle());
        TextView txtInfo = view.findViewById(R.id.txt_info);
        txtInfo.setText(vo.getInfo());

        return view;
    }
}
