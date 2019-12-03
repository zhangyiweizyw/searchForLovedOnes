package com.example.administrator.searchforlovedones;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

public class SearchRegisterMain extends Fragment {

    private ImageView img_totop = null;
    private ScrollView sc;
    private Button btn_searchpeople = null;
    private View firstpage;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (firstpage == null) {
            firstpage = inflater.inflate(R.layout.searchregister_main, container, false);
            firstpage.setBackgroundColor(Color.YELLOW);


            img_totop = firstpage.findViewById(R.id.btn_toTop);
            sc = firstpage.findViewById(R.id.sv_home);
            img_totop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    sc.post(new Runnable() {
                        public void run() {
                            // 滚动至顶部
                            sc.fullScroll(ScrollView.FOCUS_UP);
                        }
                    });
                }
            });
            SearchRegisterMain.MyListener myListener = new SearchRegisterMain.MyListener();
            btn_searchpeople = firstpage.findViewById(R.id.main_searchpeople);
            btn_searchpeople.setOnClickListener(myListener);
        }
        ViewGroup parent = (ViewGroup) firstpage.getParent();
        if (parent != null) {
            parent.removeView(firstpage);
        }
        return firstpage;
    }

    private class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.main_searchpeople:
                    //当点击家寻亲人，跳转家寻亲人登记页面
                    Intent intent = new Intent(getContext(), SearchPeople.class);
                    startActivity(intent);
            }
        }
    }
}
