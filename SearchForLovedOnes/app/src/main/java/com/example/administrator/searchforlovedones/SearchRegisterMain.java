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
    private Button btn_family = null;
    private Button btn_other = null;
    private Button btn_help = null;
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
            btn_family = firstpage.findViewById(R.id.main_searchfamily);
            btn_help = firstpage.findViewById(R.id.main_vagranthelp);
            btn_other = firstpage.findViewById(R.id.main_othersearch);
            btn_searchpeople.setOnClickListener(myListener);
            btn_family.setOnClickListener(myListener);
            btn_other.setOnClickListener(myListener);
            btn_help.setOnClickListener(myListener);




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
            Intent intent = null;
            switch (v.getId()) {
                case R.id.main_searchpeople:
                    //当点击家寻亲人，跳转家寻亲人登记页面
                    intent = new Intent(getContext(), SearchPeople.class);
                    break;
                case R.id.main_searchfamily:
                    //当点亲人寻家，跳转亲人寻家登记页面
                    intent = new Intent(getContext(), SearchFamily.class);
                    break;
                case R.id.main_vagranthelp:
                    //当点击流浪救助，跳转流浪救助登记页面
                    intent = new Intent(getContext(), VagrantHelp.class);
                    break;
                case R.id.main_othersearch:
                    //当点击其他寻人，跳转其他寻人登记页面
                    intent = new Intent(getContext(), OtherSearch.class);
                    break;
            }
            startActivity(intent);
        }
    }
}
