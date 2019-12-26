package com.example.administrator.searchforlovedones;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loper7.layout.TitleBar;

public class Idv_SerachFamily extends Activity {
    private TextView l_name;//失踪者姓名
    private TextView l_sex;//失踪者性别
    private TextView l_borndate;//失踪者出生日期
    private TextView l_phone;//联系方式
    private TextView l_email;//邮箱
    private TextView lheight;//失踪者失踪时大致身高
    private TextView l_missdate;//失踪日期
    private TextView isBlood;//是否采血
    private TextView isReport;//是否报案
    private TextView l_native;//失踪人籍贯
    private TextView l_missaddr;//失踪地点
    private TextView l_fearture;//失踪人特征描述
    private TextView l_process;//失踪经过
    private TextView l_family;//家庭背景及其线索资料
    private TextView t_familyaddr;//目标家庭地址
    private TextView t_relationfamily;//与目标家庭联系
    private TextView t_describefamily;//目标家庭描述
    private SearchFamilyBean Spb;
    private TitleBar bar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.idv_serachfamily);
        bar = findViewById(R.id.bar);
        bar.setBackImageResource(R.drawable.back);
        bar.setUseRipple(true);
        l_name=findViewById(R.id.l_name);
        l_sex=findViewById(R.id.l_sex);
        l_borndate=findViewById(R.id.l_borndate);
        l_phone=findViewById(R.id.l_phone);

        l_email =findViewById(R.id. l_email);
        lheight=findViewById(R.id. lheight);
        l_missdate=findViewById(R.id. l_missdate);
        isBlood=findViewById(R.id.l_isblood);
        isReport=findViewById(R.id. l_isreport );
        l_native=findViewById(R.id. l_native);
        l_missaddr=findViewById(R.id. l_missaddr);
        l_fearture =findViewById(R.id.l_feature);
        l_process =findViewById(R.id. l_process);
        l_family=findViewById(R.id. l_family);
        t_familyaddr =findViewById(R.id. t_familyaddr);
        t_relationfamily =findViewById(R.id. t_relationfamily);
        t_describefamily=findViewById(R.id. t_describefamily);


        String str = getIntent().getStringExtra("2");
        Spb = new Gson().fromJson(str, SearchFamilyBean.class);
        l_name.setText(Spb.getL_name());
        l_sex.setText(Spb.getL_sex());
        l_borndate.setText(Spb.getL_borndate());
        l_phone.setText(Spb.getL_phone());

        l_email .setText(Spb.getL_email());
        lheight.setText(Spb.getLheight());
        l_missdate.setText(Spb.getL_missdate());
        isBlood.setText(Spb.getIsBlood());
        isReport.setText(Spb.getIsReport());
        l_native.setText(Spb.getL_native());
        l_missaddr.setText(Spb.getL_missaddr());
        l_fearture .setText(Spb.getL_fearture());
        l_process .setText(Spb.getL_process());
        l_family.setText(Spb.getL_family());
        t_familyaddr .setText(Spb.getT_familyaddr());
        t_relationfamily .setText(Spb.getT_relationfamily());
        t_describefamily.setText(Spb.getT_describefamily());
    }
}

