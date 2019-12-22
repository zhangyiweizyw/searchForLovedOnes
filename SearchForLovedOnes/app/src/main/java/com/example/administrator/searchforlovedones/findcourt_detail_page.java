package com.example.administrator.searchforlovedones;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.MenuItemHoverListener;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loper7.layout.TitleBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.onekeyshare.OnekeyShare;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class findcourt_detail_page extends Activity {
    private Gson gson = new Gson();
    private OkHttpClient okHttpClient = new OkHttpClient();
    private ArrayList<Basic_information> mDatas = new ArrayList<Basic_information>();
    private static String ItemId;
    private TextView detail_id;//最重要 ，通过id到数据库中获取信息
    private TextView detail_sex;
    private TextView detail_name;
    private ImageView detail_photo;
    private TextView detail_type;
    private TextView detail_tel;
    private TextView detail_describe;
    private Context mContext;
    private static final int REFRESH_FINISH = 1;
    private TitleBar bar;
    // Handler
    private Handler mainHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH_FINISH: {
                    //更新ui
                    if (mDatas != null && mDatas.size() > 0) {
                        //设置id
                        detail_id.setText(mDatas.get(0).getId());
                        //设置图片
                        Glide.with(getApplicationContext())
                                .load(Constant.BASE_SERVER + mDatas.get(0).getPhoto())
                                .placeholder(R.drawable.loading02)
                                .error(R.drawable.error)
                                .fallback(R.drawable.defaultimg)
                                .into(detail_photo);
                        //设置姓名
                        detail_name.setText(mDatas.get(0).getName());
                        //设置性别
                        detail_sex.setText(mDatas.get(0).getSex());
//                        if (mDatas.get(0).getSex().equals("0")) {
//                            detail_sex.setText("男");
//                        } else  {
//                            detail_sex.setText("女");
//                        }
                        //设置寻人类型
                        String middle=mDatas.get(0).getId()+"";
                        int first=Integer.parseInt(middle.substring(0,1));
                        if(1==first)
                        {
                            detail_type.setText("家寻亲人");
                            detail_tel.setText("18603545521");
                            detail_describe.setText(mDatas.get(0).getName()+"于2019年5月12号在河北省石家庄市裕华区丢失，请有看到他的好心帮帮忙，让我们家人团聚，感激不尽");
                        }
                        else if(2==first)
                        {
                            detail_type.setText("亲人寻家");
                            detail_tel.setText("15108650598");
                            detail_describe.setText(mDatas.get(0).getName()+"于2019年9月12号在海南省东方市丢失，请有看到他的好心帮帮忙，让我们家人团聚，感激不尽");
                        }
                        else if(3==first)
                        {
                            detail_type.setText("流浪救助");
                            detail_tel.setText("18389621812");
                            detail_describe.setText(mDatas.get(0).getName()+"于2017年12月30号在山西省发现，孤苦伶仃，求好心人帮帮忙，让他回归到正常的生活");
                        }
                        else if(4==first)
                        {
                            detail_type.setText("其他寻人");
                            detail_tel.setText("18689960556");
                            detail_describe.setText(mDatas.get(0).getName()+"是一个老赖，请发现他的人马上联系我，这个人私吞了我的血汗钱，搞的我现在没钱恰饭了");
                        }



                    }
                    break;
                }


            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_findcourt_detail_page);
        findView();
        Intent intent = getIntent();
        ItemId = intent.getStringExtra("id");
        //根据id到数据库中查询数据

        Log.e("详情页接受到了id信息是", ItemId);
        try {
//            threadResponseById(ItemId);
//            MyAsyncTasjk myAsyncTasjk=new MyAsyncTasjk();
//            myAsyncTasjk.execute(Constant.TEST_URL);
            threadResponseByIdInDetail(ItemId);
            runerfresh();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void runerfresh() {

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(1500);//停滞1.5秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //向主线程发送消息 更新视图
                Message msg = new Message();
                msg.what = REFRESH_FINISH;
                mainHandler.sendMessage(msg);
            }
        }.start();
    }

    /**
     * 实现id的查询
     *
     * @param code
     */
    private void threadResponseByIdInDetail(final String code) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    JSONObject json = new JSONObject();
                    json.put("client", code);
                    Log.e("详情页.查询数据库时已经封装好了的数据", json.toString());
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
                        mDatas.clear();
                        Log.e("mData的长度clear后是：", mDatas.size() + "");
                        mDatas.addAll(0, newbasic);//全部添加
                        Log.e("mData添加数据后的长度是：", mDatas.size() + "");
                        //在photo前加上指定字符串
