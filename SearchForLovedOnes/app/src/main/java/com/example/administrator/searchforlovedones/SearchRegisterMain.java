package cn.edu.hebtu.software.xunqin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

public class SearchRegisterMain extends AppCompatActivity {

    private ImageView img_totop=null;
    private ScrollView sc;
    private Button btn_searchpeople=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchregister_main);

        img_totop=findViewById(R.id.btn_toTop);
        sc=findViewById(R.id.sv_home);
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
        MyListener myListener=new MyListener();
        btn_searchpeople=findViewById(R.id.main_searchpeople);
        btn_searchpeople.setOnClickListener(myListener);
    }

    private class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.main_searchpeople:
                    //当点击家寻亲人，跳转家寻亲人登记页面
                    Intent intent=new Intent(SearchRegisterMain.this,SearchPeople.class);
                    startActivity(intent);
            }
        }
    }
}
