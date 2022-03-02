package com.nsk.glide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] url = {
            "https://cdn.pixabay.com/photo/2020/11/14/09/36/cape-town-5741110_960_720.jpg",
                "https://cdn.pixabay.com/photo/2017/06/22/20/23/dandelion-2432381_960_720.jpg",
                "https://cdn.pixabay.com/photo/2020/05/08/16/06/dog-5146351_960_720.jpg",
                "https://cdn.pixabay.com/photo/2022/02/21/21/18/animal-7027635_960_720.jpg"
        };
        ImageView imageView = findViewById(R.id.imageView);
        ImageView imageView2 = findViewById(R.id.imageView2);
        ImageView imageView3 = findViewById(R.id.imageView3);
        ImageView imageView4 = findViewById(R.id.imageView4);
        //with : Context 정보
        //load : 불러올 이미지, Url, 경로, 바이트....
        //placeholder : Glide로 이미지 로딩시 로딩전에 보여줄 이미지
        //into : 이미지를 보여줄 view를 지정
        Glide.with(this).load(url[0]).placeholder(R.drawable.ic_launcher_background).into(imageView);
        Glide.with(this).load(url[1]).placeholder(R.drawable.ic_launcher_background).into(imageView2);
        Glide.with(this).load(url[2]).placeholder(R.drawable.ic_launcher_background).into(imageView3);
        Glide.with(this).load(url[3]).placeholder(R.drawable.ic_launcher_background).into(imageView4);
    }
}