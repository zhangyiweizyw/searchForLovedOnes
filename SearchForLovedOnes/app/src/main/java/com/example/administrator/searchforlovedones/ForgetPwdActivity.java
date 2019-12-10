package com.example.administrator.searchforlovedones;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.loper7.layout.TitleBar;

public class ForgetPwdActivity extends Activity {

    private EditText et_forget_tel;
    private EditText et_forget_checknum;
    private Button btn_forget_sendnum;
    private Button btn_forget_next;
    private TitleBar bar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_pwd_activity);

        //获取布局中各个元素ID
        et_forget_tel = findViewById(R.id.et_forget_tel);
        et_forget_checknum = findViewById(R.id.et_forget_checknum);
        btn_forget_sendnum = findViewById(R.id.btn_forget_sendnum);
        btn_forget_next = findViewById(R.id.btn_forget_next);
        bar = findViewById(R.id.bar);
        bar.setBackImageResource(R.drawable.back);
        bar.setUseRipple(true);
        btn_forget_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgetPwdActivity.this,ForgetPwd1Activity.class);
                startActivity(intent);

            }
        });
    }
}
