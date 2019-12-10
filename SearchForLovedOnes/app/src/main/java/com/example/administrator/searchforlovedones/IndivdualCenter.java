package com.example.administrator.searchforlovedones;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static com.bumptech.glide.Glide.*;

public class IndivdualCenter extends AppCompatActivity {
    private ImageView back;
    private ImageView head;
    private TextView username;
    private TextView tel;
    private TextView email;
    private ImageView release;
    private ImageView cpwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.individualcenter);
        back=findViewById(R.id.indiv_back);
        head=findViewById(R.id.indiv_head);
        username=findViewById(R.id.idv_name);
        tel=findViewById(R.id.idv_tel);
        email=findViewById(R.id.idv_email);
        release=findViewById(R.id.idv_release);
        cpwd=findViewById(R.id.idv_cpwd);
        Glide.with(this)
                .load(R.drawable.touxiang_128)
//                .bitmapTransform(new BlurTransformation(this, 25), new CenterCrop(this))
                .into(back);
        //设置圆形图像
        Glide.with(this)
                .load(R.drawable.touxiang_128)
//                .bitmapTransform(new CropCircleTransformation(this))
                .into(head);


    }
}
