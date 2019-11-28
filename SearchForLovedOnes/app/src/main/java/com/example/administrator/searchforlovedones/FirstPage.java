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
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FirstPage extends Fragment {
    public static MySpinner spinner;
    private View firstpage;
    private Button btn_reg;
    public static ScrollView lvContent;
    private TextView con;
    public static Button btn_back;


    private TabLayout tabLayout;
    private ViewPager viewPager;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //避免重复创建Fragment
        if(firstpage==null){
            firstpage = inflater.inflate(R.layout.fragment_page, container, false);
            findId();

            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
            tabLayout.setTabMode(TabLayout.MODE_FIXED);
            List<String> list = new ArrayList<>();
            list.add("防拐防骗");
            list.add("寻人方法");
            list.add("政策法规");
            tabLayout.addTab(tabLayout.newTab().setText(list.get(0)));
            tabLayout.addTab(tabLayout.newTab().setText(list.get(1)));
            tabLayout.addTab(tabLayout.newTab().setText(list.get(2)));
            ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager(),list);
            viewPager.setAdapter(adapter);
            tabLayout.setupWithViewPager(viewPager);
            click();
            itemCahnge();
            //设置返回按钮的透明度
            btn_back.setAlpha(0.8f);
            //设置返回按钮和详情页面视图为隐藏
            btn_back.setVisibility(View.GONE);
            lvContent.setVisibility(View.GONE);
            //点击事件设置
            /*click();*/
            con.setMovementMethod(ScrollingMovementMethod.getInstance());
        }

        ViewGroup parent = (ViewGroup) firstpage.getParent();
        if (parent != null) {
            parent.removeView(firstpage);
        }

        return firstpage;
    }

    private void click() {


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    //获取ID
    public void findId() {
        spinner = firstpage.findViewById(R.id.first_span);

        lvContent = firstpage.findViewById(R.id.lvContent);
        con = firstpage.findViewById(R.id.con);
        btn_back = firstpage.findViewById(R.id.first_back);
        btn_reg = firstpage.findViewById(R.id.btn_reg);

        tabLayout = firstpage.findViewById(R.id.tab_essence);
        viewPager = firstpage.findViewById(R.id.vp_essence);

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
