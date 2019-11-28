package com.example.administrator.searchforlovedones;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.style.EasyEditSpan;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class RegisterActivity extends AppCompatActivity{

    private EditText et_reg_username;
    private EditText et_reg_pwd;
    private RadioGroup radioGroup;
    private RadioButton rb_familyhunter;
    private RadioButton rb_volunteer;
    private EditText et_reg_email;
    private EditText et_reg_tel;
    private EditText et_checknum;
    private Button btn_reg_sendnum;
    private Button btn_reg_regist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.register_activity);

        et_reg_username = findViewById(R.id.et_reg_username);
        et_reg_pwd = findViewById(R.id.et_reg_pwd);
        radioGroup =(RadioGroup) findViewById(R.id.radio_group);
        rb_familyhunter = findViewById(R.id.rb_familyhunter);
        rb_volunteer = findViewById(R.id.rb_volunteer);

        //为单选按钮添加事件监听
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton1 = findViewById(checkedId);//通过序号直接选哪一项
            }
        });

        //遍历单选按钮



    }
}
