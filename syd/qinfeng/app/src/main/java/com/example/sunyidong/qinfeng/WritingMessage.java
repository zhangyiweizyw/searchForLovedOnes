package com.example.sunyidong.qinfeng;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WritingMessage extends AppCompatActivity {
    private SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.writemessage);
        final EditText ed_name=findViewById(R.id.ed_xingming);
        final EditText ed_phone=findViewById(R.id.ed_dianhua);
        final EditText ed_QQ=findViewById(R.id.ed_QQ);
        final EditText ed_email=findViewById(R.id.ed_dianziyoujian);
        final EditText ed_content=findViewById(R.id.ed_liuyan);

        Newbase newbase=new Newbase(this,"TrueFellingsMessage.db",1);
        database = newbase.getWritableDatabase();

        Button bt_return=findViewById(R.id.bt_return);
        //返回按钮
        bt_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WritingMessage.this,TrueFeelingsMessage.class);
                startActivity(intent);
            }
        });

        Button bt_submit=findViewById(R.id.bt_submit);
        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=ed_name.getText().toString();
                String phone=ed_phone.getText().toString();
                String qq =ed_QQ.getText().toString();
                String email=ed_email.getText().toString();
                String content=ed_content.getText().toString();
                Date date = new Date();
                SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String time=dateFormat.format(date);
                insertdata(name,phone,qq,email,content,time);

                Intent intent=new Intent(WritingMessage.this,TrueFeelingsMessage.class);
                startActivity(intent);
            }
        });

    }
    public void insertdata(String name,String phone,String qq,String email,String content,String time){
        ContentValues emplo = new ContentValues();
        emplo.put("name",name);
        emplo.put("phone",phone);
        emplo.put("qq",qq);
        emplo.put("email",email);
        emplo.put("content",content);
        emplo.put("time",time);
        database.insert("liuyantable",null,emplo);
    }
}