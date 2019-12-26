package com.example.administrator.searchforlovedones;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.loper7.layout.TitleBar;


public class SearchRegisterMain extends Fragment {

    private ImageView img_totop = null;
    private ScrollView sc;
    private LinearLayout btn_searchpeople = null;
    private LinearLayout btn_searchfamily = null;
    private LinearLayout btn_vagranthelp = null;
    private LinearLayout btn_othersearch = null;
<<<<<<< HEAD
=======
    private TitleBar bar;
>>>>>>> 336cc1e62cfaf0c063ed5d2b697be480d07cfc41
    private View firstpage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        if (firstpage == null) {
            firstpage = inflater.inflate(R.layout.searchregister_main, container, false);
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
            MyListener myListener = new MyListener();
            btn_searchpeople = firstpage.findViewById(R.id.main_searchpeople);
            btn_searchfamily = firstpage.findViewById(R.id.main_searchfamily);
            btn_vagranthelp = firstpage.findViewById(R.id.main_vagranthelp);
            btn_othersearch = firstpage.findViewById(R.id.main_othersearch);
            btn_searchpeople.setOnClickListener(myListener);
            btn_searchfamily.setOnClickListener(myListener);
            btn_vagranthelp.setOnClickListener(myListener);
            btn_othersearch.setOnClickListener(myListener);
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
                    intent.putExtra("userId",""+MainActivity.userId);
                    Log.e("现在的id",MainActivity.userId+"");
                    startActivity(intent);
                    break;
                case R.id.main_searchfamily:
                    //当点击亲人寻家，跳转亲人寻家登记页面
                    Intent intent1 = new Intent(getContext(), SearchFamily.class);
                    intent1.putExtra("userId",""+MainActivity.userId);
                    Log.e("现在的id",MainActivity.userId+"");
                    startActivity(intent1);
                    break;
                case R.id.main_vagranthelp:
                    //当点击流浪救助，跳转流浪救助登记页面
                    Intent intent2 = new Intent(getContext(), VagrantHelp.class);
                    intent2.putExtra("userId",""+MainActivity.userId);
                    Log.e("现在的id",MainActivity.userId+"");
                    startActivity(intent2);
                    break;
                case R.id.main_othersearch:
                    //当点击其他寻人，跳转其他寻人登记界面
                    Intent intent3 = new Intent(getContext(), OtherSearch.class);
                    intent3.putExtra("userId",""+MainActivity.userId);
                    Log.e("现在的id",MainActivity.userId+"");
                    startActivity(intent3);
                    break;
            }
        }
    }
}
