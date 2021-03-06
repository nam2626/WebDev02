package com.nsk.bookapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
    ListView lstResult;
    CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new CustomAdapter();
        edtSearch = findViewById(R.id.edt_search);
        btnSearch = findViewById(R.id.btn_search);
        lstResult = findViewById(R.id.lst_result);
        lstResult.setAdapter(adapter);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookTask task = new BookTask();
                task.execute(edtSearch.getText().toString());
            }
        });

        lstResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BookVO vo = (BookVO) adapter.getItem(i);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(vo.getLink()));
                startActivity(intent);
            }
        });
    }

    public class BookTask extends AsyncTask<String, String, ArrayList<BookVO>>{

        @Override
        protected ArrayList<BookVO> doInBackground(String... strings) {
            ArrayList<BookVO> list = new ArrayList<BookVO>();
            String result = null;
            String clientId = "9IEElpSYKwxvuax9FsIh";
            String clientSecret = "bBrxBHQPu0";
            String apiUrl = "https://openapi.naver.com/v1/search/book.json";
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
                //con.setDoOutput(true);//post????????? ??????

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
                    String title = obj.getString("title")
                            .replace("<b>","").replace("</b>","");
                    String author = obj.getString("author");
                    String publisher = obj.getString("publisher");
                    String thumnail = obj.getString("image");
                    String link = obj.getString("link");
                    list.add(new BookVO(title,"?????? : "+author + ", ????????? : "+publisher,thumnail,link));
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
        protected void onPostExecute(ArrayList<BookVO> list) {
            super.onPostExecute(list);
            adapter.clear();
            for (int i=0;i<list.size();i++){
                adapter.addItem(list.get(i));
            }
            adapter.notifyDataSetChanged();
        }
    }
}