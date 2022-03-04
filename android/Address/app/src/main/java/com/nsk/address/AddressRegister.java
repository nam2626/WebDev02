package com.nsk.address;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        edtName = findViewById(R.id.register_edt_name);
        edtTel = findViewById(R.id.register_edt_tel);
        btnAdd = findViewById(R.id.register_btn_add);
        btnCancel = findViewById(R.id.register_btn_cancel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name, tel;
                name = edtName.getText().toString();
                tel = edtTel.getText().toString();
                dbHelper.add(new AddressVO(name, tel));
            }
        });
    }
}