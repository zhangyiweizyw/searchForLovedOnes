package com.example.administrator.searchforlovedones;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TabHost;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.tencent.qq.QQ;

public class MainActivity extends FragmentActivity {

    private int Images[] = {R.drawable.first_normal, R.drawable.court_normal, R.drawable.find_normal, R.drawable.words_normal, R.drawable.center_normal};
    private int Images_select[] = {R.drawable.first_select, R.drawable.court_select, R.drawable.find_select, R.drawable.words_select, R.drawable.center_select};
    private String tags[] = {"首页", "寻人大厅", "发布寻人", "真情留言", "个人中心"};
    private Class fragment[] = {FirstPage.class, FindCourt.class, SearchRegisterMain.class, TrueFeelingsMessage.class, IndivdualCenter.class};
    public FragmentTabHost fragmentTabHost;
    public static int userId=-1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        initTabHost();

        fragmentTabHost.setCurrentTab(0);
        Intent intent = getIntent();
        if(null==intent.getStringExtra("userId")){
            userId=-1;
        }else {
            userId=Integer.parseInt(intent.getStringExtra("userId"));
        }
        fragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                for (int i = 0; i < fragment.length; i++) {
                    if (tags[i].equals(tabId)) {
                        ((TextView) fragmentTabHost.getTabWidget().getChildTabViewAt(i).findViewById(R.id.tab_item))
                                .setTextColor(Color.rgb(255,109,145));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            ((TextView) fragmentTabHost.getTabWidget().getChildTabViewAt(i).findViewById(R.id.tab_item))
                                    .setCompoundDrawablesWithIntrinsicBounds(
                                            null,
                                            getResources().getDrawable(Images_select[i], null),
                                            null, null);
                        }
                    } else {
                        ((TextView) fragmentTabHost.getTabWidget().getChildTabViewAt(i).findViewById(R.id.tab_item))
                                .setTextColor(Color.rgb(133, 133, 133));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            ((TextView) fragmentTabHost.getTabWidget().getChildTabViewAt(i).findViewById(R.id.tab_item))
                                    .setCompoundDrawablesWithIntrinsicBounds(
                                            null,
                                            getResources().getDrawable(Images[i], null),
                                            null, null);
                        }
                    }
                }

            }
        });


        fragmentTabHost.getTabWidget().getChildAt(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentTabHost.getCurrentTab() != 0) {
                    fragmentTabHost.setCurrentTab(0);
                } else {
                    Log.e("refresh", "1");

                    Glide.with(getApplicationContext())
                            .load(getResources().getDrawable(R.drawable.loading))
                            .into(FirstPage.load);
                    FirstPage.load.setVisibility(View.VISIBLE);


                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            FirstPage.load.setVisibility(View.INVISIBLE);
                            switch (FirstPage.tabLayout.getSelectedTabPosition()) {
                                case 0:
                                    ViewPagerOne.getValues();
                                    ViewPagerOne.listView.deferNotifyDataSetChanged();
                                    break;
                                case 1:
                                    ViewPagerTwo.getValues();
                                    ViewPagerTwo.listView.deferNotifyDataSetChanged();
                                    break;
                                case 2:
                                    ViewPagerThree.getValues();
                                    ViewPagerThree.listView.deferNotifyDataSetChanged();
                                    break;
                            }

                        }
                    }, 2000);


                }
            }
        });




    }

    //第三方平台分享
    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle(getString(R.string.ssdk_oks_multi_share));
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，确保SDcard下面存在此张图片
        oks.setImagePath("/sdcard/test.jpg");
        // url在微信、Facebook等平台中使用
        oks.setUrl("http://sharesdk.cn");
        // 启动分享GUI
        oks.show(this);
    }

    //第三方平台登录
    public void loginPlatForm() {

        Platform plat = ShareSDK.getPlatform(QQ.NAME);
//移除授权状态和本地缓存，下次授权会重新授权
        plat.removeAccount(true);
//SSO授权，传false默认是客户端授权
        plat.SSOSetting(false);
//授权回调监听，监听oncomplete，onerror，oncancel三种状态
        plat.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(Platform platform, int i) {

            }
        });
//抖音登录适配安卓9.0
        ShareSDK.setActivity(MainActivity.this);
//要数据不要功能，主要体现在不会重复出现授权界面
        plat.showUser(null);
    }

    private void initTabHost() {
        fragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        fragmentTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        fragmentTabHost.getTabWidget().setDividerDrawable(null);
        for (int i = 0; i < fragment.length; i++) {
            TabHost.TabSpec tabSpec = fragmentTabHost
                    .newTabSpec(tags[i]).setIndicator(getTextView(i));


            fragmentTabHost.addTab(tabSpec, fragment[i], null);
        }
    }


    private View getTextView(int i) {
        View view = getLayoutInflater().inflate(R.layout.fragment_tab, null);
        TextView textView = view.findViewById(R.id.tab_item);
        textView.setText(tags[i]);

        if (i == 0) {
            textView.setTextColor(Color.rgb(255,109,145));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                textView.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        this.getResources().getDrawable(Images_select[i], null),
                        null, null);
            }
        } else {
            textView.setTextColor(Color.rgb(133, 133, 133));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                textView.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        this.getResources().getDrawable(Images[i], null),
                        null, null);
            }
        }
        return view;
    }
}
