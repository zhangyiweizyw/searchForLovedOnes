package com.example.administrator.searchforlovedones;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;

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

public class FindCourt extends Fragment {
    private static int index;
    private OkHttpClient okHttpClient=new OkHttpClient();
    private ArrayList<Basic_information> mData=new ArrayList<Basic_information>();
    private ImageView filter;
    private View firstpage;
    private FindCourtAdapter findCourtAdapter;
    private GridView gridView;
    private DrawerLayout drawer_layout;
    private Button search;
    private Gson gson=new Gson();
    private RadioButton search_home;
    private RadioButton search_person;
    private RadioButton search_vagrancy;
    private RadioButton other_search;
    private static final int REFRESH_FINISH = 1;
    //Handler
    private Handler mainHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH_FINISH: {
                    //更新
                    findCourtAdapter.notifyDataSetChanged();
                    break;
                }


            }

        }
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (firstpage == null) {


            firstpage = inflater.inflate(R.layout.fragment_page1, container, false);
            firstpage.setBackgroundColor(Color.WHITE);
            initData();
            findView();



        }
        ViewGroup parent = (ViewGroup) firstpage.getParent();
        if (parent != null) {
            parent.removeView(firstpage);
        }

        return firstpage;


    }

    private void findView() {
        //获取单选框id
        search_home =  firstpage.findViewById(R.id.rbtn_search_home);
        search_person = firstpage. findViewById(R.id.rbtn_search_person);
        search_vagrancy =  firstpage.findViewById(R.id.rbtn_search_vagrancy);
        other_search =  firstpage.findViewById(R.id.rbtn_other_search);
        //抽屉方法
        filter = firstpage.findViewById(R.id.img_filter);
        drawer_layout = firstpage.findViewById(R.id.drawer_layout);
        search = firstpage.findViewById(R.id.btn_search);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer_layout.openDrawer(Gravity.RIGHT);
//                drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN,Gravity.END);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer_layout.closeDrawer(Gravity.RIGHT);
                //判断是否有选中的单选框
                if (search_home.isChecked() || search_vagrancy.isChecked() || search_person.isChecked() || other_search.isChecked()) {
                    //检查单选框情况
                    //以头号码为区分标准，即凡是以1为开头的id即为寻家。。依次类推
                    try {
                        threadResponse();
                        freshHandler();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //gridview布局
        gridView=firstpage.findViewById(R.id.grid_findcout_show_photos);
        findCourtAdapter=new FindCourtAdapter<Basic_information>(mData,R.layout.item_findcourt) {
            @Override
            public void bindView(ViewHolder holder, Basic_information obj) {
                holder.setImageUseGlide(R.id.iv_findcourt_photo,obj.getPhoto());
                holder.setText(R.id.tv_findcourt_name,obj.getName());
                holder.setText(R.id.tv_findcourt_sex,obj.getSex());
                holder.setText(R.id.tv_findcourt_id,obj.getId());
            }
        };
        gridView.setAdapter(findCourtAdapter);
    }

    private void initData() {
        mData.add(new Basic_information("100081","http://10.7.88.82:8080/searchfor_prj/images/a51.png","图标1","未知"));
        mData.add(new Basic_information("100082","http://10.7.88.82:8080/searchfor_prj/images/a5.jpg","图标2","未知"));
        mData.add(new Basic_information("100083","http://10.7.88.82:8080/searchfor_prj/images/a2.jpg","图标3","未知"));
        mData.add(new Basic_information("100084","http://10.7.88.82:8080/searchfor_prj/images/a1.jpg","图标4","未知"));
    }
    private void freshHandler(){
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(1500);
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
    //自定义线程执行response操作
    private void threadResponse() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    if (search_home.isChecked())
                        index = 1;
                    else if (search_person.isChecked())
                        index = 2;
                    else if (search_vagrancy.isChecked())
                        index = 3;
                    else if (other_search.isChecked())
                        index = 4;
                    JSONObject json = new JSONObject();
                    json.put("type", index);
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
//                        String mstring = response.body().string();
//                        Log.e("获取到的Gson序列化字符串是", response.body().string());
                        ArrayList<Basic_information> newbasic = gson.fromJson( response.body().string(),
                                new TypeToken<List<Basic_information>>() {
                                }.getType());
                        //在photo前加上指定字符串
                        mData.addAll(0,newbasic);
                        //要做到不重复添加

                        //打印检查
                        for(Basic_information u:newbasic){
                            String id=(String)u.getId();
                            String name=u.getName();
                            String sex=u.getSex();
                            String photo=u.getPhoto();
                            Log.e("有id为 ",id);
                            Log.e("有name为 ",name);
                            Log.e("有sex为 ",sex);
                            Log.e("有photo为 ",photo);

                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


}
