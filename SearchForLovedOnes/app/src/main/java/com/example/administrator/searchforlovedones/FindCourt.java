package com.example.administrator.searchforlovedones;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FindCourt extends Fragment {
    private static String index;
    private OkHttpClient okHttpClient = new OkHttpClient();
    private ArrayList<Basic_information> mData = new ArrayList<Basic_information>();
    private ImageView filter;
    private View firstpage;
    private FindCourtAdapter findCourtAdapter;
    private GridView gridView;
    private DrawerLayout drawer_layout;
    private Button search;
    private Button gotoRegister;
    private Gson gson = new Gson();
    private EditText input;
    private RadioButton search_home;
    private RadioButton search_person;
    private RadioButton search_vagrancy;
    private RadioButton other_search;
    private static final int REFRESH_FINISH = 1;
    private static final int SMART_FINISH=2;
    private SmartRefreshLayout refreshLayout;
    //Handler
    private Handler mainHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH_FINISH: {
                    //更新
                    findCourtAdapter.notifyDataSetChanged();
                    break;
                }
                case SMART_FINISH:{
                    findCourtAdapter.notifyDataSetChanged();
                    refreshLayout.finishRefresh();
                    break;
                }


            }

        }
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (firstpage == null) {


            firstpage = inflater.inflate(R.layout.fragment_page1, container, false);
            firstpage.setBackgroundColor(Color.WHITE);
            initData();
            findView();
            setItemClick();//点击一个item后跳转到详情页
            setSmartFreshListeners();//设置智能刷新控件的效果


        }
        ViewGroup parent = (ViewGroup) firstpage.getParent();
        if (parent != null) {
            parent.removeView(firstpage);
        }

        return firstpage;


    }

    private void setSmartFreshListeners() {
        //监听下拉刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //向主线程发送消息 更新视图
                        Message msg = new Message();
                        msg.what = SMART_FINISH;
                        mainHandler.sendMessage(msg);

                    }
                }.start();
            }
        });
        //监听上拉加载更多
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                PersonListTask task = new PersonListTask();
                task.execute();
            }
        });
    }
    //异步类实现上拉加载更多
    private class PersonListTask extends AsyncTask{
        @Override
        protected Object doInBackground(Object[] objects) {
            try{
                Thread.sleep(1500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
//            //更新视图
//            mData.add(new People(R.drawable.dog2, "目瞪狗呆"));
            //更新
            findCourtAdapter.notifyDataSetChanged();
            //结束加载更多的动画
            refreshLayout.finishLoadMore();
            Log.e("上拉加载更多已结束","结束");
        }
    }

    private void setItemClick() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(firstpage.getContext(), "你点击了~" + position + "~项", Toast.LENGTH_SHORT).show();
//                Intent intent=new Intent(getActivity(),findcourt_detail_page.class);
                Intent intent=new Intent();
                Log.e("此时点击的item的id是",mData.get(position).getId());
                intent.putExtra("id",mData.get(position).getId());
                intent.setClass(getActivity(),findcourt_detail_page.class);
//                startActivity(intent);
                startActivity(intent);
            }

        });
    }

    private void findView() {
        //跳转到寻亲登记界面
        gotoRegister=firstpage.findViewById(R.id.btn_findcour_goto_register);
//        gotoRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        //智能刷新
        refreshLayout=firstpage.findViewById(R.id.findcourt_smart_layout);
        //获取单选框id
        search_home = firstpage.findViewById(R.id.rbtn_search_home);
        search_person = firstpage.findViewById(R.id.rbtn_search_person);
        search_vagrancy = firstpage.findViewById(R.id.rbtn_search_vagrancy);
        other_search = firstpage.findViewById(R.id.rbtn_other_search);
        //抽屉方法
        filter = firstpage.findViewById(R.id.img_filter);
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
                //判断是否有选中的单选框
                if (search_home.isChecked() || search_vagrancy.isChecked() || search_person.isChecked() || other_search.isChecked()) {
                    //检查单选框情况
                    //以头号码为区分标准，即凡是以1为开头的id即为寻家。。依次类推
                    try {
//                        threadResponse();
                        ExamRadiobutton();
                        freshHandler();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //gridview布局
        gridView = firstpage.findViewById(R.id.grid_findcout_show_photos);
        findCourtAdapter = new FindCourtAdapter<Basic_information>(mData, R.layout.item_findcourt) {
            @Override
            public void bindView(ViewHolder holder, Basic_information obj) {
                holder.setImageUseGlide(R.id.iv_findcourt_photo, obj.getPhoto());
                holder.setText(R.id.tv_findcourt_name, obj.getName());
                holder.setText(R.id.tv_findcourt_sex, obj.getSex());
                holder.setText(R.id.tv_findcourt_id, obj.getId());
            }
        };
        gridView.setAdapter(findCourtAdapter);

        /**
         * 关于EditView的操作,相应虚拟键盘上的"搜索"按钮
         */
        input=firstpage.findViewById(R.id.et_findcourt_input);
        input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if ((actionId == 0 || actionId == 3) && event != null) {
                    //点击搜索要做的操作
                    if(input.getText().toString().length()>0){
                        String code=input.getText().toString().trim();//获取输入框字符串
                        //判断输入的类型
                        Pattern pNumber = Pattern.compile("[0-9]*"); //数字
                        Pattern pLetter = Pattern.compile("[a-zA-Z]*"); //字母
                        Pattern pChinese = Pattern.compile("[\\u4e00-\\u9fa5]*"); //汉字
                        if (pNumber.matcher(code).matches()) {//启用id搜索
                            Log.e("测试", "走到了这里，说明是数字");
                            String codes=input.getText().toString();
                            threadResponseById(codes);
                            freshHandler();
//                            String blank="";
//                            input.setText(blank);
                        } else if (pLetter.matcher(code).matches()||pChinese.matcher(code).matches()) {//启用人名搜索
                            Log.e("测试", "走到了这里，说明是字母或汉字");
                            String names=input.getText().toString();
                            threadResponseByName(names);
                            freshHandler();
//                            String blank="";
//                            input.setText(blank);

                        }
                    }
                }
                return false;
            }
        });
    }

    /**
     * 在数据库里任意取一些数据放到展示界面
     */
    private void initData() {
//        mData.add(new Basic_information("100081", "/images/a51.png", "图标1", "未知"));
//        mData.add(new Basic_information("100082", "/images/a5.jpg", "图标2", "未知"));
//        mData.add(new Basic_information("100083", "/images/a2.jpg", "图标3", "未知"));
//        mData.add(new Basic_information("100084", "/images/a1.jpg", "图标4", "未知"));

        GetDataRandom("get_clientsome_random_data");//我不信有人会取这样的名字QAQ
        freshHandler();//重新刷新一下页面，显示初始化数据
    }
    /**
     * 实现单选框查询和输入id的查询
     * @param code
     */
    private void GetDataRandom( final String code) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    JSONObject json = new JSONObject();
                    json.put("client", code);
                    Log.e("已经封装好了的数据", json.toString());
                    MediaType type = MediaType.parse("application/json;charset=UTF-8");
                    RequestBody requestBody = RequestBody.create(type, json.toString());
                    Log.e("requestBody", json.toString());
                    Request request = new Request.Builder()
                            .url(Constant.TEST_URL)
                            .post(requestBody)
                            .build();
                    Log.e("request", json.toString());
                    Response response = okHttpClient.newCall(request).execute();//有问题？
