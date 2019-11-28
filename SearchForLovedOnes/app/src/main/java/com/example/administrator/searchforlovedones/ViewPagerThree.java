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

public class ViewPagerThree extends Fragment {
    private View viewPageThree;
    private ListView listView;
    private List<PageText> texts = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewPageThree = inflater.inflate(R.layout.viewpager_three,container,false);
        viewPageThree.setBackgroundColor(Color.YELLOW);

        findId();
        getValues();
        listSource();
        PageListAdapter adapter = new PageListAdapter(texts, viewPageThree.getContext(), R.layout.page_listitem);
        listView.setAdapter(adapter);
        listView.deferNotifyDataSetChanged();
        return viewPageThree;
    }
    private void findId() {
        listView = viewPageThree.findViewById(R.id.list_three);

    }

    //ListView设置
    private void listSource() {
        PageListAdapter adapter = new PageListAdapter(texts, viewPageThree.getContext(), R.layout.page_listitem);
        listView.setAdapter(adapter);
    }
    //添加数据
    private void getValues() {
        for (int i = 0; i < 10; i++) {
            PageText page = new PageText();
            page.setImgName("img");
            page.setTitle("习近平总书记关于访谈");
            page.setContent("习近平总书记最近访问了关于苏格拉里国...");
            texts.add(page);
        }
    }
}
