package com.nsk.naversearch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edtSearch;
    Button btnSearch;
    ListView listResult;
    CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtSearch = findViewById(R.id.edt_search);
        btnSearch = findViewById(R.id.btn_search);
        listResult = findViewById(R.id.lst_result);
        adapter = new CustomAdapter();
        listResult.setAdapter(adapter);
    }

    public class SearchTask extends AsyncTask<String, String, ArrayList<ItemVO>>{

        @Override
        protected ArrayList<ItemVO> doInBackground(String... strings) {
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<ItemVO> list) {
            super.onPostExecute(list);
            adapter.clear();
            for(int i=0;i<list.size();i++){
                adapter.addItem(list.get(i));
            }
            adapter.notifyDataSetChanged();
        }
    }
}




