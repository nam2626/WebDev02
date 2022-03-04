package com.nsk.address;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btnRegister;
    DBHelper dbHelper;
    ListView listAddress;
    CustomAdapter adapter;
    ImageButton btnSearch;
    EditText edtSearch;
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
        listAddress.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,AddressUpdateActivity.class);
                AddressVO vo = (AddressVO) adapter.getItem(i);
                if(vo == null) return;
                intent.putExtra("name",vo.getName());
                intent.putExtra("tel",vo.getTel());
                intent.putExtra("id",vo.getId());
                startActivity(intent);
            }
        });
        edtSearch = findViewById(R.id.main_edt_search);
        btnSearch = findViewById(R.id.main_btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<AddressVO> list =  dbHelper.selectAddressVO(edtSearch.getText().toString());
                adapter.clearList();
                for(int i=0;i<list.size();i++){
                    adapter.addItem(list.get(i));
                }
                adapter.notifyDataSetChanged();
            }
        });
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