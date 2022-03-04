package com.nsk.address;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    ArrayList<AddressVO> list;

    public CustomAdapter() {
        list = new ArrayList<AddressVO>();
    }
    public void addItem(AddressVO vo){
        list.add(vo);
    }

    public void clearList(){
        list.clear();
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

        TextView name = view.findViewById(R.id.list_txt_name);
        name.setText(list.get(i).getName());
        TextView tel = view.findViewById(R.id.list_txt_tel);
        name.setText(list.get(i).getTel());
        
        return view;
    }
}
