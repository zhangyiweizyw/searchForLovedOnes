package com.example.administrator.searchforlovedones;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;

/*import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;*/
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {

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
    private String radioButtonText;
    private OkHttpClient okHttpClient;
/*    EventHandler eventHandler;*/
    private boolean coreflag = true;

    //自定义电话号和验证码的字符串
    private String phone_number;
    private String cord_number;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        okHttpClient = new OkHttpClient();//采取的属性都是默认操作

        //获取对应的View
        initViews();
        sms_verification();

        //为单选按钮添加事件监听
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);//通过序号直接获取对应单选按钮
                Log.e("选择的是：", radioButton.getText() + "");//当某一项类型被点击时
            }
        });
    }

    protected void onDestroy(){//注意及时销毁短信回调，避免泄露内存
        super.onDestroy();
      /*  SMSSDK.unregisterAllEventHandler();*/
    }

    protected void onResume(){
        super.onResume();
    }

    public void initViews(){
        //获取对应布局中元素ID
        et_reg_username =(EditText) findViewById(R.id.et_reg_username);
        et_reg_pwd = findViewById(R.id.et_reg_pwd);
        et_reg_email = findViewById(R.id.et_reg_email);
        et_reg_tel = findViewById(R.id.et_reg_tel);//获得电话编辑框
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        et_checknum = findViewById(R.id.et_checknum);//获取验证码编辑框
        btn_reg_sendnum = (Button) findViewById(R.id.btn_reg_sendnum);
    }

    public void buttonClicked(View view){
        switch (view.getId()) {
            case R.id.btn_reg_sendnum://获取验证码的ID
                if (judgePhone()){//去掉左右空格获取字符串，是正确的的手机号
                    Log.e("发送验证码","000");
/*
                    SMSSDK.getVerificationCode("86",phone_number);//获取验证码
*/
                    Log.e("发送验证码","111");
                    et_checknum.requestFocus();//判断是否获得焦点
                }
                break;
            case R.id.btn_reg_regist:
                judgeUserInfo();//验证用户信息是否为空
                okHttpMethod();//若用户信息不为空，则向服务器端传用户信息

                //获取验证码后要提交验证码以判断是否正确，并登录成功
//                if (judgeCord()){//判断验证码是否正确
//                    SMSSDK.submitVerificationCode("86",phone_number,cord_number);//提交手机号和验证码
//                    okHttpMethod();
//
//                }
//                coreflag = false;
                break;
        }
    }

    //获取并封装用户信息到User对象中
    public User getUserInformation(String radioButtonText){

        //获取用户的各项信息，封装到User对象中,再将user对象序列化
        String userName = et_reg_username.getText().toString();
        String userPwd = et_reg_pwd.getText().toString();
        String userEmail = et_reg_email.getText().toString();
        String userTel = et_reg_tel.getText().toString();

        User user = new User(userName,userPwd,radioButtonText,userEmail,userTel);
        return user;
    }

    //向服务器端发送用户注册信息
    public void okHttpMethod(){
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
            //选择的某一项单选按钮如果被选择，就获取单选按钮对应的文本信息
            //同时将其他信息一起封装发送给客户端
            if (radioButton.isChecked()) {
                radioButtonText = radioButton.getText().toString();//获取到对应的单选按钮文本字符串
                Log.e("某一项类型已经被选择", radioButtonText);

                String userName = et_reg_username.getText().toString();
                String userPwd = et_reg_pwd.getText().toString();
                String userEmail = et_reg_email.getText().toString();
                String userTel = et_reg_tel.getText().toString();

                try {
                    if (!userName.equals("") && !userPwd.equals("") && !radioButtonText.equals("") && !userTel.equals("") && !userEmail.equals("")) {

                        //用户选择完单选框后，向获取信息方法中传入用户身份类型，进行用户封装
                        User user = getUserInformation(radioButtonText);
                        Log.e("已经向封装方法中传入用户类型", user + "");
                        //创建Gson对象
                        Gson gson = new Gson();

                        //将User对象转换为json对象
                        String userJson = gson.toJson(user);
                        Log.e("已经将User类型转换成json字符串", userJson);

                        //创建JSONObject对象，向服务器端发送json数据
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("userJson", userJson);//传入封装的json对象
                        Log.e("要传递的json对象", jsonObject + "");

                        //使用okHttp方式传送用户信息
                        MediaType type = MediaType.parse("application/json;charset=UTF-8");
                        RequestBody requestBody = RequestBody.create(type, jsonObject.toString());
                        Request request = new Request.Builder()
                                .url(Constant.BASE_URL + "/RegisterServlet")
                                .post(requestBody)
                                .build();
                        Log.e("login", "已经注册");
                        Call call = okHttpClient.newCall(request);
                        call.enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                e.printStackTrace();
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                Log.e("user", "已成功注册！");
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }else{
                Toast.makeText(this,"请选择用户类型",Toast.LENGTH_LONG).show();
            }
        }
    }

    //判断用户填入信息是否为空,为空时提醒用户输入
    public void judgeUserInfo(){
        String name = et_reg_username.getText().toString();
        String pwd = et_reg_pwd.getText().toString();
        String email = et_reg_email.getText().toString();
        String tel = et_reg_tel.getText().toString();

        if (name.equals("")){
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_LONG).show();
        }else if (pwd.equals("")){
            Toast.makeText(this,"用户密码不能为空",Toast.LENGTH_LONG).show();
        }else if (tel.equals("")){
            Toast.makeText(this,"用户电话不能为空",Toast.LENGTH_LONG).show();
        }else if (name.equals("") && pwd.equals("")){
            Toast.makeText(this,"用户名和密码不能为空",Toast.LENGTH_LONG).show();
        }else if (name.equals("") && tel.equals("")){
            Toast.makeText(this,"用户名和电话不能为空",Toast.LENGTH_LONG).show();
        }else if (pwd.equals("") && tel.equals("")){
            Toast.makeText(this,"用户密码和电话不能为空",Toast.LENGTH_LONG).show();
        }else if ( name.equals("") &&  pwd.equals("") && tel.equals("")){
            Toast.makeText(this,"用户名和密码和电话都不能为空",Toast.LENGTH_LONG).show();
        }
    }


    //判断电话号码是否正确
    public boolean judgePhone(){
        //不正确的情况
        if (TextUtils.isEmpty(et_reg_tel.getText().toString().trim())){//若手机号为空时
            Toast.makeText(RegisterActivity.this,"请输入您的电话号码",Toast.LENGTH_SHORT).show();
            et_reg_tel.requestFocus();
            return false;
        }else if (et_reg_tel.getText().toString().trim().length()!=11){//若手机号不是11位时
            Toast.makeText(RegisterActivity.this,"您的电话号码位数不正确",Toast.LENGTH_SHORT).show();
            et_reg_tel.requestFocus();
            return false;
        }
        //正确情况
        else{
            phone_number = et_reg_tel.getText().toString().trim();
            String num="[1][3578]\\d{9}";
            if (phone_number.matches(num)){
                return true;
            }else{
                Toast.makeText(RegisterActivity.this,"请输入正确的手机号码",Toast.LENGTH_SHORT).show();
                return false;
            }
        }
    }

    //判断验证码是否正确
    public  boolean judgeCord(){
        judgePhone();//先执行：验证手机号是否正确
        if(TextUtils.isEmpty(et_checknum.getText().toString().trim())){//当验证码为空时
            Toast.makeText(RegisterActivity.this, "请输入您的验证码", Toast.LENGTH_SHORT).show();
            et_checknum.requestFocus();//聚集焦点
            return false;
        }else if (et_checknum.getText().toString().trim().length()!=4){//当验证码不是4位数时
            Toast.makeText(RegisterActivity.this, "您的验证码位数不正确", Toast.LENGTH_SHORT).show();
            et_checknum.requestFocus();
            return false;
        }else{
            cord_number = et_checknum.getText().toString().trim();//验证成功
            return true;
        }
    }

    public void sms_verification(){
       /* eventHandler = new EventHandler(){
          public void afterEvent(int event,int result,Object data){
              Message msg = new Message();
              msg.arg1=event;
              msg.arg2=result;
              msg.obj=data;
              handler.sendMessage(msg);
          }
        };
        SMSSDK.registerEventHandler(eventHandler);//注册短信回调（注意销毁，避免泄露内存）*/
    }

    //使用Handler来分发Message对象到主线程中，处理事件
    /*Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){//获取验证码成功
                if (result == SMSSDK.RESULT_COMPLETE){
                    //回调完成
                    boolean smart = (Boolean)data;
                    if (smart){
                        Toast.makeText(getApplicationContext(),"该手机已经注册过，请重新输入",Toast.LENGTH_LONG).show();
                        et_reg_tel.requestFocus();//焦点
                        return;
                    }
                }
            }


            //回调完成
            if (result == SMSSDK.RESULT_COMPLETE){
                if (event ==SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE){//提交验证码成功
                    Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }else{//其他出错情况
                if(coreflag){
                    btn_reg_sendnum.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(),"验证码获取失败！请重新获取",Toast.LENGTH_LONG).show();
                    et_reg_tel.requestFocus();
                }else{
                    Toast.makeText(getApplicationContext(),"验证码输入错误",Toast.LENGTH_LONG).show();
                }
            }
        }
    };*/
}