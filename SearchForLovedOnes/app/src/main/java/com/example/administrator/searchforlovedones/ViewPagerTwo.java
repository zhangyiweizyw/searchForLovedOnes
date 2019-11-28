package com.example.administrator.searchforlovedones;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerTwo extends Fragment {
    private View viewPageTwo;
    private ListView listView;
    private List<PageText> texts = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewPageTwo = inflater.inflate(R.layout.viewpager_two,container,false);
        viewPageTwo.setBackgroundColor(Color.YELLOW);
        findId();
        getValues();
        listSource();
        PageListAdapter adapter = new PageListAdapter(texts, viewPageTwo.getContext(), R.layout.page_listitem);
        listView.setAdapter(adapter);
        listView.deferNotifyDataSetChanged();
        return viewPageTwo;
    }
    private void findId() {
        listView = viewPageTwo.findViewById(R.id.list_two);

    }

    //ListView设置
    private void listSource() {
        PageListAdapter adapter = new PageListAdapter(texts, viewPageTwo.getContext(), R.layout.page_listitem);
        listView.setAdapter(adapter);
    }
    //添加数据
    private void getValues() {
        for (int i = 0; i < 10; i++) {
            PageText page = new PageText();
            page.setImgName("img");
            page.setTitle("找人急先锋");
            page.setContent("虽然不知道这里写什么，但是不写的话感觉很别扭");
            texts.add(page);
        }
    }
}
