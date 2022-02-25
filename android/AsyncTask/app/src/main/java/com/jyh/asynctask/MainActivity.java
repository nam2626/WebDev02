package com.jyh.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ProgressBar bar;
    Button btnStart;
    Button btnStop;
    ProgressTask task;
    int value = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar = findViewById(R.id.progressBar);
        btnStart = findViewById(R.id.btn_start);
        btnStop = findViewById(R.id.btn_stop);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ProgressThread thread = new ProgressThread();
//                thread.start();
                task = new ProgressTask();
                task.execute();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task.cancel(true);
            }
        });
    }

    /*
        value 값을 1초마다 5씩 증가시키는 스레드 작성
        value 값이 100되면 스레드 종료
        스레드 클래스 명 : ProgressThread
    public class ProgressThread extends Thread{
        @Override
        public void run() {
            while(value<100){
                value += 5;
                bar.setProgress(value,true);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
     */

    public class ProgressTask extends AsyncTask<Integer,Integer,Integer>{

        @Override
        protected Integer doInBackground(Integer... integers) {
            //태스크 처리 부분
            while(!isCancelled()){
                value++;
                if(value>=100) break;
                else
                   publishProgress(value);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return value;
        }

        @Override
        protected void onCancelled() {
            //태스크 종료 신호를 받았을 때
            bar.setProgress(0);
            Toast.makeText(MainActivity.this,"태스크를 종료합니다",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //현재 작업 진행 상태 표시 ---> doInBackground에서 publishProgress가 호출
            bar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            //결과값 받아서 UI 처리하는 부분
            bar.setProgress(integer);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //백그라운드 작업 전에 실행할 초기화 영역
            value = 0;
            bar.setProgress(0);
        }
    }
}






