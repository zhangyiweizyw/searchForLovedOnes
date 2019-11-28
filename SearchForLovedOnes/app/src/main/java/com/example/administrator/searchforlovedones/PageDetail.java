package com.example.administrator.searchforlovedones;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class PageDetail extends Activity {

    private ImageView titImg;
    private Button btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_detail);
        titImg = findViewById(R.id.title_img);
        btn_back = findViewById(R.id.first_back);
        //设置返回按钮的透明度
        btn_back.setAlpha(0.8f);
        Glide.with(getApplicationContext())
                .load(getResources().getDrawable(R.drawable.titleimg))
                .into(titImg);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
