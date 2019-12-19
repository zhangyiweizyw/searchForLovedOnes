package com.example.administrator.searchforlovedones;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ViewPagerTwo extends Fragment {
    public static View viewPageTwo;
    public static ListView listView;
    public static List<PageText> texts = new ArrayList<>();
    public static Gson gson;
    private SmartRefreshLayout smartRefreshLayout;
    private List<PageText> addtexts = new ArrayList<>();
    private int position = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(viewPageTwo==null) {
            viewPageTwo = inflater.inflate(R.layout.viewpager_two, container, false);
            gson = new Gson();
            findId();
            getValues();
            smartListener();
        }
        ViewGroup parent = (ViewGroup) viewPageTwo.getParent();
        if (parent != null) {
            parent.removeView(viewPageTwo);
        }
        return viewPageTwo;
    }

    private void smartListener() {

        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(1000);
                addValues();
            }
        });

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(1000);
                getValues();
            }
        });
    }

    private void addValues() {
        AddPageTextTask addPageTextTask = new AddPageTextTask();
        addPageTextTask.execute("http://"+Constant.IP+":8080/searchfor_prj/findway");
    }

    private void findId() {
        listView = viewPageTwo.findViewById(R.id.list_two);
        smartRefreshLayout = viewPageTwo.findViewById(R.id.smart_two);

    }

    //ListView设置
    public static void listSource() {
        PageListAdapter adapter = new PageListAdapter(texts, viewPageTwo.getContext(), R.layout.page_listitem);
        listView.setAdapter(adapter);
    }

    //添加数据
    public static void getValues() {
        PageTextTask pageTextTask = new PageTextTask();
        pageTextTask.execute("http://"+Constant.IP+":8080/searchfor_prj/findway");

    }

    public static class PageTextTask extends AsyncTask {


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
                texts = gson.fromJson(String.valueOf(jsonStr), new TypeToken<List<PageText>>() {}.getType());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
    }

    private class AddPageTextTask extends AsyncTask {


        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            for(PageText pageText : addtexts){
                texts.add(pageText);
            }
            position = listView.getFirstVisiblePosition();
            listSource();
            listView.setSelection(position);
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
                Log.e("获取到的JSON格式的用户列表", jsonStr);
                addtexts = gson.fromJson(String.valueOf(jsonStr), new TypeToken<List<PageText>>() {}.getType());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
    }


}
