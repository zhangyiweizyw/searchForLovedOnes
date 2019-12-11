package com.example.administrator.searchforlovedones;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

public class ForgetPwdActivity extends Activity {

    private EditText et_forget_tel;
    private EditText et_forget_checknum;
    private Button btn_forget_sendnum;
    private Button btn_forget_next;
    private String tel;
    private TitleBar bar;
    private OkHttpClient okHttpClient;
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

    }

    public void buttonClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_forget_next:
                tel = et_forget_tel.getText().toString();
                if (!(tel.equals(""))){
                    //先去数据库查询该电话是否存在于数据库中，若存在，再发送验证码
                    try{
                        JSONObject json = new JSONObject();
                        json.put("searchTel",tel);

                        //使用okHttp方式传送用户电话
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
                                        //先默认用户验证码发送成功
                                        Intent intent = new Intent(ForgetPwdActivity.this,ForgetPwd1Activity.class);
                                        intent.putExtra("tel",tel);
                                        startActivity(intent);
                                        Log.e("forget",tel);
                                        Log.e("forget","tel");
                                    } else {
                                        Log.e("forget1", "用户电话不存在，请先注册！");
                                    }
                                } else {
                                    Log.e("false", "用户电话不存在，请先注册！");
                                }
                            }
                        });

                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }else if(tel.equals("")){
                    Toast.makeText(this,"用户电话不能为空！",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
