package com.example.administrator.searchforlovedones;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class IndivdualCenter extends Fragment {
    private ImageView back;
    private ImageView head;
    private TextView username;
    private TextView tel;
    private TextView email;
    private ImageView release;
    private ImageView cpwd;
    private View page;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        page = inflater.inflate(R.layout.fragment_page4,container,false);
        page.setBackgroundColor(Color.WHITE);
        findId();
        Glide.with(this)
                .load(R.drawable.touxiang_128)
//                .bitmapTransform(new BlurTransformation(this, 25), new CenterCrop(this))
                .into(back);
        //设置圆形图像
        Glide.with(this)
                .load(R.drawable.touxiang_128)
//                .bitmapTransform(new CropCircleTransformation(this))
                .into(head);
        return page;
    }

    private void findId() {
        back=page.findViewById(R.id.indiv_back);
        head=page.findViewById(R.id.indiv_head);
        username=page.findViewById(R.id.idv_name);
        tel=page.findViewById(R.id.idv_tel);
        email=page.findViewById(R.id.idv_email);
        release=page.findViewById(R.id.idv_release);
        cpwd=page.findViewById(R.id.idv_cpwd);
    }
}
