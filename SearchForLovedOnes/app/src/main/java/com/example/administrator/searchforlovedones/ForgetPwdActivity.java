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
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loper7.layout.TitleBar;

import org.json.JSONObject;

import java.io.IOException;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ForgetPwdActivity extends Activity {

    private EditText et_forget_tel;
    private EditText et_forget_checknum;
    private Button btn_forget_sendnum;
    private Button btn_forget_next;
    private String tel;
    private TitleBar bar;
    private OkHttpClient okHttpClient;
    //自定义电话号和验证码的字符串
    private String phone_number;
    private String cord_number;
    private boolean coreflag = true;
    EventHandler eventHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_pwd_activity);
        okHttpClient = new OkHttpClient();

        //获取布局中各个元素ID
        et_forget_tel = findViewById(R.id.et_forget_tel);
        et_forget_checknum = findViewById(R.id.et_forget_checknum);
        btn_forget_sendnum = findViewById(R.id.btn_forget_sendnum);
        btn_forget_next = findViewById(R.id.btn_forget_next);
        bar = findViewById(R.id.bar);
        bar.setBackImageResource(R.drawable.back);
        bar.setUseRipple(true);

        sms_verification();

    }

    public void buttonClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_forget_sendnum:
                //验证电话是否存在，若存在发送验证码
                sendNum();
                break;
            case R.id.btn_forget_next:
                //先验证电话是否存在
                if(judgePhone() && judgeCord()) {
                    //当电话存在，验证码通过后进行下一步操作
                    SMSSDK.submitVerificationCode("86",phone_number,cord_number);//提交手机号和验证码
                }
                coreflag = false;
                break;
        }
    }

    protected void onDestroy(){//注意及时销毁短信回调，避免泄露内存
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
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

    protected void onResume(){
        super.onResume();
    }

    public void sendNum(){
        tel = et_forget_tel.getText().toString();
        if (judgePhone()){
            //先去数据库查询该电话是否存在于数据库中，若存在，再发送验证码
            TimeTask timeTask = new TimeTask();
            timeTask.execute();
            try{
                JSONObject json = new JSONObject();
                json.put("searchTel",tel);

                //使用okHttp方式传送用户电话，验证电话是否存在数据库中
                MediaType type = MediaType.parse("application/json;charset=UTF-8");
                RequestBody requestBody = RequestBody.create(type, json.toString());
                Log.e("requestBody", json.toString());
                Request request = new Request.Builder()
                        .url(Constant.BASE_URL + "/PwdChangedTelServlet")
                        .post(requestBody)
                        .build();
                Log.e("request", json.toString());

                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            if (response.body().string().equals("{\"isExist\":\"1\"}")) {//注意，response.body().string()只会调用一次
                                Log.e("forget", "用户电话已存在，可以发送验证码！");
                                //用户电话已经存在，进行发送验证码操作
                                SMSSDK.getVerificationCode("86",phone_number);//获取验证码
                                Log.e("forget",tel);
                                Log.e("forget","tel");
                            } else {
                                Log.e("forget1", "用户电话不存在，请先注册！");
                                Looper.prepare();
                                Toast.makeText(ForgetPwdActivity.this,"用户电话不存在，请先注册！",Toast.LENGTH_LONG).show();
                                Looper.loop();
                            }
                        } else {
                            Looper.prepare();
                            Toast.makeText(ForgetPwdActivity.this,"用户电话不存在，请先注册！",Toast.LENGTH_LONG).show();
                            Looper.loop();
                        }
                    }
                });
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }


    //判断电话号码是否正确
    public boolean judgePhone(){
        //不正确的情况
        if (TextUtils.isEmpty(et_forget_tel.getText().toString().trim())){//若手机号为空时
            Toast.makeText(ForgetPwdActivity.this,"电话号码不能为空",Toast.LENGTH_LONG).show();
            et_forget_tel.requestFocus();
            return false;
        }else if (et_forget_tel.getText().toString().trim().length()!=11){//若手机号不是11位时
            Toast.makeText(ForgetPwdActivity.this,"您的电话号码位数不正确",Toast.LENGTH_LONG).show();
            et_forget_tel.requestFocus();
            return false;
        }
        //正确情况
        else{
            phone_number = et_forget_tel.getText().toString().trim();
            String num="[1][3578]\\d{9}";
            if (phone_number.matches(num)){
                return true;
            }else{
                Toast.makeText(ForgetPwdActivity.this,"请输入正确的手机号码",Toast.LENGTH_SHORT).show();
                return false;
            }
        }
    }

    //判断验证码是否正确
    public  boolean judgeCord(){
        judgePhone();//先执行：验证手机号是否正确
        if(TextUtils.isEmpty(et_forget_checknum.getText().toString().trim())){//当验证码为空时
            Toast.makeText(ForgetPwdActivity.this, "请输入您的验证码", Toast.LENGTH_SHORT).show();
            et_forget_checknum.requestFocus();//聚集焦点
            return false;
        }else if (et_forget_checknum.getText().toString().trim().length()!=4){//当验证码不是4位数时
            Toast.makeText(ForgetPwdActivity.this, "您的验证码位数不正确", Toast.LENGTH_SHORT).show();
            et_forget_checknum.requestFocus();
            return false;
        }else{
            cord_number = et_forget_checknum.getText().toString().trim();//验证成功
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
                        et_forget_tel.requestFocus();//焦点
                        return;
                    }
                }
            }


            //回调完成
            if (result == SMSSDK.RESULT_COMPLETE){
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE){//提交验证码成功
                    tel = et_forget_tel.getText().toString();
                    Intent intent = new Intent(ForgetPwdActivity.this,ForgetPwd1Activity.class);
                    intent.putExtra("tel",tel);
                    startActivity(intent);
                }
            }else{//其他出错情况
                if(coreflag){
                    btn_forget_sendnum.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(),"验证码获取失败！请重新获取",Toast.LENGTH_LONG).show();
                    et_forget_tel.requestFocus();
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
            btn_forget_sendnum.setClickable(false);     //设置button此时不可点击
            btn_forget_sendnum.setBackground(getResources().getDrawable(R.drawable.rounded_edittext));//修改button的背景
            btn_forget_sendnum.setTextColor(getResources().getColor(R.color.black));//修改button的textColor
            btn_forget_sendnum.setText(millisUntilFinished / 1000 +"s后发送");//显示button的倒计时文字
        }

        @Override
        public void onFinish() {      //倒计时结束状态
            btn_forget_sendnum.setBackground(getResources().getDrawable(R.drawable.rounded_edittext));
            btn_forget_sendnum.setTextColor(getResources().getColor(R.color.black));
            btn_forget_sendnum.setClickable(true);   //重新设置button为可点击
            btn_forget_sendnum.setText("重新获取");   //修改button的文字
        }
    }
}
