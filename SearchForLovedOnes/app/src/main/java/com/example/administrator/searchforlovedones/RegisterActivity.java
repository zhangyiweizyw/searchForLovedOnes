package com.example.administrator.searchforlovedones;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loper7.layout.TitleBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends Activity {

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
    EventHandler eventHandler;
    private boolean coreflag = true;
    private String tel;//获取用户传入电话
    private Boolean isTel = true;

    //自定义电话号和验证码的字符串
    private String phone_number;
    private String cord_number;
    private TitleBar bar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.register_activity);

        okHttpClient = new OkHttpClient();//采取的属性都是默认操作

        //获取对应的View
        initViews();
        sms_verification();

        bar.setBackImageResource(R.drawable.back);
        bar.setUseRipple(true);

        //为单选按钮添加事件监听
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);//通过序号直接获取对应单选按钮
                Log.e("选择的是：", radioButton.getText() + "");//当某一项类型被点击时
            }
        });

        //发送验证码点击事件
        btn_reg_sendnum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        if(judgeTel()){//先判断用户电话是否存在于数据库中
                            if (judgePhone()){//去掉左右空格获取字符串，是正确的的手机号

                                Log.e("发送验证码","000");
                                SMSSDK.getVerificationCode("86",phone_number);//获取验证码
                                Log.e("发送验证码","111");
                                //et_checknum.requestFocus();//判断是否获得焦点
                            }
                        }
                    }
                }.start();

                TimeTask timeTask = new TimeTask();
                timeTask.execute();


            }
        });


    }

    private class TimeTask extends AsyncTask {


        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            TimeCount timeCount = new TimeCount(60000,1000);
            timeCount.start();

        }
        @Override
        protected Object doInBackground(Object[] objects) {
            return true;
        }
    }


    protected void onDestroy(){//注意及时销毁短信回调，避免泄露内存
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
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
        bar = findViewById(R.id.bar);
        btn_reg_regist = findViewById(R.id.btn_reg_regist);
    }

    public void buttonClicked(View view){
        switch (view.getId()) {
//            case R.id.btn_reg_sendnum://获取验证码的ID
//                if(judgeTel()){//先判断用户电话是否存在于数据库中
//                    if (judgePhone()){//去掉左右空格获取字符串，是正确的的手机号
//                        Log.e("发送验证码","000");
//                        SMSSDK.getVerificationCode("86",phone_number);//获取验证码
//                        Log.e("发送验证码","111");
//                        et_checknum.requestFocus();//判断是否获得焦点
//                    }
//                }
//
//                break;
            case R.id.btn_reg_regist:
                if(judgeUserInfo() && judgeType()){//验证用户信息是否为空
                    if (judgeCord()){//判断验证码是否正确
                        SMSSDK.submitVerificationCode("86",phone_number,cord_number);//提交手机号和验证码
                        okHttpMethod();
                   }
                }

                coreflag = false;
                break;
        }
    }

    //获取用户输入电话是否已经注册
    public boolean judgeTel(){
        tel = et_reg_tel.getText().toString();

        try {
            //向数据库中传用户电话
            JSONObject json = new JSONObject();
            json.put("judgeTel", tel);

            //采用输入流形式
            String path = Constant.BASE_URL + "/RegisterJudgeTelServlet";
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            OutputStream os = connection.getOutputStream();
            os.write(json.toString().getBytes());

            //采用输入流接收服务器端传来的数据库信息
            InputStream is = connection.getInputStream();
            byte[] buffer = new byte[255];
            int len = is.read(buffer);
            String content = new String(buffer, 0, len);
            os.close();
            is.close();

            JSONObject response = new JSONObject(content);
            isTel = response.getBoolean("isTel");
            Log.e("reg",isTel+"");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (isTel) {//手机号不存在
            return true;
        }else{//手机号已经存在
            Looper.prepare();
            Toast.makeText(RegisterActivity.this,"该手机号已经注册！",Toast.LENGTH_LONG).show();
            Looper.loop();
            return false;
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

    //判断用户填入信息是否为空,为空时提醒用户输入
    public boolean judgeUserInfo(){
        String name = et_reg_username.getText().toString();
        String pwd = et_reg_pwd.getText().toString();
        String email = et_reg_email.getText().toString();
        String tel = et_reg_tel.getText().toString();

        if (name.equals("")){
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_LONG).show();
            return false;
        }else if (pwd.equals("")){
            Toast.makeText(this,"用户密码不能为空",Toast.LENGTH_LONG).show();
            return false;
        }else if (tel.equals("")){
            Toast.makeText(this,"用户电话不能为空",Toast.LENGTH_LONG).show();
            return false;
        }else if(email.equals("")){
            Toast.makeText(this,"邮箱不能为空",Toast.LENGTH_LONG).show();
            return false;
        }else{
            return true;
        }
    }

    //判断用户类型是否被选择
    public boolean judgeType(){
        for (int i = 0;i<radioGroup.getChildCount();i++){
            RadioButton radioButton1 = (RadioButton)radioGroup.getChildAt(0);//得到被选择的第一个按钮
            RadioButton radioButton2 = (RadioButton)radioGroup.getChildAt(1);//得到遍历的第二个按钮
            if (radioButton1.isChecked()){//当按钮1被选择时
                Log.e("reg","btn1");
                return true;
            }else if(radioButton2.isChecked()){//当按钮2被选择时
                return true;
            } else if(!radioButton1.isChecked() && !radioButton2.isChecked()){//当两个按钮都未被选择时
                Toast.makeText(RegisterActivity.this,"请选择用户类型！",Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return false;
    }

    //向服务器端发送用户注册信息
    public void okHttpMethod(){
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
            radioButtonText = radioButton.getText().toString();//获取到对应的单选按钮文本字符串
            //选择的某一项单选按钮如果被选择，就获取单选按钮对应的文本信息
            //同时将其他信息一起封装发送给客户端
            if (radioButton.isChecked()) {
                String userName = et_reg_username.getText().toString();
                String userPwd = et_reg_pwd.getText().toString();
                String userEmail = et_reg_email.getText().toString();
                String userTel = et_reg_tel.getText().toString();

                try {
                    if (!userName.equals("") && !userPwd.equals("") && !radioButtonText.equals("") && !userTel.equals("")) {

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
                                if (response.body().string().equals("{\"isAdd\":\"1\"}")) {
                                    //获取验证码后要提交验证码以判断是否正确，并登录成功
                                    finish();
                                } else {
                                    Looper.prepare();
                                    Toast.makeText(RegisterActivity.this, "未注册成功，请重新注册！", Toast.LENGTH_SHORT).show();
                                    Looper.loop();
                                }
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
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
        eventHandler = new EventHandler(){
          public void afterEvent(int event,int result,Object data){
              Message msg = new Message();
              msg.arg1=event;
              msg.arg2=result;
              msg.obj=data;
              handler.sendMessage(msg);
          }
        };
        SMSSDK.registerEventHandler(eventHandler);//注册短信回调（注意销毁，避免泄露内存）
    }

    //使用Handler来分发Message对象到主线程中，处理事件
    Handler handler = new Handler(){
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
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
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
    };

    /*验证码倒计时*/
    private class TimeCount extends CountDownTimer {
        /**
         * @param millisInFuture  总时间长度（毫秒）
         * @param countDownInterval 时间间隔（毫秒），每经过一次时间间隔都会调用onTick方法
         */
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {   //倒计时状态
            btn_reg_sendnum.setClickable(false);     //设置button此时不可点击
            btn_reg_sendnum.setBackground(getResources().getDrawable(R.drawable.rounded_edittext));//修改button的背景
            btn_reg_sendnum.setTextColor(getResources().getColor(R.color.black));//修改button的textColor
            btn_reg_sendnum.setText(millisUntilFinished / 1000 +"s后发送");//显示button的倒计时文字
        }

        @Override
        public void onFinish() {      //倒计时结束状态
            btn_reg_sendnum.setBackground(getResources().getDrawable(R.drawable.rounded_edittext));
            btn_reg_sendnum.setTextColor(getResources().getColor(R.color.black));
            btn_reg_sendnum.setClickable(true);   //重新设置button为可点击
            btn_reg_sendnum.setText("重新获取");   //修改button的文字
        }
    }

}