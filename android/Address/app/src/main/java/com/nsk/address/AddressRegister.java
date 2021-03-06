package com.nsk.address;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddressRegister extends AppCompatActivity {
    DBHelper dbHelper;
    EditText edtName;
    EditText edtTel;
    Button btnAdd;
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_register);
        dbHelper = new DBHelper(getApplicationContext());

        edtName = findViewById(R.id.update_edt_name);
        edtTel = findViewById(R.id.update_edt_tel);
        btnAdd = findViewById(R.id.update_btn_update);
        btnCancel = findViewById(R.id.update_btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name, tel;
                name = edtName.getText().toString();
                tel = edtTel.getText().toString();
                dbHelper.add(new AddressVO(name, tel));
                Toast.makeText(AddressRegister.this,"주소록 등록이 완료되었습니다.",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}