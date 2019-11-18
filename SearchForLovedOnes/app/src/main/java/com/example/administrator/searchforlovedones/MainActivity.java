package com.example.administrator.searchforlovedones;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

    private int Images[] = {R.drawable.first_normal,R.drawable.court_noraml,R.drawable.find_normal,R.drawable.words_noraml,R.drawable.center_noraml};
    private int Images_select[] = {R.drawable.first_select,R.drawable.court_select,R.drawable.find_select,R.drawable.words_select,R.drawable.center_select};
    private String tags[]={"首页","寻人大厅","发布寻人","真情留言","个人中心"};
    private Class fragment[] ={FirstPage.class,F1.class,F2.class,F3.class,F4.class};
    public FragmentTabHost fragmentTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTabHost();
        fragmentTabHost.setCurrentTab(0);
        fragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                for(int i=0;i < fragment.length;i++){
                    if(tags[i].equals(tabId)){
                        ((TextView)fragmentTabHost.getTabWidget().getChildTabViewAt(i).findViewById(R.id.tab_item))
                                .setTextColor(Color.rgb(135,206,255));
                        ((TextView)fragmentTabHost.getTabWidget().getChildTabViewAt(i).findViewById(R.id.tab_item))
                                .setCompoundDrawablesWithIntrinsicBounds(
                                        null,
                                        getResources().getDrawable(Images_select[i],null),
                                        null,null);
                    }else{
                        ((TextView)fragmentTabHost.getTabWidget().getChildTabViewAt(i).findViewById(R.id.tab_item))
                                .setTextColor(Color.rgb(133,133,133));
                        ((TextView)fragmentTabHost.getTabWidget().getChildTabViewAt(i).findViewById(R.id.tab_item))
                                .setCompoundDrawablesWithIntrinsicBounds(
                                        null,
                                        getResources().getDrawable(Images[i],null),
                                        null,null);
                    }
                }

            }
        });


    }

    private void initTabHost() {
        fragmentTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        fragmentTabHost.setup(this,getSupportFragmentManager(),android.R.id.tabcontent);
        fragmentTabHost.getTabWidget().setDividerDrawable(null);
        for(int i=0;i < fragment.length;i++){
            TabHost.TabSpec tabSpec = fragmentTabHost
                    .newTabSpec(tags[i]).setIndicator(getTextView(i));

            fragmentTabHost.addTab(tabSpec,fragment[i],null);
        }
    }


    private View getTextView(int i) {
        View view = getLayoutInflater().inflate(R.layout.fragment_tab,null);
        TextView textView = view.findViewById(R.id.tab_item);
        textView.setText(tags[i]);

        if(i==0){
            textView.setTextColor(Color.rgb(135,206,255));
            textView.setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    this.getResources().getDrawable(Images_select[i],null),
                    null,null);
        }else{
            textView.setTextColor(Color.rgb(133,133,133));
            textView.setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    this.getResources().getDrawable(Images[i],null),
                    null,null);
        }
        return view;
    }
}