package com.nsk.address;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddressUpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_update);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String tel = intent.getStringExtra("tel");
        int id = intent.getIntExtra("id",0);
        EditText edtName = findViewById(R.id.update_edt_name);
        edtName.setText(name);
        EditText edtTel = findViewById(R.id.update_edt_tel);
        edtTel.setText(tel);

        Button btnUpdate = findViewById(R.id.update_btn_update);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(getApplicationContext());
                AddressVO vo = new AddressVO(id, edtName.getText().toString(), edtTel.getText().toString());
                dbHelper.update(vo);
                finish();
            }
        });
        Button btnDelete = findViewById(R.id.update_btn_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(getApplicationContext());
                dbHelper.delete(id);
                finish();
            }
        });
        Button btnCancel = findViewById(R.id.update_btn_cancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}