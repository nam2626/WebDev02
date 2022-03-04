package com.nsk.address;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AddressRegister extends AppCompatActivity {
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_register);
        dbHelper = new DBHelper(getApplicationContext());
    }
}