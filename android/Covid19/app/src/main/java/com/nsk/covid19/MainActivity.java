package com.nsk.covid19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {
    TextView txtResult;
    Button btnLoad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResult = findViewById(R.id.txt_result);
        btnLoad = findViewById(R.id.btn_load);

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Covid19 covid19 = new Covid19();
                covid19.execute();
            }
        });
    }

    public class Covid19 extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... strings) {

            try {
                StringBuilder urlBuilder = new StringBuilder("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson");
                urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=hpOVfNem4MVro1QdBZTMTq%2FMZs%2B8yylSvxNQlqPiEQec%2Bo99WRRbIvrVqLltto5W0TmluoxR7uQHpHFNZ146qg%3D%3D");
                urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
                urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8"));
                urlBuilder.append("&" + URLEncoder.encode("startCreateDt","UTF-8") + "=" + URLEncoder.encode("20220226", "UTF-8"));
                urlBuilder.append("&" + URLEncoder.encode("endCreateDt","UTF-8") + "=" + URLEncoder.encode("20220303", "UTF-8"));

                DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = builderFactory.newDocumentBuilder();
                Document document = builder.parse(urlBuilder.toString());
                document.normalize();
                NodeList tagList = document.getElementsByTagName("item");
                ArrayList<String> dateList = new ArrayList<String>();
                ArrayList<Integer> decideList = new ArrayList<Integer>();
                for(int i = 0; i < tagList.getLength(); i++) {
                    NodeList list = tagList.item(i).getChildNodes();
                    for(int j = 0; j < list.getLength(); j++) {
                        if("decideCnt".equals(list.item(j).getNodeName())) {
                            Log.d("Covid19", "doInBackground: " + list.item(j).getNodeName() + " - " + list.item(j).getTextContent());
                            decideList.add(Integer.parseInt(list.item(j).getTextContent()));
                        }
                        if("stateDt".equals(list.item(j).getNodeName())) {
                            Log.d("Covid19", "doInBackground: " + list.item(j).getNodeName() + " - " + list.item(j).getTextContent());
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                            Date date = sdf.parse(list.item(j).getTextContent());
                            date.setDate(date.getDate()-1);
                            sdf = new SimpleDateFormat("yyyy-MM-dd");
                            dateList.add(sdf.format(date));
                        }

                    }
                    Log.d("Covid19","----------------------");
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

        }
    }
}