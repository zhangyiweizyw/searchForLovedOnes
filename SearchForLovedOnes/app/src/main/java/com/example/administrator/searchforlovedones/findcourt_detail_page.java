package com.example.administrator.searchforlovedones;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class findcourt_detail_page extends Activity {
    private Gson gson = new Gson();
    private OkHttpClient okHttpClient = new OkHttpClient();
    private ArrayList<Basic_information> mData = new ArrayList<Basic_information>();
    private static String ItemId;
    private TextView detail_id;//最重要 ，通过id到数据库中获取信息
    private TextView detail_sex;
    private TextView detail_name;
    private ImageView detail_photo;
    private Context mContext;
    private static final int REFRESH_FINISH = 1;
    // Handler
    private Handler mainHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH_FINISH: {
                    //更新
                    detail_id.setText(ItemId);
                    Glide.with(getApplicationContext())
                            .load(Constant.BASE_SERVER + mData.get(0).getPhoto())
                            .placeholder(R.drawable.loading02)
                            .error(R.drawable.error)
                            .fallback(R.drawable.defaultimg)
                            .into(detail_photo);
                    detail_name.setText(mData.get(0).getName());
                    detail_sex.setText(mData.get(0).getSex());
                    break;
                }


            }

        }
    };

    private void Handlerfresh() {

        Glide.with(mContext)
                .load(Constant.BASE_SERVER + mData.get(0).getPhoto())
                .placeholder(R.drawable.loading02)
                .error(R.drawable.error)
                .fallback(R.drawable.defaultimg)
                .into(detail_photo);
        detail_name.setText(mData.get(0).getName());
        detail_sex.setText(mData.get(0).getSex());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findcourt_detail_page);
        findView();
        Intent intent = getIntent();
        ItemId = intent.getStringExtra("id");
        //根据id到数据库中查询数据

        Log.e("详情页接受到了id信息是", ItemId);
        try {
            threadResponseById(ItemId);
            Log.e("此时mData的长度是",mData.size()+"");
            runer();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void runer() {

        new Thread() {
            @Override
            public void run() {
                super.run();
                //向主线程发送消息 更新视图
                Message msg = new Message();
                msg.what = REFRESH_FINISH;
                mainHandler.sendMessage(msg);
            }
        }.start();
    }

    /**
     * 实现单选框查询和输入id的查询
     *
     * @param code
     */
    private void threadResponseById(final String code) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    JSONObject json = new JSONObject();
                    json.put("client", code);
                    Log.e("已经封装好了的数据", json.toString());
                    MediaType type = MediaType.parse("application/json;charset=UTF-8");
                    RequestBody requestBody = RequestBody.create(type, json.toString());
                    Log.e("requestBody", json.toString());
                    Request request = new Request.Builder()
                            .url(Constant.TEST_URL)
                            .post(requestBody)
                            .build();
                    Log.e("request", json.toString());
                    Response response = okHttpClient.newCall(request).execute();//有问题？
//                    String returnok = response.body().string();
                    if (response.isSuccessful()) {//response.body().string()只能用一次！！！！！！！！
                        ArrayList<Basic_information> newbasic = gson.fromJson(response.body().string(),
                                new TypeToken<List<Basic_information>>() {
                                }.getType());
                        //在photo前加上指定字符串
//                        mData.clear();
                        mData.addAll(0, newbasic);//全部添加
                        Log.e("有id为 ", mData.get(0).getId());
                        Log.e("有name为 ", mData.get(0).getName());
                        Log.e("有sex为 ", mData.get(0).getSex());
                        Log.e("有photo为 ", mData.get(0).getPhoto());

                        //打印检查
//                        for (Basic_information u : newbasic) {
//                            String id = (String) u.getId();
//                            String name = u.getName();
//                            String sex = u.getSex();
//                            String photo = u.getPhoto();
//                            Log.e("有id为 ", id);
//                            Log.e("有name为 ", name);
//                            Log.e("有sex为 ", sex);
//                            Log.e("有photo为 ", photo);
//
//
//                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void findView() {
        detail_id = findViewById(R.id.tv_findcourt_detail_id);
        detail_photo = findViewById(R.id.iv_findcourt_detail_photo);
        detail_name = findViewById(R.id.tv_findcourt_detail_name);
        detail_sex = findViewById(R.id.tv_findcourt_detail_sex);
    }

}
