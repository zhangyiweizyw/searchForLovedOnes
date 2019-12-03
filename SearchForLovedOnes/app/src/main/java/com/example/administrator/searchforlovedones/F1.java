package com.example.administrator.searchforlovedones;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class F1 extends Fragment {
    private ImageView filter;
    private View firstpage;
    private DrawerLayout drawer_layout;
    private Button search;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        firstpage = inflater.inflate(R.layout.fragment_page1, container, false);
        firstpage.setBackgroundColor(Color.WHITE);
        /*filter = firstpage.findViewById(R.id.img_filter);
        drawer_layout = firstpage.findViewById(R.id.drawer_layout);
        search = firstpage.findViewById(R.id.btn_search);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer_layout.openDrawer(Gravity.RIGHT);
//                drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN,Gravity.END);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer_layout.closeDrawer(Gravity.RIGHT);
            }
        });*/


        return firstpage;


    }
}
