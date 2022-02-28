package com.papago;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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
                String text = edtInput.getText().toString();
                PaPago papago = new PaPago();
                papago.execute(text);
            }
        });
    }

    public class PaPago extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... strings) {
            Log.d("PAPAGO", "doInBackground: "+strings[0]);
            String result = strings[0];
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            txtResult.setText(s);
        }
    }

}






