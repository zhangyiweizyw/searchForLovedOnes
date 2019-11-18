package com.example.administrator.searchforlovedones;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FirstPage extends Fragment{
    private Spinner spinner;
    private ListView listView;
    private View firstpage;
    private List<PageText> texts = new ArrayList<>();
    private TextView div1;
    private TextView div2;
    private TextView div3;
    private Button button1;
    private Button button2;
    private Button button3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        firstpage = inflater.inflate(R.layout.fragment_page, container, false);


        findId();

        itemCahnge();
        getValues();
        listSource();
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allToGrew();
                button1.setTextColor(Color.parseColor("#00B2EE"));
                div1.setBackgroundColor(Color.parseColor("#00B2EE"));
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allToGrew();
                button2.setTextColor(Color.parseColor("#00B2EE"));
                div2.setBackgroundColor(Color.parseColor("#00B2EE"));
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allToGrew();
                button3.setTextColor(Color.parseColor("#00B2EE"));
                div3.setBackgroundColor(Color.parseColor("#00B2EE"));
            }
        });

        return firstpage;
    }

    //添加数据
    private void getValues() {
       for(int i=0;i < 10;i++) {
           PageText page = new PageText();
           page.setImgName("img");
           page.setTitle("EditText字体颜色");
           page.setContent("我在写一个记事本，想实现编辑EditText中文字的大小，颜色等等属性后，保存，然后显示的TextView也可以显示edittext中的变化。求大神指导，谢谢");
           texts.add(page);
       }
    }


    //ListView设置
    private void listSource() {
        PageListAdapter adapter = new PageListAdapter(texts,firstpage.getContext(),R.layout.page_listitem);
        listView.setAdapter(adapter);
    }

    //获取ID
    public void findId() {
        spinner = firstpage.findViewById(R.id.first_span);
        listView = firstpage.findViewById(R.id.list);

        button1 = firstpage.findViewById(R.id.page_button1);
        div1 = firstpage.findViewById(R.id.page_div1);

        button2 = firstpage.findViewById(R.id.page_button2);
        div2 = firstpage.findViewById(R.id.page_div2);

        button3 = firstpage.findViewById(R.id.page_button3);
        div3 = firstpage.findViewById(R.id.page_div3);
    }

    //设置下拉按钮方法
    private void itemCahnge() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("ResourceType")
            @Override

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("select", position + "");
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.fragmentTabHost.setCurrentTab(position);
                spinner.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void allToGrew() {
        button1.setTextColor(Color.parseColor("#838B8B"));
        button2.setTextColor(Color.parseColor("#838B8B"));
        button3.setTextColor(Color.parseColor("#838B8B"));
        div1.setBackgroundColor(Color.WHITE);
        div2.setBackgroundColor(Color.WHITE);
        div3.setBackgroundColor(Color.WHITE);

    }
}
