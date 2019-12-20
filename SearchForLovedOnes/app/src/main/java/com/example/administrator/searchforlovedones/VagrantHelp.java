package com.example.administrator.searchforlovedones;

import android.Manifest;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.loper7.layout.TitleBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class VagrantHelp extends Activity {

    private ImageView img_add=null;//添加图片按钮
    private Button btn_sumbit=null;//确认登记按钮
    private LinearLayout addimg_view=null;
    private int addimgId=0;//添加图片id
    private List<String> imgpaths=new ArrayList<>();//添加的图片地址
    private int addcount=0;//添加图片数量
    private ImageView img_remove=null;//删除图片按钮
    private List<ImageView>imageViews=new ArrayList<>();//新添加的图片布局

    private EditText vagrant_name=null;//流浪者姓名
    private RadioButton vagrant_sexwoman=null;//性
    private EditText vagrant_age=null;//流浪者大约年龄
    private EditText begintime=null;//开始流浪时间
    private EditText targetfamily=null;//目标家庭信息
    private EditText describe=null;//流浪者特征描述
    private EditText findaddress=null;//流浪者发现地址
    private EditText phonenmber=null;//发现者联系方式


    private String name=null;
    private String sex=null;
    private String age=null;
    private String time=null;
    private String family=null;
    private String feature=null;
    private String address=null;
    private String phone=null;
    private Vagrant vagrant;
    private TitleBar bar;
    //建立OkHttp连接
    private OkHttpClient okHttpClient;

    private boolean issignin=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
=======
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
>>>>>>> 31b9ce27f8c2458010e5d31bcf4d519845421bc3
        setContentView(R.layout.vagranthelp);

        findViews();
        //设置监听事件
        MyListener myListener=new MyListener();
        img_add.setOnClickListener(myListener);
        img_remove.setOnClickListener(myListener);
        btn_sumbit.setOnClickListener(myListener);
        vagrant_age.setOnClickListener(myListener);

        //监听EditText
        phonenmber.setOnFocusChangeListener(new View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                } else {
                    //验证手机号
                    isPhone();
                }
            }
        });
    }
    public void findViews(){
        img_add=findViewById(R.id.img_view);
        btn_sumbit=findViewById(R.id.btn_submit);
        addimg_view=findViewById(R.id.addimg_view);
        img_remove=findViewById(R.id.img_view2);
        //获取信息字段
        vagrant_name=findViewById(R.id.vagrant_name);
        vagrant_age=findViewById(R.id.vagrant_age);
        vagrant_sexwoman=findViewById(R.id.vagrant_sexwoman);
        begintime=findViewById(R.id.begintime_vagrant);
        targetfamily=findViewById(R.id.targetfamily_vagrant);
        describe=findViewById(R.id.describe_vagrant);
        findaddress=findViewById(R.id.findaddress);
        phonenmber=findViewById(R.id.phonenumber);
        bar = findViewById(R.id.bar);
        bar.setBackImageResource(R.drawable.back);
        bar.setUseRipple(true);
        }
    //验证输入的手机号的合法性
    public void isPhone(){
        String phoneNumber=phonenmber.getText().toString();
        TextView phonetip=findViewById(R.id.phonetip);
        Pattern pattern = Pattern.compile("((^(13|15|18)[0-9]{9}$)|(^0[1,2]{1}\\d{1}-?\\d{8}$)|(^0[3-9] {1}\\d{2}-?\\d{7,8}$)|(^0[1,2]{1}\\d{1}-?\\d{8}-(\\d{1,4})$)|(^0[3-9]{1}\\d{2}-?\\d{7,8}-(\\d{1,4})$))");
        Matcher matcher = pattern.matcher(phoneNumber);
        if(!matcher.matches()){
            phonetip.setText("!输入的手机号不合法");
        }
        else{
            phonetip.setText("格式正确！");
        }
    }
    //监听按钮事件
    private class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.img_view:
                    //动态申请权限
                    if(addcount==5){
                        Toast.makeText(VagrantHelp.this,"最多只能添加5张照片",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        addcount++;
                        ActivityCompat.requestPermissions(VagrantHelp.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);
                    }
                    break;
                case R.id.img_view2:
                    //删除图片
                    if(imgpaths.size()!=0){
                        removeImg(imageViews.get(--addimgId));
                    }
                    else{
                        Toast.makeText(VagrantHelp.this,"请先添加图片!",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.btn_submit:
                    //判断是否有空字段
                    getVagrantInformation();
                    if(!name.equals("")&&!age.equals("")&&!time.equals("")&&!family.equals("")&&!feature.equals("")&&!phone.equals("")&&!address.equals("")&!sex.equals("")){
                        if(imgpaths.size()!=0){
                            //显示弹窗
                            showPopupWindow(v);
                        }
                        else{
                            new AlertDialog.Builder(VagrantHelp.this)
                                    .setTitle("提示！")
                                    .setMessage("请上传至少一张照片！")
                                    .setPositiveButton("确定",null)
                                    .show();
                        }
                    }
                    else{
                        new AlertDialog.Builder(VagrantHelp.this)
                                .setTitle("提示！")
                                .setMessage("输入的信息中包含空字段，请您重新输入")
                                .setPositiveButton("确定",null)
                                .show();
                    }


                    break;

            }
        }
    }
    //获取流浪者信息
    public void getVagrantInformation(){
        name=vagrant_name.getText().toString();
        age=vagrant_age.getText().toString();
        time=begintime.getText().toString();
        family=targetfamily.getText().toString();
        feature=describe.getText().toString();
        phone=phonenmber.getText().toString();
        address=findaddress.getText().toString();
        if(vagrant_sexwoman.isChecked()){
            sex="女";
        }
        else{
            sex="男";
        }
        vagrant=new Vagrant(name,sex,age,address,time,family,feature,phone);
        Log.e("上传信息",new Gson().toJson(vagrant));
    }
    //用户允许权限
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==100){
            Intent intent=new Intent();
            intent.setAction(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent,200);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            String imgPath = null;
            if (cursor.moveToFirst()) {
                imgPath = cursor.getString(cursor.getColumnIndex("_data"));
                Log.e("imgPath", imgPath);
                imgpaths.add(imgPath);
                addImg();
            }
        }
    }
    //连续添加多张照片
    private void addImg(){
        ImageView imageView = new ImageView(this);  //创建imageview
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(20, 0, 0, 0);//4个参数按顺序分别是左上右下
        imageView.setId(addimgId);
        Log.e("addimgid",addimgId+"");
        imageViews.add(imageView);
        imageView.setLayoutParams(layoutParams);
        imageView.setImageResource(R.drawable.uploadimgtip);  //设置imageview呈现的图片
        addimg_view.addView(imageView);
        RequestOptions options = new RequestOptions().placeholder(R.drawable.uploadimgtip);
        Log.e("imgpath",imgpaths.get(addimgId));
        Glide.with(this)
                .load(imgpaths.get(addimgId))
                .apply(options)
                .into(imageView);
        addimgId++;
    }
    //从右至左依次删除图片
    private void removeImg(ImageView imageView){
        addimg_view.removeView(imageView);
        imageViews.remove(addimgId);
        imgpaths.remove(addimgId);
        addcount--;
    }
    //上传图片和信息至服务器
    public void uploadInformation(){
        //上传流浪者图片
        List<File>files=new ArrayList<>();
        List<byte[]>bytes=new ArrayList<>();
        for(int i=0;i<imgpaths.size();i++){
            File file=new File(imgpaths.get(i));
            files.add(file);
        }
        for(int i=0;i<files.size();i++){
            byte[]buffer=new byte[1024];
            int len=-1;
            try{
                InputStream is=new FileInputStream(files.get(i));
                ByteArrayOutputStream baos=new ByteArrayOutputStream();
                while ((len=is.read(buffer))!=-1) {
                    baos.write(buffer,0,len);
                }
                byte[]imgdata=baos.toByteArray();
                bytes.add(imgdata);
                baos.close();
                Log.e("不知道1","成功没有");
                is.close();
            }catch(FileNotFoundException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        //上传流浪者信息
        Gson gson=new Gson();
        String jsonStr=gson.toJson(bytes);
        String jsontextStr=gson.toJson(vagrant);
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"),
                jsonStr);
        //创建FormBody对象
        FormBody formBody=new FormBody.Builder()
                .add("image",jsonStr)
                .add("infor",jsontextStr)
                .build();
        Request request=new Request.Builder()
                .url(Constant.BASE_URL+"/AddVagrantServlet")
                .post(formBody)
                .build();
        Call call=okHttpClient.newCall(request);
        Log.e("不知道2","成功没有");
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("为啥失败呢",e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("返回信息:",response.body().string());

            }
        });
    }

    //从服务器判断是否为登录状态
    private void isLogin(){
        okHttpClient=new OkHttpClient();
        //创建FormBody对象
        FormBody formBody=new FormBody.Builder()
                .add("tip","判断登录")
                .build();
        Request request=new Request.Builder()
                .url(Constant.BASE_URL+"/IsLoginServlet")
                .post(formBody)
                .build();
        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String islogin=response.body().string();
                if(islogin.equals("已登录")){
                    issignin=true;
                }
                else{
                    issignin=false;
                }

            }
        });
    }

    //显示弹窗
    private void showPopupWindow(View v){
        View popupWindowView=getLayoutInflater().inflate(R.layout.popwindow_view,null);
        final PopupWindow popupWindow=new PopupWindow(popupWindowView, 1000, ActionBar.LayoutParams.WRAP_CONTENT, true);
        // 设置背景颜色变暗
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.7f;//调节透明度
        getWindow().setAttributes(lp);
        //dismiss时恢复原样
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        //引入依附的布局
        View parentView = LayoutInflater.from(VagrantHelp.this).inflate(R.layout.popwindow_view, null);
        //相对于父控件的位置（例如正中央Gravity.CENTER，下方Gravity.BOTTOM等），可以设置偏移或无偏移
        popupWindow.showAtLocation(parentView, Gravity.CENTER, 0, 0);
        //点击返回修改按钮
        Button btn_change=popupWindowView.findViewById( R.id.btn_turntochange);
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VagrantHelp.this,"返回修改",Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();

            }
        });
        //点击确认修改按钮，上传信息至服务器并跳转寻人大厅页面
        Button btn_suresubmit=popupWindowView.findViewById(R.id.btn_suresubmit);
        btn_suresubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VagrantHelp.this,"确认修改",Toast.LENGTH_SHORT).show();
                //将信息上传至服务器
                //获取流浪者信息
               getVagrantInformation();
                //将信息和图片上传至服务器
                okHttpClient=new OkHttpClient();
                uploadInformation();
                //跳转寻人大厅界面
               // Intent intent=new Intent(VagrantHelp.this,);
            }
        });

    }
}
