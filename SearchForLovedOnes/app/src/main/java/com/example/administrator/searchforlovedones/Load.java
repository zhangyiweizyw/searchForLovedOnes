package com.example.administrator.searchforlovedones;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loper7.layout.TitleBar;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Load extends Activity {

    private EditText et_login_account;
    private EditText et_login_pwd;
    private TextView tv_user_regist;
    private TextView tv_forget_pwd;
    private OkHttpClient okHttpClient;
    private TitleBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        //获取对应布局中各个元素ID
        et_login_account = findViewById(R.id.et_login_account);
        et_login_pwd = findViewById(R.id.et_login_pwd);
        tv_user_regist = findViewById(R.id.tv_user_regist);
        tv_forget_pwd = findViewById(R.id.tv_forget_pwd);
        bar = findViewById(R.id.bar);

        bar.setBackImageResource(R.drawable.back);
        bar.setUseRipple(true);




        //设置登录页面跳转注册和忘记密码
        tv_user_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Load.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        tv_forget_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Load.this,ForgetPwdActivity.class);
                startActivity(intent);
            }
        });
    }


    public void buttonClicked(View view){
        switch (view.getId()){
            case R.id.btn_login:
                okHttpMethod();
        }
    }

    public void okHttpMethod(){
        try{
            String name = et_login_account.getText().toString();
            String password = et_login_pwd.getText().toString();

            JSONObject json = new JSONObject();
            json.put("name",name);
            json.put("password",password);
            Log.e("已经封装好数据",json.toString());

            //使用okHttp方式传送用户登录信息
            MediaType type = MediaType.parse("application/json;charset=UTF-8");
            RequestBody requestBody = RequestBody.create(type, json.toString());
            Log.e("requestBody",json.toString());
            Request request = new Request.Builder()
                    .url(Constant.BASE_URL + "/LoginServlet")
                    .post(requestBody)
                    .build();

            Log.e("request",json.toString());
            Call call = okHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if(response.isSuccessful()){
                        if(response.body().string().equals("{\"isSuccess\":\"1\"}")){//注意，response.body().string()只会调用一次
                            Log.e("login","用户已成功登录！");

                            Intent intent = new Intent(Load.this,ForgetPwd1Activity.class);
                            startActivity(intent);
                        } else{
                            Toast.makeText(Load.this,"用户名或密码输入错误，请重新输入！",Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.e("false", "响应失败！");
                    }
                }
            });

            Log.e("已经发送成功数据",json.toString());

        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
