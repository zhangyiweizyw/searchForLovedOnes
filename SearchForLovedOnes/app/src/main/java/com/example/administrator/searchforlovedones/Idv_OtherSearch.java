package com.example.administrator.searchforlovedones;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Idv_OtherSearch extends Activity {
    private TextView s_name;//被寻者姓名
    private TextView s_sex;//被寻者性别
    private TextView s_reason;//寻人原因及其他线索资料
    private TextView relation;//与被寻者联系
    private TextView y_name;//寻人者姓名
    private TextView y_sex;//寻人者性别
    private TextView y_age;//寻人者年龄
    private TextView y_email;//寻人者邮箱
    private TextView y_phone;//寻人者电话
    private TextView y_address;//寻人者住址

    private OtherSearchBean Spb;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.idv_othersearch);

        s_name=findViewById(R.id.inv_sname);//被寻者姓名
        s_sex=findViewById(R.id.inv_ssex);//被寻者性别
        s_reason=findViewById(R.id.inv_reason);//寻人原因及其他线索资料
        relation=findViewById(R.id.inv_srelation);//与被寻者联系
        y_name=findViewById(R.id.inv_yname);//寻人者姓名
        y_sex=findViewById(R.id.inv_ysex);//寻人者性别
        y_age=findViewById(R.id.inv_yage);//寻人者年龄
        y_email=findViewById(R.id.inv_yemail);//寻人者邮箱
        y_phone=findViewById(R.id.inv_yphone);//寻人者电话
        y_address=findViewById(R.id.inv_yaddress);//寻人者住址




        Spb=(OtherSearchBean) getIntent().getSerializableExtra("4");
        s_name.setText(Spb.getS_name());//被寻者姓名
        s_sex.setText(Spb.getS_sex());//被寻者性别
        s_reason.setText(Spb.getS_reason());//寻人原因及其他线索资料
        relation.setText(Spb.getRelation());//与被寻者联系
        y_name.setText(Spb.getY_name());//寻人者姓名
        y_sex.setText(Spb.getY_sex());//寻人者性别
        y_age.setText(String.valueOf(Spb.getY_age()));//寻人者年龄
        y_email.setText(Spb.getY_email());//寻人者邮箱
        y_phone.setText(Spb.getY_phone());//寻人者电话
        y_address.setText(Spb.getY_address());//寻人者住址


    }
}

