package com.example.administrator.searchforlovedones;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.hjq.bar.TitleBar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class saysomething extends Activity {
    private static String ItemId;
    private GridView gridView;
    private EditText et_name;
    private EditText et_phone;
    private EditText et_content;
    private static final int REFRESH_FINISH=1;
    private Button ok;
    private ArrayList<Saysomething_bean> mData=new ArrayList<Saysomething_bean>();//总的数据
    private ArrayList<Saysomething_bean> now=new ArrayList<Saysomething_bean>();//当前要用的
    private FindCourtAdapter findCourtAdapter;
    private TitleBar bar;
    //Handler
    private Handler mainHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH_FINISH: {
                    //更新
                    //获取当前时间
                    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                    Date date=new Date(System.currentTimeMillis());
                    mData.add(new Saysomething_bean(ItemId,R.drawable.boyhead2,et_name.getText().toString(),et_phone.getText().toString(),simpleDateFormat.format(date),et_content.getText().toString()));
                    filtermData();
                    findCourtAdapter.notifyDataSetChanged();
                    break;
                }



            }

        }
    };
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saysomething);
        Intent intent = getIntent();
        ItemId = intent.getStringExtra("id");
        Log.e("获取到的id是",ItemId);
        //根据id在mData里抽取评论放到now里面
        initData();
        filtermData();
        findView();



    }

    private void filtermData() {
        int length=mData.size();
        now.clear();//保持零状态
        Log.e("此时mData里有 ",mData.size()+"个数据");
        for(int i=0;i<length;i++){
            if(mData.get(i).getIds().equals(ItemId))
                now.add(mData.get(i));
        }
        Log.e("现在now里有 ",now.size()+" 个数据");

    }

    private void initData() {
        mData.add(new Saysomething_bean("1001",R.drawable.girlhead1,
                "程英",
                "18689960336",
                "2018-01-02",
                "解开我最神秘的等待，星星坠落风在吹动，终于再将你拥入怀中，两颗心颤抖"));
        mData.add(new Saysomething_bean("1002",R.drawable.girlhead2,
                "程素灵",
                "18689960336",
                "2017-01-09",
                "每一夜被心痛穿越，思念永没有终点，早习惯了孤独相随，我微笑面对"));
        mData.add(new Saysomething_bean("1003",R.drawable.girlhead3,
                "陆无双",
                "18689960336",
                "2016-05-12",
                "相信我你选择等待，再多苦痛也不闪躲，只有你的温柔能解救，无边的冷漠"));
        mData.add(new Saysomething_bean("1004",R.drawable.boyhead2,
                "李莫愁",
                "18689960336",
                "2010-11-02",
                "无论经过多少的寒冬，我决不放手，啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦阿联"));
    }

    private void findView() {
        gridView=findViewById(R.id.gv_saysomething_content);
        et_name=findViewById(R.id.et_saysomething_name);
        et_phone=findViewById(R.id.et_saysomething_phone);
        et_content=findViewById(R.id.et_saysomething_content);
        ok=findViewById(R.id.btn_saysomething_ok);

        findCourtAdapter = new FindCourtAdapter<Saysomething_bean>(now, R.layout.item_saysomthing) {
            @Override
            public void bindView(ViewHolder holder, Saysomething_bean obj) {
                 holder.setImageResource(R.id.img_saysomething_head,obj.getId());
                 holder.setText(R.id.tv_saysomething_name,obj.getName());
                 holder.setText(R.id.tv_saysomething_phone,obj.getPhone());
                 holder.setText(R.id.tv_saysomething_time,obj.getTime());
                 holder.setText(R.id.tv_saysomething_content,obj.getContent());

            }
        };
        gridView.setAdapter(findCourtAdapter);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_name.getText().toString().length()>0&&et_phone.getText().length()>0&&et_content.getText().length()>0){//全部有内容才进行下一步

                    Toast.makeText(getApplicationContext(),"评论成功",Toast.LENGTH_SHORT).show();
                    freshHandler();

                }
                else{
                    Toast.makeText(getApplicationContext(),"你特么的有没填的",Toast.LENGTH_SHORT).show();
                }
            }
        });

//        bar.setBackImageResource(R.drawable.back);
//        bar.setUseRipple(true);

    }
    /**
     * 重新刷新布局
     */
    private void freshHandler() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(1000);//停滞1秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //向主线程发送消息 更新视图
                Message msg = new Message();
                msg.what = REFRESH_FINISH;
                mainHandler.sendMessage(msg);
            }
        }.start();
    }

}