//                        mData.clear();
//                        mData.add(newbasic.get(0));//全部添加
                        Log.e("详情页.有id为 ", mDatas.get(0).getId());
                        Log.e("详情页.有name为 ", mDatas.get(0).getName());
                        Log.e("详情页.有sex为 ", mDatas.get(0).getSex());
                        Log.e("详情页.有photo为 ", mDatas.get(0).getPhoto());


                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private class MyAsyncTasjk extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                URL url = new URL((String) objects[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                OutputStream outputStream = connection.getOutputStream();
                String json = gson.toJson(ItemId);
                outputStream.write(json.getBytes());

                InputStreamReader is = new InputStreamReader(connection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(is);
                StringBuffer str = new StringBuffer();
                String line = null;
                while (null != (line = bufferedReader.readLine())) {
                    str.append(line);
                }
                is.close();
                String jsonStr = new String(str.toString().getBytes("utf-8"), "UTF-8");
                Log.e("获取到的JSON格式的用户列表", jsonStr);
                mDatas = gson.fromJson(String.valueOf(jsonStr), new TypeToken<List<Basic_information>>() {
                }.getType());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            //更新
            if (mDatas != null && mDatas.size() > 0) {
                Log.e("postExecute", " yes");
                detail_id.setText(mDatas.get(mDatas.size() - 1).getId());
                Glide.with(getApplication())
                        .load(Constant.BASE_SERVER + mDatas.get(mDatas.size() - 1).getPhoto())
                        .placeholder(R.drawable.loading02)
                        .error(R.drawable.error)
                        .fallback(R.drawable.defaultimg)
                        .into(detail_photo);
                detail_name.setText(mDatas.get(mDatas.size() - 1).getName());
                detail_sex.setText(mDatas.get(mDatas.size() - 1).getSex());
            }

        }
    }


    private void findView() {
        detail_id = findViewById(R.id.tv_findcourt_detail_id);
        detail_photo = findViewById(R.id.iv_findcourt_detail_photo);
        detail_name = findViewById(R.id.tv_findcourt_detail_name);
        detail_sex = findViewById(R.id.tv_findcourt_detail_sex);
        detail_type=findViewById(R.id.tv_findcourt_detail_type);
        detail_describe=findViewById(R.id.tv_findcourt_detail_decribe);
        detail_tel=findViewById(R.id.tv_findcourt_detail_tel);
        bar = findViewById(R.id.bar);
        bar.setBackImageResource(R.drawable.back);
        bar.setUseRipple(true);
        bar.setMenuImageResource(R.drawable.sharetosomeone);
        bar.setOnMenuListener(new shareListeners());

    }
    public class shareListeners implements TitleBar.OnMenuListener{
        @Override
        public void onMenuClick() {
            showShare();
        }
    }
    public class shareListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.drawable.sharetosomeone:
                    showShare();
                    break;
            }
        }
    }

    //第三方平台分享
    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle(getString(R.string.ssdk_oks_multi_share));
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，确保SDcard下面存在此张图片
        oks.setImagePath("/sdcard/test.jpg");
        // url在微信、Facebook等平台中使用
        oks.setUrl("http://sharesdk.cn");
        // 启动分享GUI
        oks.show(this);
    }

}
