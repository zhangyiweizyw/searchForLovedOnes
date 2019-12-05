package com.example.sunyidong.qinfeng;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;


public class WritingMessage extends AppCompatActivity {
    private EditText ed_name;
    private EditText ed_phone;
    private EditText ed_QQ;
    private EditText ed_email;
    private EditText ed_content;
    private Comment comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.writemessage);
        ed_name=findViewById(R.id.ed_xingming);
        ed_phone=findViewById(R.id.ed_dianhua);
        ed_QQ=findViewById(R.id.ed_QQ);
        ed_email=findViewById(R.id.ed_dianziyoujian);
        ed_content=findViewById(R.id.ed_liuyan);


        Button bt_return=findViewById(R.id.bt_return);
        //返回按钮
        bt_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(WritingMessage.this,TrueFeelingsMessage.class);
                startActivity(intent);
            }
        });

        //发表按钮
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
                //实例化一条留言
                comment=new Comment(name,phone,email,content,qq,time);
                getValues();
                Intent intent=new Intent(WritingMessage.this,TrueFeelingsMessage.class);
                startActivity(intent);
            }
        });

    }
    private void getValues() {
        PageTextTask pageTextTask = new PageTextTask();
        pageTextTask.execute("http://10.7.88.19:8080/OkHttpServer/WriteCommentServlet");
    }
    private class PageTextTask extends AsyncTask {

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Object doInBackground(Object[] objects) {

            try {
                URL url = new URL((String) objects[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                OutputStream outputStream = connection.getOutputStream();
                Gson gson = new Gson();
                String jsonStr = gson.toJson(comment);
                Log.e("comment",jsonStr);
                outputStream.write(jsonStr.getBytes());
                InputStream is = connection.getInputStream();
                outputStream.close();
                is.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}