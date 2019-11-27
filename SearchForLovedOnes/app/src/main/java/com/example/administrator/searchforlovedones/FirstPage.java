package com.example.administrator.searchforlovedones;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FirstPage extends Fragment {
    public static MySpinner spinner;
    public static ListView listView;
    private View firstpage;
    private List<PageText> texts = new ArrayList<>();
    private TextView div1;
    private TextView div2;
    private TextView div3;
    private Button button1;
    private Button button2;
    private Button button3;
    public static ScrollView lvContent;
    private TextView con;
    public static Button btn_back;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        firstpage = inflater.inflate(R.layout.fragment_page, container, false);

        findId();
        itemCahnge();
        getValues();
        listSource();

        //设置返回按钮的透明度
        btn_back.setAlpha(0.8f);
        //设置返回按钮和详情页面视图为隐藏
        btn_back.setVisibility(View.GONE);
        lvContent.setVisibility(View.GONE);
        //点击事件设置
        click();
        con.setMovementMethod(ScrollingMovementMethod.getInstance());


        return firstpage;
    }

    private void click() {
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allToGrew();
                button1.setTextColor(Color.parseColor("#00B2EE"));
                div1.setBackgroundColor(Color.parseColor("#00B2EE"));
                texts.clear();
                PageText page = new PageText();
                page.setImgName("img");
                page.setTitle("1");
                page.setContent("1");
                texts.add(page);
                PageListAdapter adapter = new PageListAdapter(texts, firstpage.getContext(), R.layout.page_listitem);
                listView.setAdapter(adapter);
                listView.deferNotifyDataSetChanged();
                if(listView.getVisibility()== View.GONE){
                    backVisibility();
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allToGrew();
                button2.setTextColor(Color.parseColor("#00B2EE"));
                div2.setBackgroundColor(Color.parseColor("#00B2EE"));
                texts.clear();
                PageText page = new PageText();
                page.setImgName("img");
                page.setTitle("2");
                page.setContent("2");
                texts.add(page);
                PageListAdapter adapter = new PageListAdapter(texts, firstpage.getContext(), R.layout.page_listitem);
                listView.setAdapter(adapter);
                listView.deferNotifyDataSetChanged();
                if(listView.getVisibility()== View.GONE){
                    backVisibility();
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allToGrew();
                button3.setTextColor(Color.parseColor("#00B2EE"));
                div3.setBackgroundColor(Color.parseColor("#00B2EE"));
                texts.clear();
                PageText page = new PageText();
                page.setImgName("img");
                page.setTitle("3");
                page.setContent("3");
                texts.add(page);
                PageListAdapter adapter = new PageListAdapter(texts, firstpage.getContext(), R.layout.page_listitem);
                listView.setAdapter(adapter);
                listView.deferNotifyDataSetChanged();
                if(listView.getVisibility()== View.GONE){
                    backVisibility();
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backVisibility();
            }
        });
    }

    private void backVisibility() {
        listView.setVisibility(View.VISIBLE);
        btn_back.setVisibility(View.GONE);
        lvContent.setVisibility(View.GONE);
        spinner.setVisibility(View.VISIBLE);
    }

    public static void changeVisibility(){
        listView.setVisibility(View.GONE);
        btn_back.setVisibility(View.VISIBLE);
        lvContent.setVisibility(View.VISIBLE);
        spinner.setVisibility(View.GONE);
    }

    //改变数据状态Visibility


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


    //ListView设置
    private void listSource() {
        PageListAdapter adapter = new PageListAdapter(texts, firstpage.getContext(), R.layout.page_listitem);
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

        lvContent = firstpage.findViewById(R.id.lvContent);
        con = firstpage.findViewById(R.id.con);
        btn_back = firstpage.findViewById(R.id.first_back);
    }

    //设置下拉按钮方法
    private void itemCahnge() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("ResourceType")
            @Override

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0 && view != null) {
                    view.setVisibility(View.INVISIBLE);
                }

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
