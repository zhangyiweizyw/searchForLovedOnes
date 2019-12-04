package com.example.administrator.searchforlovedones;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class FirstPage extends Fragment {
    public MySpinner spinner;
    private View firstpage;
    private Button btn_reg;
    public ScrollView lvContent;
    public Button btn_back;
    private List<String> list;
    public static TabLayout tabLayout;
    private ViewPager viewPager;
    public static ImageView load;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //避免重复创建Fragment
        if (firstpage == null) {
            firstpage = inflater.inflate(R.layout.fragment_page, container, false);
            findId();
            //给TabLayout添加Tab
            addTab();
            //相关连Tablayout和ViewPager
            tabAndView();
            //点击事件设置
            click();
            //下拉按钮改变
            itemCahnge();
            load.setVisibility(View.INVISIBLE);



        }

        ViewGroup parent = (ViewGroup) firstpage.getParent();
        if (parent != null) {
            parent.removeView(firstpage);
        }

        return firstpage;
    }

    private void tabAndView() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager(), list);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void addTab() {
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        list = new ArrayList<>();
        list.add("防拐防骗");
        list.add("寻人方法");
        list.add("政策法规");
        tabLayout.addTab(tabLayout.newTab().setText(list.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(list.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(list.get(2)));
    }

    private void click() {


        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    //获取ID
    public void findId() {
        spinner = firstpage.findViewById(R.id.first_span);
        btn_reg = firstpage.findViewById(R.id.btn_reg);
        tabLayout = firstpage.findViewById(R.id.tab_essence);
        viewPager = firstpage.findViewById(R.id.vp_essence);
        load = firstpage.findViewById(R.id.load);
        lvContent = firstpage.findViewById(R.id.lvContent);
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

}
