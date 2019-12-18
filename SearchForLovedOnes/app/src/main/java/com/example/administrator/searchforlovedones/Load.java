package com.example.administrator.searchforlovedones;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loper7.layout.TitleBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
    private Button btn_login;

    //实例化一键清除内容
    private EditText input;
    private ImageView inputDel;

    //密码明文密文切换功能
    private boolean showPwd = false;//默认不显示密码
    private TitleBar bar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        //获取对应布局中各个元素ID
        et_login_account = findViewById(R.id.et_login_account);
        et_login_pwd = findViewById(R.id.et_login_pwd);
        tv_user_regist = findViewById(R.id.tv_user_regist);
        tv_forget_pwd = findViewById(R.id.tv_forget_pwd);
        img_pwdshow = (ImageView) findViewById(R.id.img_pwdshow);
        btn_login = findViewById(R.id.btn_login);
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

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        name = et_login_account.getText().toString();
                        password = et_login_pwd.getText().toString();
                        Log.e("load",name+password);

                        try {
                            JSONObject json = new JSONObject();
                            json.put("name",name);
                            json.put("password",password);

                            String path = Constant.BASE_URL+"/LoginUserServlet";
                            URL url = new URL(path);
                            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                            connection.setRequestMethod("POST");
                            OutputStream os = connection.getOutputStream();
                            os.write(json.toString().getBytes());

                            InputStream is = connection.getInputStream();
                            byte[] buffer = new byte[255];
                            int len = is.read(buffer);
                            String content = new String(buffer,0,len);
                            os.close();
                            is.close();

                            JSONObject response = new JSONObject(content);
                            Boolean isSuccess = response.getBoolean("isSuccess");
                            if(isSuccess){
                                Intent intent = new Intent(Load.this,MainActivity.class);
                                startActivity(intent);

                            }else{
                                Looper.prepare();
                                Toast.makeText(Load.this,"用户名或密码输入错误，请重新输入！",Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

        //实现一键清空
        initView();
        initListener();

        //实现明文密文切换
        initEvents();
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