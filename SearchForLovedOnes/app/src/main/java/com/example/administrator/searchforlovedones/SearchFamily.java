package com.example.administrator.searchforlovedones;

import android.Manifest;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.Spinner;
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
import java.util.Calendar;
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

public class SearchFamily extends Activity {

    private Spinner syear=null;
    private Spinner smouth=null;
    private Spinner sday=null;
    private Spinner lyear=null;
    private Spinner lmouth=null;
    private Spinner lday=null;
    private ImageView img=null;

    private ImageView img_add=null;//添加图片按钮
    private Button btn_sumbit=null;
    private LinearLayout addimg_view=null;
    private int addimgId=0;//添加图片id
    private List<String> imgpaths=new ArrayList<>();//添加的图片地址
    private int addcount=0;//添加图片数量
    private ImageView img_remove=null;//删除图片按钮
    private List<ImageView>imageViews=new ArrayList<>();//新添加的图片布局

    private EditText l_name;//失踪者姓名
    private RadioButton l_sexwoman;

    private EditText l_phone;//联系方式
    private EditText l_email;//邮箱
    private EditText lheight;//失踪者失踪时大致身高

    private RadioButton yesBlood;//是否采血
    private RadioButton yesReport;//是否报案
    private EditText l_native;//失踪人籍贯
    private EditText l_missaddr;//失踪地点
    private EditText l_fearture;//失踪人特征描述
    private EditText l_process;//失踪经过
    private EditText l_family;//家庭背景及其线索资料
    private EditText t_familyaddr;//目标家庭地址
    private EditText t_relationfamily;//与目标家庭联系
    private EditText t_describefamily;//目标家庭描述

    private String lt_name;//失踪者姓名
    private String lt_sex;//失踪者性别
    private String lt_borndate;//失踪者出生日期
    private String lt_phone;//联系方式
    private String lt_email;//邮箱
    private String ltheight;//失踪者失踪时大致身高
    private String lt_missdate;//失踪日期
    private String isBlood;//是否采血
    private String isReport;//是否报案
    private String lt_native;//失踪人籍贯
    private String lt_missaddr;//失踪地点
    private String lt_fearture;//失踪人特征描述
    private String lt_process;//失踪经过
    private String lt_family;//家庭背景及其线索资料
    private String tt_familyaddr;//目标家庭地址
    private String tt_relationfamily;//与目标家庭联系
    private String tt_describefamily;//目标家庭描述

    private SearchFamilyBean searchFamilyBean;

    private int bornmouth=0;
    private int bornyear=0;
    private int bornday=0;

    private int missmouth=0;
    private int missyear=0;
    private int missday=0;
    private TitleBar bar;

