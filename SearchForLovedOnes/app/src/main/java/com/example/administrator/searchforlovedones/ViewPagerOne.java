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

public class ViewPagerOne extends Fragment {
    private View viewPageOne;
    private ListView listView;
    private List<PageText> texts = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewPageOne = inflater.inflate(R.layout.viewpager_one,container,false);
        viewPageOne.setBackgroundColor(Color.YELLOW);

        findId();
        getValues();
        listSource();
        PageListAdapter adapter = new PageListAdapter(texts, viewPageOne.getContext(), R.layout.page_listitem);
        listView.setAdapter(adapter);
        listView.deferNotifyDataSetChanged();

        return viewPageOne;
    }

    private void findId() {
        listView = viewPageOne.findViewById(R.id.list_one);

    }

    //ListView设置
    private void listSource() {
        PageListAdapter adapter = new PageListAdapter(texts, viewPageOne.getContext(), R.layout.page_listitem);
        listView.setAdapter(adapter);
    }
    //添加数据
    private void getValues() {
        for (int i = 0; i < 10; i++) {
            PageText page = new PageText();
            page.setImgName("img");
            page.setTitle("EditText字体颜色");
            page.setContent("我在写一个记事本，想实现编辑EditText中文字的大小，颜色等等属性后，保存，然后显示的TextView也可以显示edittext中的变化。求大神指导，谢谢");
            texts.add(page);
        }
    }
}
