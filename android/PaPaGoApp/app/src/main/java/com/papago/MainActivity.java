package com.papago;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

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
            String result = null;
            String clientId = "클라이언트 아이디";
            String clientSecret = "클라이언트 시크릿";
            String apiUrl = "https://openapi.naver.com/v1/papago/n2mt";
            HttpURLConnection con = null;
            DataOutputStream dos = null;
            BufferedReader br = null;

            try {
                String text = URLEncoder.encode(strings[0],"UTF-8");

                URL url = new URL(apiUrl);
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("X-Naver-Client-Id", clientId);
                con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
                con.setDoOutput(true);

                dos = new DataOutputStream(con.getOutputStream());
                String postParams = "source=ko&target=en&text="+text;
                dos.writeBytes(postParams);
                dos.flush();
                dos.close();
                //응답
                int responseCode = con.getResponseCode(); //응답 코드 받음
                if(responseCode == HttpURLConnection.HTTP_OK) {
                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                }else {
                    br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                }
                result = new String();
                while(true) {
                    String str = br.readLine();
                    if(str == null) break;
                    result += str;
                }
                JSONObject json = new JSONObject(result);
                result = json.getJSONObject("message").getJSONObject("result").getString("translatedText");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s == null)
                s = "작업에 문제가 생겼습니다.";
            txtResult.setText(s);
        }
    }

}