    private OkHttpClient okHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchfamily);
        findViews();
        setSyear();
        setSday();

        bar.setBackImageResource(R.drawable.back);
        bar.setUseRipple(true);
        //绑定监听事件
        MyListener myListener=new MyListener();
        img_add.setOnClickListener(myListener);
        img_remove.setOnClickListener(myListener);
        btn_sumbit.setOnClickListener(myListener);
        //监听EditText
        l_phone.setOnFocusChangeListener(new View.
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
        l_email.setOnFocusChangeListener(new View.
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
        //设置年份月份日期下拉框
        syear=findViewById(R.id.year);
        smouth=findViewById(R.id.mouth);
        sday=findViewById(R.id.day);
        lyear=findViewById(R.id.loseyear);
        lmouth=findViewById(R.id.losemouth);
        lday=findViewById(R.id.loseday);
        addimg_view=findViewById(R.id.addimg_view);
        img_add=findViewById(R.id.img_view);
        img_remove=findViewById(R.id.img_view2);
        btn_sumbit=findViewById(R.id.btn_submit);
        //获取亲人寻家信息
        l_name=findViewById(R.id.l_name);
        l_sexwoman=findViewById(R.id.l_sexwoman);
        l_phone=findViewById(R.id.l_phone);
        l_email=findViewById(R.id.l_email);
        lheight=findViewById(R.id.l_height);
        yesBlood=findViewById(R.id.yesBlood);
        yesReport=findViewById(R.id.yesReport);
        l_native=findViewById(R.id.l_native);
        l_missaddr=findViewById(R.id.l_missaddr);
        l_fearture=findViewById(R.id.l_feature);
        l_process=findViewById(R.id.l_process);
        l_family=findViewById(R.id.l_family);
        t_familyaddr=findViewById(R.id.t_familyaddr);
        t_relationfamily=findViewById(R.id.t_relationfamily);
        t_describefamily=findViewById(R.id.describefamily);
        bar = findViewById(R.id.bar);
    }
    //获取亲人寻家的信息
    public void getInformation(){
        lt_name=l_name.getText().toString();
        if(l_sexwoman.isChecked()){
            lt_sex="女";
        }
        else{
            lt_sex="男";
        }
        lt_borndate=bornyear+"年"+bornmouth+"月"+bornday+"天";
        lt_phone=l_phone.getText().toString();
        lt_email=l_email.getText().toString();
        ltheight=lheight.getText().toString();
        lt_missdate=missyear+"年"+missmouth+"月"+missday+"天";
        if(yesBlood.isChecked()){
            isBlood="是";
        }
        else{
            isBlood="否";
        }
        if(yesReport.isChecked()){
            isReport="是";
        }
        else{
            isReport="否";
        }
        lt_native=l_native.getText().toString();
        lt_missaddr=l_missaddr.getText().toString();
        lt_fearture=l_fearture.getText().toString();
        lt_process=l_process.getText().toString();
        lt_family=l_family.getText().toString();
        tt_familyaddr=t_familyaddr.getText().toString();
        tt_relationfamily=t_relationfamily.getText().toString();
        tt_describefamily=t_describefamily.getText().toString();
        searchFamilyBean=new SearchFamilyBean(lt_name,lt_sex,lt_borndate,lt_phone,lt_email,ltheight,lt_missdate,isBlood,isReport,lt_native,lt_missaddr,lt_fearture,lt_process,lt_family,tt_familyaddr,tt_relationfamily,tt_describefamily);

    }
     //验证手机号是否合法
    public void isPhone(){
        TextView phonetip=findViewById(R.id.phonetip);
        String phoneNumber=l_phone.getText().toString();
        String tag="((^(13|15|18)[0-9]{9}$)|(^0[1,2]{1}\\d{1}-?\\d{8}$)|(^0[3-9] {1}\\d{2}-?\\d{7,8}$)|(^0[1,2]{1}\\d{1}-?\\d{8}-(\\d{1,4})$)|(^0[3-9]{1}\\d{2}-?\\d{7,8}-(\\d{1,4})$))";
        Pattern pattern = Pattern.compile(tag);
        Matcher matcher = pattern.matcher(phoneNumber);
        if(!matcher.matches()){
           phonetip.setText("!输入的手机号格式不正确");
        }
        else{
            phonetip.setText("格式正确!");
        }
    }
    //验证邮箱是否合法
    public void isEmail(){
        TextView emailtip=findViewById(R.id.emailtip);
        String email=l_email.getText().toString();
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
                        Toast.makeText(SearchFamily.this,"最多只能添加5张照片",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        addcount++;
                        ActivityCompat.requestPermissions(SearchFamily.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);
                    }
                    break;
                case R.id.img_view2:
                    //删除图片
                    if(imgpaths.size()!=0){
                        removeImg(imageViews.get(--addimgId));
                    }
                    else{
                        Toast.makeText(SearchFamily.this,"请先添加图片!",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.btn_submit:
                    getInformation();
                    //如果有空字段
                    if(!lt_name.equals("")&&!lt_sex.equals("")&&!lt_borndate.equals("")&&!lt_phone.equals("")&&!lt_email.equals("")&&!ltheight.equals("")
                    &&!lt_missdate.equals("")&&!isBlood.equals("")&&!isReport.equals("")&&!lt_native.equals("")&&!lt_missaddr.equals("")&&!lt_fearture.equals("")
                    &&!lt_process.equals("")&&!lt_family.equals("")&&!tt_familyaddr.equals("")&&!tt_relationfamily.equals("")&&!tt_describefamily.equals("")){
                        //显示弹窗
                        showPopupWindow(v);
                    }
                    else{
                        new AlertDialog.Builder(SearchFamily.this)
                                .setTitle("提示！")
                                .setMessage("输入的信息中包含空字段，请您重新输入。")
                                .setPositiveButton("确定",null)
                                .show();
                    }
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
            String imgPsth=null;
            if(cursor.moveToFirst()){
                String imgPath=cursor.getString(cursor.getColumnIndex("_data"));
                Log.e("imgPath",imgPath);
                imgpaths.add(imgPath);
                addImg();
            }
            //将图片上传至服务器
        }
    }

    //获取下拉框年份数据
    private void setSyear(){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR) ;
        List<Integer> list=new ArrayList<Integer>();
        for(int i=1940;i<=year;i++){
            list.add(i);
        }
        Integer[] years=new Integer[list.size()];
        for(int j=0;j<list.size();j++){
            years[j]=list.get(j);
        }
        ArrayAdapter<Integer>arrayAdapter=new ArrayAdapter<Integer>(SearchFamily.this, R.layout.support_simple_spinner_dropdown_item,years);
        syear.setAdapter(arrayAdapter);
        lyear.setAdapter(arrayAdapter);
    }
    //获取下拉框日期数据
    private void setSday(){
        final Integer[]days1=new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
        final Integer[]days2=new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
        final Integer[]days3=new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29};
        final Integer[]days4=new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28};
        //获取出生选中的月份
        smouth.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String mnumber= SearchFamily.this.getResources().getStringArray(R.array.month)[position];
                Log.e("month",mnumber);
                bornmouth=Integer.parseInt(mnumber);
                String ynumber= syear.getSelectedItem().toString();
                bornyear=Integer.parseInt(ynumber);
                if(bornmouth==1||bornmouth==3||bornmouth==5||bornmouth==7||bornmouth==8||bornmouth==10||bornmouth==12){
                    ArrayAdapter<Integer>arrayAdapter=new ArrayAdapter<Integer>(SearchFamily.this, R.layout.support_simple_spinner_dropdown_item,days1);
                    sday.setAdapter(arrayAdapter);
                }
                else if(bornmouth==2){
                    if((bornyear%4==0&&bornyear%100!=0)||bornyear%400==0){
                        //今年是闰年
                        ArrayAdapter<Integer>arrayAdapter=new ArrayAdapter<Integer>(SearchFamily.this, R.layout.support_simple_spinner_dropdown_item,days3);
                        sday.setAdapter(arrayAdapter);
                    }
                    else{
                        //今年是平年
                        ArrayAdapter<Integer>arrayAdapter=new ArrayAdapter<Integer>(SearchFamily.this, R.layout.support_simple_spinner_dropdown_item,days4);
                        sday.setAdapter(arrayAdapter);
                    }
                }
                else{
                    ArrayAdapter<Integer>arrayAdapter=new ArrayAdapter<Integer>(SearchFamily.this, R.layout.support_simple_spinner_dropdown_item,days2);
                    sday.setAdapter(arrayAdapter);
                }
            }
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        //获取出生的日期
        sday.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String dnumber= sday.getSelectedItem().toString();
                bornday=Integer.parseInt(dnumber);
            }
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        //获取失踪月份
        lmouth.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String mnumber= SearchFamily.this.getResources().getStringArray(R.array.month)[position];
                Log.e("month",mnumber);
                missmouth=Integer.parseInt(mnumber);
                String ynumber= lyear.getSelectedItem().toString();
                missyear=Integer.parseInt(ynumber);
                if(missmouth==1||missmouth==3||missmouth==5||missmouth==7||missmouth==8||missmouth==10||missmouth==12){
                    ArrayAdapter<Integer>arrayAdapter=new ArrayAdapter<Integer>(SearchFamily.this, R.layout.support_simple_spinner_dropdown_item,days1);
                    lday.setAdapter(arrayAdapter);
                }
                else if(missmouth==2){
                    if((missyear%4==0&&missyear%100!=0)||missyear%400==0){
                        //今年是闰年
                        ArrayAdapter<Integer>arrayAdapter=new ArrayAdapter<Integer>(SearchFamily.this, R.layout.support_simple_spinner_dropdown_item,days3);
                        lday.setAdapter(arrayAdapter);
                    }
                    else{
                        //今年是平年
                        ArrayAdapter<Integer>arrayAdapter=new ArrayAdapter<Integer>(SearchFamily.this, R.layout.support_simple_spinner_dropdown_item,days4);
                        lday.setAdapter(arrayAdapter);
                    }
                }
                else{
                    ArrayAdapter<Integer>arrayAdapter=new ArrayAdapter<Integer>(SearchFamily.this, R.layout.support_simple_spinner_dropdown_item,days2);
                    lday.setAdapter(arrayAdapter);
                }
            }
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        //获取失踪的日期
        lday.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String dnumber= lday.getSelectedItem().toString();
                missday=Integer.parseInt(dnumber);
            }
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
    }

    //连续添加多张照片
    private void addImg(){
        ImageView imageView = new ImageView(this);  //创建imageview
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(20, 0, 0, 0);//4个参数按顺序分别是左上右下
        imageView.setId(addimgId);
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
        //上传亲人寻家图片
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
        String jsontextStr=gson.toJson(searchFamilyBean);
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"),
                jsonStr);
        //创建FormBody对象
        FormBody formBody=new FormBody.Builder()
                .add("image",jsonStr)
                .add("infor",jsontextStr)
                .build();
        Request request=new Request.Builder()
                .url(Constant.BASE_URL+"AddSearchFamilyServlet")
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
        View parentView = LayoutInflater.from(SearchFamily.this).inflate(R.layout.popwindow_view, null);
        //相对于父控件的位置（例如正中央Gravity.CENTER，下方Gravity.BOTTOM等），可以设置偏移或无偏移
        popupWindow.showAtLocation(parentView, Gravity.CENTER, 0, 0);
        //点击返回修改按钮
        Button btn_change=popupWindowView.findViewById( R.id.btn_turntochange);
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SearchFamily.this,"返回修改",Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();

            }
        });
        //点击确认修改按钮，上传信息至服务器并跳转寻人大厅页面
        Button btn_suresubmit=popupWindowView.findViewById(R.id.btn_suresubmit);
        btn_suresubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SearchFamily.this,"确认修改",Toast.LENGTH_SHORT).show();
                //将信息上传至服务器
                //获取信息
                getInformation();
                //将信息和图片上传至服务器
                okHttpClient=new OkHttpClient();
                uploadInformation();
            }
        });

    }


}

