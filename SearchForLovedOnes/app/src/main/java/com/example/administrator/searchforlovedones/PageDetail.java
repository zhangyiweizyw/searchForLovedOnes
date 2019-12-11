package com.example.administrator.searchforlovedones;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

public class PageDetail extends Activity {
    private TitleBar bar;
    private ImageView titImg;
//    private Button btn_back;
    private TextView Title;
    private TextView Content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_detail);
        titImg = findViewById(R.id.title_img);
//        btn_back = findViewById(R.id.first_back);
        Title = findViewById(R.id.tit);
        Content = findViewById(R.id.con);
        bar = findViewById(R.id.bar);
        bar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(View v) {
                finish();
            }

            @Override
            public void onTitleClick(View v) {

            }

            @Override
            public void onRightClick(View v) {

            }
        });
        //设置返回按钮的透明度
//        btn_back.setAlpha(0.8f);
        Title.setText(getIntent().getStringExtra("title"));
        Content.setText(getIntent().getStringExtra("content"));
        Glide.with(getApplicationContext())
                .load("http://"+Constant.IP+":8080/searchfor_prj/images/"+getIntent().getStringExtra("imgName")+".jpg")
                .into(titImg);

//        btn_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }

}
