package com.software.ashely.searchforproject_user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText et_login_account;
    private EditText et_login_pwd;
    private TextView tv_user_regist;
    private TextView tv_forget_pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_login_account = findViewById(R.id.et_login_account);
        et_login_pwd = findViewById(R.id.et_login_pwd);
        tv_user_regist = findViewById(R.id.tv_user_regist);
        tv_forget_pwd = findViewById(R.id.tv_forget_pwd);

        //设置登录页面跳转注册和忘记密码
        tv_user_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        tv_forget_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ForgetPwdActivity.class);
                startActivity(intent);
            }
        });
    }
}
