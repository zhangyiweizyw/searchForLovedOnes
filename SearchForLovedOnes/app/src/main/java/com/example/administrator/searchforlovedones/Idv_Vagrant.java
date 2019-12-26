package com.example.administrator.searchforlovedones;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loper7.layout.TitleBar;

public class Idv_Vagrant extends Activity {
    private TextView name;//流浪者姓名
    private TextView sex;//流浪者性别
    private TextView age;//流浪者大约年龄
    private TextView findaddress;//流浪者发现地址

    private TextView begintime;//开始流浪时间
    private TextView targetfamily;//目标家庭信息
    private TextView describe;//流浪者特征描述
    private TextView phonenumber;//发现者联系方式
    private TitleBar bar;

    private Vagrant Spb;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.idv_vagrant);
        bar = findViewById(R.id.bar);
        bar.setBackImageResource(R.drawable.back);
        bar.setUseRipple(true);
        name=findViewById(R.id.inv_v_name);//流浪者姓名
        sex=findViewById(R.id.inv_v_sex);//流浪者性别
        age=findViewById(R.id.inv_v_age);//流浪者大约年龄
        findaddress=findViewById(R.id.inv_v_findaddr);//流浪者发现地址

        begintime=findViewById(R.id.inv_v_missdate);//开始流浪时间
        targetfamily=findViewById(R.id.inv_v_targetfamily);//目标家庭信息
        describe=findViewById(R.id.inv_v_feature);//流浪者特征描述
        phonenumber=findViewById(R.id.inv_v_phone);//发现者联系方式



        String str = getIntent().getStringExtra("3");
        Spb = new Gson().fromJson(str, Vagrant.class);
        name.setText(Spb.getName());//流浪者姓名
        sex.setText(Spb.getSex());//流浪者性别
        age.setText(Spb.getAge());//流浪者大约年龄
        findaddress.setText(Spb.getFindaddress());//流浪者发现地址

        begintime.setText(Spb.getBegintime());//开始流浪时间
        targetfamily.setText(Spb.getTargetfamily());//目标家庭信息
        describe.setText(Spb.getDescribe());//流浪者特征描述
        phonenumber.setText(Spb.getPhonenumber());//发现者联系方式


    }
}

