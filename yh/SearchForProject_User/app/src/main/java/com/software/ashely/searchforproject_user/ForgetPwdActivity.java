package com.software.ashely.searchforproject_user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ForgetPwdActivity extends AppCompatActivity {

    private EditText et_forget_tel;
    private EditText et_forget_checknum;
    private Button btn_forget_sendnum;
    private Button btn_forget_next;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_pwd_activity);

        //获取布局中各个元素ID
        et_forget_tel = findViewById(R.id.et_forget_tel);
        et_forget_checknum = findViewById(R.id.et_forget_checknum);
        btn_forget_sendnum = findViewById(R.id.btn_forget_sendnum);
        btn_forget_next = findViewById(R.id.btn_forget_next);

        btn_forget_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgetPwdActivity.this,ForgetPwd1Activity.class);
                startActivity(intent);

            }
        });
    }
}
