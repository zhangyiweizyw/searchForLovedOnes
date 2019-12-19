package com.example.administrator.searchforlovedones;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;


public class IndivdualCenter extends Fragment {
    private Gson gson;
    private ImageView back;
    private ImageView head;

    private TextView username1;
    private TextView username;
    private TextView tel;
    private TextView email;
    private ImageView release;
    private ImageView cpwd;
    private User user=new User();
    private String type;//type为1 是修改用户名、2是修改手机、3是修改邮箱
    private View page;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        page = inflater.inflate(R.layout.fragment_page4,container,false);
        page.setBackgroundColor(Color.WHITE);
        gson = new Gson();
        back=page.findViewById(R.id.indiv_back);
        head=page.findViewById(R.id.indiv_head);
        username1=page.findViewById(R.id.indiv_username);
        username=page.findViewById(R.id.idv_name);
        tel=page.findViewById(R.id.idv_tel);
        email=page.findViewById(R.id.idv_email);
        release=page.findViewById(R.id.idv_release);
        cpwd=page.findViewById(R.id.idv_cpwd);
        user.setId(-1);
        getUser();
        Glide.with(this)
                .load(R.drawable.touxiang_128)
//                .bitmapTransform(new BlurTransformation(this, 25), new CenterCrop(this))
                .into(back);
        //设置圆形图像
        Glide.with(this)
                .load(R.drawable.touxiang_128)
//                .bitmapTransform(new CropCircleTransformation(this))
                .into(head);
        //修改用户名

            username.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final EditText et_name = new EditText(v.getContext());
                    new AlertDialog.Builder(v.getContext()).setTitle("请输入修改的用户名")
                            .setIcon(R.mipmap.user_48px)
                            .setView(et_name)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //按下确定键后的事件
                                    user.setUserName(et_name.getText().toString());
                                    type="1";
                                    getValues();
                                    username.setText(user.getUserName());
                                    username1.setText(user.getUserName());
                                }
                            }).setNegativeButton("取消",null).show();
                }
            });

            //修改手机
            tel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final EditText et_name = new EditText(v.getContext());
                    new AlertDialog.Builder(v.getContext()).setTitle("请输入修改的手机号")
                            .setIcon(R.mipmap.smartphone_72)
                            .setView(et_name)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //按下确定键后的事件
                                    user.setUserTel(et_name.getText().toString());
                                    type="2";
                                    getValues();
                                    tel.setText(user.getUserTel());
                                }
                            }).setNegativeButton("取消",null).show();
                }
            });
            //修改email
            email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final EditText et_name = new EditText(v.getContext());
                    new AlertDialog.Builder(v.getContext()).setTitle("请输入修改的邮箱")
                            .setIcon(R.mipmap.open_email_48px)
                            .setView(et_name)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //按下确定键后的事件
                                    user.setUserEmail(et_name.getText().toString());
                                    type="3";
                                    getValues();
                                    email.setText(user.getUserEmail());
                                }
                            }).setNegativeButton("取消",null).show();
                }
            });
            //我的发布
            release.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent_1=new Intent(getActivity(),Idv_Release.class);
                    intent_1.putExtra("user_id",user.getId());
                    startActivity(intent_1);
                }
            });
            //修改密码
            cpwd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent();
                    intent.putExtra("source","idvcenter");
                    intent.setClass(getActivity(),ForgetPwdActivity.class);
                    startActivity(intent);
                }
            });

       if(user.getId()==-1) {
            username1.setText("如已登录 可能网络延迟请稍等...");
        }
        return page;
    }


    private  void getUser() {
        PageTextTask2 pageTextTask2 = new PageTextTask2();
        pageTextTask2.execute("http://"+Constant.IP+":8080/searchfor_prj/IdvInitialize");
    }
    private class PageTextTask2 extends AsyncTask {

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            username1.setText(user.getUserName());
            username.setText(user.getUserName());
            tel.setText(user.getUserTel());
            email.setText(user.getUserEmail());
        }

        @Override
        protected Object doInBackground(Object[] objects) {

            try {
                URL url = new URL((String) objects[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                InputStreamReader is = new InputStreamReader(connection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(is);
                StringBuffer str = new StringBuffer();
                String line = null;
                while (null!=(line = bufferedReader.readLine()))  {
                    str.append(line);
                }
                is.close();
                String jsonStr =new String(str.toString().getBytes("utf-8"),"UTF-8");

                user= gson.fromJson(jsonStr,User.class);



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
    }

    private void getValues() {
        PageTextTask pageTextTask = new PageTextTask();
        pageTextTask.execute("http://"+Constant.IP+":8080/searchfor_prj/IdvCenterServlet");
    }
    private class PageTextTask extends AsyncTask {

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Object doInBackground(Object[] objects) {

            try {
                URL url = new URL((String) objects[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                OutputStream outputStream = connection.getOutputStream();
                Gson gson = new Gson();
                String jsonStr = gson.toJson(user)+type;
                Log.e("user",jsonStr);
                outputStream.write(jsonStr.getBytes());
                InputStream is = connection.getInputStream();
                outputStream.close();
                is.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
