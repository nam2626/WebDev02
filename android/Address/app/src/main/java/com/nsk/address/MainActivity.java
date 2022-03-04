package com.nsk.address;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btnRegister;
    DBHelper dbHelper;
    ListView listAddress;
    CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);
        btnRegister = findViewById(R.id.main_btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddressRegister.class);
                startActivity(intent);
            }
        });
        adapter = new CustomAdapter();
        listAddress = findViewById(R.id.list_address);
        listAddress.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        dbHelper = new DBHelper(this);
        Log.d("Address", "onResume");
        List<AddressVO> list = dbHelper.selectAllAddressVO();
        Log.d("Address", "onResume: "+list.toString());
        adapter.clearList();
        for(int i=0;i<list.size();i++){
            adapter.addItem(list.get(i));
        }
        adapter.notifyDataSetChanged();
    }
}