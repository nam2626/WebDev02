package com.nsk.naversearch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
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

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchTask task = new SearchTask();
                task.execute(edtSearch.getText().toString());
            }
        });
    }

    public class SearchTask extends AsyncTask<String, String, ArrayList<ItemVO>>{

        @Override
        protected ArrayList<ItemVO> doInBackground(String... strings) {
            ArrayList<ItemVO> list = new ArrayList<ItemVO>();
            String result = null;
            String clientId = "9IEElpSYKwxvuax9FsIh";
            String clientSecret = "bBrxBHQPu0";
            String apiUrl = "https://openapi.naver.com/v1/search/blog.json";
            HttpURLConnection con = null;
            BufferedReader br = null;
            String text = strings[0];
            try {
                text = URLEncoder.encode(text, "UTF-8");
                URL url = new URL(apiUrl+"?query="+text);
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("X-Naver-Client-Id", clientId);
                con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
                //con.setDoOutput(true);//post일때만 적용

                int responseCode = con.getResponseCode();
                if(responseCode == HttpURLConnection.HTTP_OK) {
                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                }else {
                    br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                }

                result = new String();

                while (true) {
                    String str = br.readLine();
                    if(str == null) break;
                    result += str;
                }
                Log.d("SEARCH", "doInBackground: "+result);
                JSONObject json = new JSONObject(result);
                JSONArray arr = json.getJSONArray("items");
                for(int i=0;i<arr.length();i++) {
                    JSONObject obj = (JSONObject) arr.get(i);
                    String title =obj.getString("title");
                    String description =obj.getString("description");
                    String link =obj.getString("link");
                    list.add(new ItemVO(title,description,link));
                }
                Log.d("SEARCH", "doInBackground: "+list.toString());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return list;
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