//                    String returnok = response.body().string();
                    if (response.isSuccessful()) {//response.body().string()只能用一次！！！！！！！！
                        ArrayList<Basic_information> newbasic = gson.fromJson(response.body().string(),
                                new TypeToken<List<Basic_information>>() {
                                }.getType());
                        //在photo前加上指定字符串
                        mData.addAll(0,newbasic);//全部添加
                        //打印检查
                        for (Basic_information u : newbasic) {
                            String id = (String) u.getId();
                            String name = u.getName();
                            String sex = u.getSex();
                            String photo = u.getPhoto();
                            Log.e("有id为 ", id);
                            Log.e("有name为 ", name);
                            Log.e("有sex为 ", sex);
                            Log.e("有photo为 ", photo);
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
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


    /**
     * 响应单选框的事件
     */
    private void ExamRadiobutton(){
        if (search_home.isChecked())
            index = "1";
        else if (search_person.isChecked())
            index = "2";
        else if (search_vagrancy.isChecked())
            index = "3";
        else if (other_search.isChecked())
            index = "4";
        threadResponseById(index);
    }

    //自定义线程执行response操作

    /**
     * 实现单选框查询和输入id的查询
     * @param code
     */
    private void threadResponseById( final String code) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    JSONObject json = new JSONObject();
                    json.put("client", code);
                    Log.e("已经封装好了的数据", json.toString());
                    MediaType type = MediaType.parse("application/json;charset=UTF-8");
                    RequestBody requestBody = RequestBody.create(type, json.toString());
                    Log.e("requestBody", json.toString());
                    Request request = new Request.Builder()
                            .url(Constant.TEST_URL)
                            .post(requestBody)
                            .build();
                    Log.e("request", json.toString());
                    Response response = okHttpClient.newCall(request).execute();//有问题？
//                    String returnok = response.body().string();
                    if (response.isSuccessful()) {//response.body().string()只能用一次！！！！！！！！
                        ArrayList<Basic_information> newbasic = gson.fromJson(response.body().string(),
                                new TypeToken<List<Basic_information>>() {
                                }.getType());
                        //在photo前加上指定字符串
                        mData.clear();
                        mData.addAll(0,newbasic);//全部添加
                        //打印检查
                        for (Basic_information u : newbasic) {
                            String id = (String) u.getId();
                            String name = u.getName();
                            String sex = u.getSex();
                            String photo = u.getPhoto();
                            Log.e("有id为 ", id);
                            Log.e("有name为 ", name);
                            Log.e("有sex为 ", sex);
                            Log.e("有photo为 ", photo);


                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    private void threadResponseByName(final String name) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    JSONObject json = new JSONObject();
                    json.put("client", name);
                    Log.e("已经封装好了的数据", json.toString());
                    MediaType type = MediaType.parse("application/json;charset=UTF-8");
                    RequestBody requestBody = RequestBody.create(type, json.toString());
                    Log.e("requestBody", json.toString());
                    Request request = new Request.Builder()
                            .url(Constant.TEST_URL)
                            .post(requestBody)
                            .build();
                    Log.e("request", json.toString());
                    Response responseName = okHttpClient.newCall(request).execute();
                    if (responseName.isSuccessful()) {//response.body().string()只能用一次！！！！！！！！
                        ArrayList<Basic_information> newbasics = gson.fromJson(responseName.body().string(),
                                new TypeToken<List<Basic_information>>() {
                                }.getType());
                        //在photo前加上指定字符串
                        mData.clear();
                        mData.addAll(0,newbasics);//全部添加

                        //打印检查
                        for (Basic_information u : newbasics) {
                            String id = (String) u.getId();
                            String name = u.getName();
                            String sex = u.getSex();
                            String photo = u.getPhoto();
                            Log.e("有id为 ", id);
                            Log.e("有name为 ", name);
                            Log.e("有sex为 ", sex);
                            Log.e("有photo为 ", photo);


                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


}
