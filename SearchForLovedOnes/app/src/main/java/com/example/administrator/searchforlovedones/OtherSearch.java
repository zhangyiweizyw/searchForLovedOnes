package com.example.administrator.searchforlovedones;

import android.Manifest;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.system.Os;
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
import com.loper7.layout.TitleBar;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OtherSearch extends Activity {

    private ImageView img_add=null;//添加图片按钮
    private Button btn_sumbit=null;
    private LinearLayout addimg_view=null;
    private int addimgId=0;//添加图片id
    private List<String> imgpaths=new ArrayList<>();//添加图片路径
    private int addcount=0;//添加图片数量
    private ImageView img_remove=null;//删除图片按钮
    private List<ImageView>imageViews=new ArrayList<>();//新添加的图片布局

    private EditText s_name=null;//被寻者姓名
    private RadioButton s_sexwoman=null;//被寻者性别
    private EditText s_reason=null;//寻人原因及其他线索资料
    private EditText relation=null;//与被寻者联系
    private EditText y_name=null;//寻人者姓名
    private RadioButton y_sexwoman=null;//寻人者性别
    private EditText y_age=null;//寻人者年龄
    private EditText y_email=null;//寻人者邮箱
    private EditText y_phone=null;//寻人者电话
    private EditText y_address=null;//寻人者住址

    private String st_name=null;//被寻者姓名
    private String st_sex=null;//被寻者性别
    private String st_reason=null;//寻人原因及其他线索资料
    private String syrelation=null;//与被寻者联系
    private String yt_name=null;//寻人者姓名
    private String yt_sex=null;//寻人者性别
    private int yt_age=0;//寻人者年龄
    private String yt_email=null;//寻人者邮箱
    private String yt_phone=null;//寻人者电话
    private String yt_address=null;//寻人者住址
    private TitleBar bar;
    private OtherSearchBean otherSearchBean;
    private OkHttpClient okHttpClient;

    private int userId=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.othersearch);

        Intent intent = getIntent();
        if(null!=intent.getStringExtra("userId")){
            userId = Integer.parseInt(intent.getStringExtra("userId"));
        }else{
            userId = -1;
        }
        findViews();

        //设置监听事件
        MyListener myListener=new MyListener();
        img_add.setOnClickListener(myListener);
        img_remove.setOnClickListener(myListener);
        btn_sumbit.setOnClickListener(myListener);

        //监听EditText
        y_phone.setOnFocusChangeListener(new View.
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
        y_email.setOnFocusChangeListener(new View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                } else {
                    //验证邮箱
                    isEmail();
                }
            }
        });
    }
    public void findViews(){
        img_add=findViewById(R.id.img_view);
        addimg_view=findViewById(R.id.addimg_view);
        img_remove=findViewById(R.id.img_view2);
        btn_sumbit=findViewById(R.id.btn_submit);
        //获取寻人信息
        s_name=findViewById(R.id.s_name);
        s_sexwoman=findViewById(R.id.s_sexwoman);
        s_reason=findViewById(R.id.s_reason);
        relation=findViewById(R.id.s_relationship);
        y_name=findViewById(R.id.y_name);
        y_sexwoman=findViewById(R.id.y_sexwoman);
        y_age=findViewById(R.id.y_age);
        y_email=findViewById(R.id.y_email);
        y_phone=findViewById(R.id.y_phone);
        y_address=findViewById(R.id.y_address);
        bar = findViewById(R.id.bar);
        bar.setBackImageResource(R.drawable.back);
        bar.setUseRipple(true);
    }
    //获取寻人者和被寻者信息
    public void getInformation(){
        st_name=s_name.getText().toString();
        if(s_sexwoman.isChecked()){
            st_sex="女";
        }
        else{
            st_sex="男";
        }
        st_reason=s_reason.getText().toString();
        syrelation=relation.getText().toString();
        yt_name=y_name.getText().toString();
        if(y_sexwoman.isChecked()){
            yt_sex="女";
        }
        else{
            yt_sex="男";
        }
       // yt_age=Integer.parseInt(y_age.getText().toString());
        yt_email=y_email.getText().toString();
        yt_phone=y_phone.getText().toString();
        yt_address=y_address.getText().toString();
        //otherSearchBean=new OtherSearchBean(st_name,st_sex,st_reason,syrelation,yt_name,yt_sex,yt_age,yt_email,yt_phone,yt_address);

    }
    //验证手机号是否合法
    public void isPhone(){
        TextView phonetip=findViewById(R.id.phonetip);
        String phoneNumber=y_phone.getText().toString();
        Pattern pattern = Pattern.compile("((^(13|15|18)[0-9]{9}$)|(^0[1,2]{1}\\d{1}-?\\d{8}$)|(^0[3-9] {1}\\d{2}-?\\d{7,8}$)|(^0[1,2]{1}\\d{1}-?\\d{8}-(\\d{1,4})$)|(^0[3-9]{1}\\d{2}-?\\d{7,8}-(\\d{1,4})$))");
        Matcher matcher = pattern.matcher(phoneNumber);
        if(!matcher.matches()){
            phonetip.setText("!输入的手机号不合法");
        }
        else{
            phonetip.setText("格式正确！");
        }
    }
    //验证邮箱是否合法
    public void isEmail(){
        TextView emailtip=findViewById(R.id.emailtip);
        String email=y_email.getText().toString();
        String tag="^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern pattern=Pattern.compile(tag);
        Matcher matcher=pattern.matcher(email);
        if(!matcher.matches()){
            emailtip.setText("!输入的邮箱格式不正确");
        }
        else{
            emailtip.setText("格式正确!");
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
                        Toast.makeText(OtherSearch.this,"最多只能添加5张照片",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        addcount++;
                        ActivityCompat.requestPermissions(OtherSearch.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);
                    }
                    break;
                case R.id.img_view2:
                    //删除图片
                    if(imgpaths.size()!=0){
                        removeImg(imageViews.get(--addimgId));
                    }
                    else{
                        Toast.makeText(OtherSearch.this,"请先添加图片!",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.btn_submit:
                   //判断是否为登陆状态
                    isLogin(v);
                    break;
            }
        }
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
        if(requestCode==200&&resultCode==RESULT_OK){
            Uri uri=data.getData();
            Cursor cursor=getContentResolver().query(uri,null,null,null,null);
            String imgPath=null;
            if(cursor.moveToFirst()){
                imgPath=cursor.getString(cursor.getColumnIndex("_data"));
                Log.e("imgPath",imgPath);
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
        imageViews.add(imageView);
        imageView.setLayoutParams(layoutParams);
        imageView.setImageResource(R.drawable.uploadimgtip);  //设置imageview呈现的图片
        addimg_view.addView(imageView);
        RequestOptions options = new RequestOptions().placeholder(R.drawable.uploadimgtip);
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
        String jsontextStr=gson.toJson(otherSearchBean);
        String jsonid = gson.toJson(userId);
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"),
                jsonStr);
        //创建FormBody对象
        FormBody formBody=new FormBody.Builder()
                .add("image",jsonStr)
                .add("infor",jsontextStr)
                .add("userid", jsonid)
                .build();
        Request request=new Request.Builder()
                .url(Constant.BASE_URL+"/AddOtherSearchServlet")
                .post(formBody)
                .build();
        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("返回信息:",response.body().string());
                //跳转首页界面
                finish();

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
        View parentView = LayoutInflater.from(OtherSearch.this).inflate(R.layout.popwindow_view, null);
        //相对于父控件的位置（例如正中央Gravity.CENTER，下方Gravity.BOTTOM等），可以设置偏移或无偏移
        popupWindow.showAtLocation(parentView, Gravity.CENTER, 0, 0);
        //点击返回修改按钮
        Button btn_change=popupWindowView.findViewById( R.id.btn_turntochange);
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OtherSearch.this,"返回修改",Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();

            }
        });
        //点击确认修改按钮，上传信息至服务器并跳转寻人大厅页面
        Button btn_suresubmit=popupWindowView.findViewById(R.id.btn_suresubmit);
        btn_suresubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OtherSearch.this,"确认修改",Toast.LENGTH_SHORT).show();
                //将信息上传至服务器
                //获取信息
                getInformation();
                yt_age=Integer.parseInt(y_age.getText().toString());
                otherSearchBean=new OtherSearchBean(st_name,st_sex,st_reason,syrelation,yt_name,yt_sex,yt_age,yt_email,yt_phone,yt_address);
                //将信息和图片上传至服务器
                okHttpClient=new OkHttpClient();
                uploadInformation();

            }
        });

    }

    //从服务器判断是否为登录状态
    private void isLogin(View v){
        if(userId==-1){
            new AlertDialog.Builder(OtherSearch.this)
                    .setTitle("提示！")
                    .setMessage("请先登录在进行操作！")
                    .setPositiveButton("确定",null)
                    .show();
        }
        else{
            judgeNull(v);
        }
    }
    public void judgeNull(View v){
        getInformation();
        if(!st_name.equals("")&&!st_sex.equals("")&&!st_reason.equals("")&&!syrelation.equals("")&&!yt_name.equals("")
                &&!yt_sex.equals("")&&!y_age.getText().toString().equals("")&&!yt_email.equals("")&&!yt_phone.equals("")
                &&!yt_address.equals("")){
            if(imgpaths.size()!=0){
                //显示弹窗
                showPopupWindow(v);
            }
            else{
                new AlertDialog.Builder(OtherSearch.this)
                        .setTitle("提示！")
                        .setMessage("请上传至少一张照片！")
                        .setPositiveButton("确定",null)
                        .show();
            }
        }
        else{
            new AlertDialog.Builder(OtherSearch.this)
                    .setTitle("提示！")
                    .setMessage("输入的信息中包含空字段，请您重新输入")
                    .setPositiveButton("确定",null)
                    .show();
        }
    }
}
