package com.example.administrator.searchforlovedones;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Looper;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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

public class Load extends Activity implements View.OnClickListener{

    private EditText et_login_account;
    private EditText et_login_pwd;
    private ImageView img_pwdshow;
    private TextView tv_user_regist;
    private TextView tv_forget_pwd;
    private OkHttpClient okHttpClient;
    private String name;
    private String password;

    //实例化一键清除内容
    private EditText input;
    private ImageView inputDel;

    //密码明文密文切换功能
    private boolean showPwd = false;//默认不显示密码

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
        img_pwdshow = (ImageView) findViewById(R.id.img_pwdshow);
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

        //实现一键清空
        initView();
        initListener();

        //实现明文密文切换
        initEvents();
    }

    public void buttonClicked(View view){
        switch (view.getId()){
            case R.id.btn_login:
                name = et_login_account.getText().toString();
                password = et_login_pwd.getText().toString();
                Log.e("login",name+password);
                if(name != null && !password.equals("")) {
                    okHttpMethod(name,password);
                    Log.e("load","okhttp完成");
                }else if(name == null || name.equals("")){
                    Toast.makeText(Load.this,"用户名不能为空！",Toast.LENGTH_LONG).show();
                }else if(password == null || password.equals("")){
                    Toast.makeText(Load.this,"密码不能为空！",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn_cancel:
                Intent intent = new Intent(Load.this,MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void okHttpMethod(String name,String password){
        try{
            Log.e("load",name+password);
            JSONObject json = new JSONObject();
                json.put("name",name);
                json.put("password",password);
                Log.e("已经封装好数据",json.toString());

                //使用okHttp方式传送用户登录信息
                MediaType type = MediaType.parse("application/json;charset=UTF-8");
                RequestBody requestBody = RequestBody.create(type, json.toString());
                Log.e("requestBody",json.toString());
                Request request = new Request.Builder()
                        .url(Constant.BASE_URL + "/LoginUserServlet")
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
                                Intent intent = new Intent(Load.this,MainActivity.class);
                                startActivity(intent);
                            } else{
                                Log.e("login","用户名或密输入错误");
                                Looper.prepare();
                                Toast.makeText(Load.this,"用户名或密码输入错误，请重新输入！",Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }
                        } else {
                            Log.e("false", "响应失败！");
                        }
                    }
                });
                Log.e("load","完成");

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //实现输入用户名时的一键清除功能
    private void initView() {
        input = findViewById(R.id.et_login_account);
        inputDel = findViewById(R.id.et_login_account_cancel);
    }

    //设置监听器
    private void initListener() {
        input.addTextChangedListener(textWatcher);
        inputDel.setOnClickListener(this);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            if (input.getEditableText().length() >= 1) {
                inputDel.setVisibility(View.VISIBLE);
            } else {
                inputDel.setVisibility(View.GONE);
            }
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (input.getEditableText().length() >= 1) {
                inputDel.setVisibility(View.VISIBLE);
            } else {
                inputDel.setVisibility(View.GONE);
            }
        }
    };

    //点击事件实现清空效果
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_login_account_cancel:
                input.setText("");
                break;
        }
    }



    //实现密码明文密文切换
    private void initEvents(){
        img_pwdshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOrHiddenPwdWithInputType();
            }
        });
    }

    //实现明文密文切换功能
    private void showOrHiddenPwdWithInputType(){
        if(!showPwd){
            showPwd = true;
            img_pwdshow.setImageResource(R.drawable.password_view);
            //显示密码
            et_login_pwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }else{
            showPwd = false;
            img_pwdshow.setImageResource(R.drawable.password_not_view);
            //隐藏密码
            et_login_pwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }
}
