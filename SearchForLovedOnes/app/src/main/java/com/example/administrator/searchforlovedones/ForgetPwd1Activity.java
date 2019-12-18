package com.example.administrator.searchforlovedones;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class ForgetPwd1Activity extends Activity {

    private EditText et_newpwd;
    private EditText et_newpwd_again;
    private Button btn_newpwd_sure;
    private TitleBar bar;
    private String newPwd;
    private String newPwdAgain;
    private OkHttpClient okHttpClient;
    private String tel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_pwd1_activity);
        okHttpClient = new OkHttpClient();

        //获取对应布局中各个元素Id
        et_newpwd = (EditText) findViewById(R.id.et_newpwd);
        et_newpwd_again = (EditText) findViewById(R.id.et_newpwd_again);
        btn_newpwd_sure = findViewById(R.id.btn_newpwd_sure);
        bar = findViewById(R.id.bar);
        bar.setBackImageResource(R.drawable.back);
        bar.setUseRipple(true);

        //获取上一个页面传递的电话号码
        Intent intent = this.getIntent();
        tel = intent.getStringExtra("tel");
        Log.e("forget1",tel+"");
    }

    public void initData(){
        newPwd= et_newpwd.getText().toString();
        newPwdAgain = et_newpwd_again.getText().toString();
    }

    //判断是否两次密码是否相等
    public void buttonClicked(View view){
        switch (view.getId()){
            case R.id.btn_newpwd_sure:
                initData();//获取输入内容
                if(newPwd.equals(newPwdAgain)){//若前后两次密码输入相等，则向服务器端传输
                    okHttpMethod(tel);//向服务器发送修改后的密码
                }else{//前后两次密码输入不相等时
                    Toast.makeText(this,"密码不一致，请重新输入密码",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    public void okHttpMethod(String tel){
        try{
            newPwdAgain = et_newpwd_again.getText().toString();

            JSONObject json = new JSONObject();
            json.put("tel",tel);
            Log.e("tel",tel);
            json.put("newPwd",newPwdAgain);
            Log.e("已经封装好数据",json.toString());

            //使用okHttp方式传送用户登录信息
            MediaType type = MediaType.parse("application/json;charset=UTF-8");
            RequestBody requestBody = RequestBody.create(type, json.toString());
            Log.e("requestBody",json.toString());
            Request request = new Request.Builder()
                    .url(Constant.BASE_URL + "/PwdChangedServlet")
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
                        if(response.body().string().equals("{\"isUpdate\":\"1\"}")){//注意，response.body().string()只会调用一次
                            Intent intent = new Intent(ForgetPwd1Activity.this,Load.class);
                            startActivity(intent);
                            Looper.prepare();
                            Toast.makeText(ForgetPwd1Activity.this,"修改成功",Toast.LENGTH_LONG).show();
                            Looper.loop();
                        } else{
                            Looper.prepare();
                            Toast.makeText(ForgetPwd1Activity.this,"用户密码未修改成功！",Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }
                    } else {
                        Log.e("false", "响应失败！");
                    }
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
