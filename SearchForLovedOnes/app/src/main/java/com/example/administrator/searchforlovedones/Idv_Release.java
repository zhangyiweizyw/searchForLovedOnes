package com.example.administrator.searchforlovedones;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.searchforlovedones.PullScrollView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loper7.layout.TitleBar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;


public class Idv_Release extends Activity implements PullScrollView.OnTurnListener{
    private ListView listView;
    private ListView listView2;
    private ListView listView3;
    private ListView listView4;
    private Gson gson;
    private List<SearchPeopleBean>  spb;
    private List<SearchFamilyBean>  sfb;
    private List<Vagrant> vgt;
    private List<OtherSearchBean> osb;
    private IdvAdapter adapter;
    private IdvAdapter2 adapter2;
    private IdvAdapter3 adapter3;
    private IdvAdapter4 adapter4;
    private int user_id=-1;
    private PullScrollView mScrollView;
    private ImageView mHeadImg;
    private TextView user_name;
    private  TextView user_type;
    private TextView user_email;
    private TitleBar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.idv_release);
        Intent intent = getIntent();
        if(null!=intent.getStringExtra("user_id")){
            user_id=Integer.parseInt(getIntent().getStringExtra("user_id"));
        }else{
            user_id=-1;
        }


        gson = new Gson();
        listView = findViewById(R.id.idv_data1);
        listView2=findViewById(R.id.idv_data2);
        listView3=findViewById(R.id.idv_data3);
        listView4=findViewById(R.id.idv_data4);

        user_name=findViewById(R.id.idv_name1);
        user_type=findViewById(R.id.idv_usertype);
        user_email=findViewById(R.id.idv_email1);
        user_name.setText(getIntent().getStringExtra("user_name"));
        user_type.setText(getIntent().getStringExtra("user_type"));
        user_email.setText(getIntent().getStringExtra("user_email"));

        mScrollView = (PullScrollView) findViewById(R.id.scroll_view);
        mHeadImg = (ImageView) findViewById(R.id.background_img);
        mScrollView.setHeader(mHeadImg);
        mScrollView.setOnTurnListener(this);
        getValues();
        getValues2();
        getValues3();
        getValues4();
    }
    private void getValues() {
        PageTextTask pageTextTask = new PageTextTask();
        pageTextTask.execute("http://"+ Constant.IP+":8080/searchfor_prj/IdvServlet");
    }

    private void listSource() {
        adapter = new IdvAdapter(this,spb , R.layout.listview_item);
        listView.setAdapter(adapter);
    }

    @Override
    public void onTurn() {

    }

    //连接服务端
    private class PageTextTask extends AsyncTask {


        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            listSource();
            listView.deferNotifyDataSetChanged();
        }

        @Override
        protected Object doInBackground(Object[] objects) {

            try {
                URL url = new URL((String) objects[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                OutputStream outputStream = connection.getOutputStream();
                String json1 = gson.toJson( user_id);//传用户id
                outputStream.write(json1.getBytes());

                InputStreamReader is = new InputStreamReader(connection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(is);
                StringBuffer str = new StringBuffer();
                String line = null;
                while (null!=(line = bufferedReader.readLine()))  {
                    str.append(line);
                }
                is.close();
                String jsonStr =new String(str.toString().getBytes("utf-8"),"UTF-8");
                Log.e("获取到的JSON格式的用户列表", jsonStr);
                spb = gson.fromJson(String.valueOf(jsonStr), new TypeToken<List<SearchPeopleBean>>() {}.getType());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
    }
    private void getValues2() {
        PageTextTask2 pageTextTask2 = new PageTextTask2();
        pageTextTask2.execute("http://"+ Constant.IP+":8080/searchfor_prj/IdvServlet2");
    }

    private void listSource2() {
        adapter2 = new IdvAdapter2(this,sfb , R.layout.listview_item);
        listView2.setAdapter(adapter2);
    }
    //连接服务端
    private class PageTextTask2 extends AsyncTask {


        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            listSource2();
            listView2.deferNotifyDataSetChanged();
        }

        @Override
        protected Object doInBackground(Object[] objects) {

            try {
                URL url = new URL((String) objects[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                OutputStream outputStream = connection.getOutputStream();
                String json1 = gson.toJson( user_id);//传用户id
                outputStream.write(json1.getBytes());

                InputStreamReader is = new InputStreamReader(connection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(is);
                StringBuffer str = new StringBuffer();
                String line = null;
                while (null!=(line = bufferedReader.readLine()))  {
                    str.append(line);
                }
                is.close();
                String jsonStr =new String(str.toString().getBytes("utf-8"),"UTF-8");
                Log.e("获取到的JSON格式的用户列表2", jsonStr);
                sfb = gson.fromJson(String.valueOf(jsonStr), new TypeToken<List<SearchFamilyBean>>() {}.getType());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
    }

    private void getValues3() {
        PageTextTask3 pageTextTask3 = new PageTextTask3();
        pageTextTask3.execute("http://"+ Constant.IP+":8080/searchfor_prj/IdvServlet3");
    }

    private void listSource3() {
        adapter3 = new IdvAdapter3(this,vgt , R.layout.listview_item);
        listView3.setAdapter(adapter3);
    }
    //连接服务端
    private class PageTextTask3 extends AsyncTask {


        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            listSource3();
            listView3.deferNotifyDataSetChanged();
        }

        @Override
        protected Object doInBackground(Object[] objects) {

            try {
                URL url = new URL((String) objects[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                OutputStream outputStream = connection.getOutputStream();
                String json1 = gson.toJson( user_id);//传用户id
                outputStream.write(json1.getBytes());

                InputStreamReader is = new InputStreamReader(connection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(is);
                StringBuffer str = new StringBuffer();
                String line = null;
                while (null!=(line = bufferedReader.readLine()))  {
                    str.append(line);
                }
                is.close();
                String jsonStr =new String(str.toString().getBytes("utf-8"),"UTF-8");
                Log.e("获取到的JSON格式的用户列表3", jsonStr);
                vgt = gson.fromJson(String.valueOf(jsonStr), new TypeToken<List<Vagrant>>() {}.getType());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
    }

    private void getValues4() {
        PageTextTask4 pageTextTask4 = new PageTextTask4();
        pageTextTask4.execute("http://"+ Constant.IP+":8080/searchfor_prj/IdvServlet4");
    }

    private void listSource4() {
        adapter4 = new IdvAdapter4(this,osb , R.layout.listview_item);
        listView4.setAdapter(adapter4);
    }
    //连接服务端
    private class PageTextTask4 extends AsyncTask {


        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            listSource4();
            listView4.deferNotifyDataSetChanged();
        }

        @Override
        protected Object doInBackground(Object[] objects) {

            try {
                URL url = new URL((String) objects[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                OutputStream outputStream = connection.getOutputStream();
                String json1 = gson.toJson( user_id);//传用户id
                outputStream.write(json1.getBytes());

                InputStreamReader is = new InputStreamReader(connection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(is);
                StringBuffer str = new StringBuffer();
                String line = null;
                while (null!=(line = bufferedReader.readLine()))  {
                    str.append(line);
                }
                is.close();
                String jsonStr =new String(str.toString().getBytes("utf-8"),"UTF-8");
                Log.e("获取到的JSON格式的用户列表4", jsonStr);
                osb = gson.fromJson(String.valueOf(jsonStr), new TypeToken<List<OtherSearchBean>>() {}.getType());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
    }



}
