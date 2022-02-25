package com.papago;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText edtInput;
    TextView txtResult;
    Button btnTranslate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtInput = findViewById(R.id.edt_input);
        txtResult = findViewById(R.id.txt_result);
        btnTranslate = findViewById(R.id.btn_translate);

        btnTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
    }
}






