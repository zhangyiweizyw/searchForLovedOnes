package com.example.administrator.searchforlovedones;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import cn.sharesdk.onekeyshare.OnekeyShare;

public class PageDetail extends Activity {
    private TitleBar bar;
    private ImageView titImg;
//    private Button btn_back;
    private TextView Title;
    private TextView Content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_page_detail);
        titImg = findViewById(R.id.title_img);
//        btn_back = findViewById(R.id.first_back);
        Title = findViewById(R.id.tit);
        Content = findViewById(R.id.con);
        bar = findViewById(R.id.bar);
        bar.setLeftIcon(R.drawable.detailback);
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
                showShare();

            }
        });

        //设置返回按钮的透明度
        Title.setText(getIntent().getStringExtra("title"));
        Content.setText(getIntent().getStringExtra("content"));
        Glide.with(getApplicationContext())
                .load("http://"+ Constant.IP+":8080/searchfor_prj/images/"+getIntent().getStringExtra("imgName")+".jpg")
                .into(titImg);
    }
    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle("亲逢APP："+getIntent().getStringExtra("title"));
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl("http://116.62.13.180:8080/searchfor_prj/index.jsp");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("亲逢APP："+getIntent().getStringExtra("content"));
        // imagePath是图片的本地路径，确保SDcard下面存在此张图片
//        oks.setImagePath("/sdcard/test.jpg");
        // url在微信、Facebook等平台中使用
        oks.setUrl("http://116.62.13.180:8080/searchfor_prj/index.jsp");
        // 启动分享GUI
        oks.show(this);
    }

}
