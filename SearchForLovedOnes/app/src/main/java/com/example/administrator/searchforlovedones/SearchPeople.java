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

public class SearchPeople extends Activity {

    private Spinner syear=null;
    private Spinner smouth=null;
    private Spinner sday=null;
    private Spinner lyear=null;
    private Spinner lmouth=null;
    private Spinner lday=null;

    private EditText m_name;
    private RadioButton m_sexwoman;
    private EditText height;
    private RadioButton yesBlood;
    private RadioButton yesReport;
    private EditText m_native;
    private EditText m_missadd;
    private EditText m_feature;
    private EditText m_process;
    private EditText m_family;
    private EditText y_name;
    private EditText y_phone;
    private EditText y_email;
    private EditText y_address;
    private EditText y_relation;
    private String mt_name;//失踪者姓名
    private String mt_sex;//失踪者性别
    private String mt_borndate;//失踪者出生日期
    private String mtheight;//失踪者失踪时大致身高
    private String mt_missdate;//失踪日期
    private String isBlood;//是否采血
    private String isReport;//是否报案
    private String mt_native;//失踪人籍贯
    private String mt_missadd;//失踪地点
    private String mt_fearture;//失踪人特征描述
    private String mt_process;//失踪经过
    private String mt_family;//家庭背景及其线索资料
    private String yt_name;//联系人姓名
    private String yt_phone;//联系人手机
    private String yt_email;//联系人邮箱
    private String yt_address;//联系人现住址
    private String yt_relation;//联系人与失踪者联系

    private SearchPeopleBean searchPeopleBean;

    private ImageView img_add=null;//添加图片按钮
    private Button btn_sumbit=null;
    private LinearLayout addimg_view=null;
    private int addimgId=0;//添加图片id
    private List<String> imgpaths=new ArrayList<>();//添加的图片地址
    private int addcount=0;//添加图片数量
    private ImageView img_remove=null;//删除图片按钮
    private List<ImageView>imageViews=new ArrayList<>();//新添加的图片布局

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
        setContentView(R.layout.searchpeople);
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
        //获取家寻亲人信息
        m_name=findViewById(R.id.m_name);
        m_sexwoman=findViewById(R.id.m_sexwoman);
        height=findViewById(R.id.m_height);
        yesBlood=findViewById(R.id.yesBlood);
        yesReport=findViewById(R.id.yesReport);
        m_native=findViewById(R.id.m_native);
        m_missadd=findViewById(R.id.m_missaddr);
        m_feature=findViewById(R.id.m_feature);
        m_process=findViewById(R.id.m_process);
        m_family=findViewById(R.id.m_family);
        y_name=findViewById(R.id.y_name);
        y_phone=findViewById(R.id.y_phone);
        y_email=findViewById(R.id.y_email);
        y_address=findViewById(R.id.y_address);
        y_relation=findViewById(R.id.relation);
        bar = findViewById(R.id.bar);
    }
    //获取家寻亲人信息
    public void getInformation(){
         mt_name=m_name.getText().toString();
        if(m_sexwoman.isChecked()){
            mt_sex="女";
        }
        else{
            mt_sex="男";
        }
         mt_borndate=bornyear+"年"+bornmouth+"月"+bornday+"日";
         mtheight=height.getText().toString();
         mt_missdate=missyear+"年"+missmouth+"月"+missday+"日";
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
         mt_native=m_native.getText().toString();
         mt_missadd=m_missadd.getText().toString();
         mt_fearture=m_feature.getText().toString();
         mt_process=m_process.getText().toString();
         mt_family=m_family.getText().toString();
         yt_name=y_name.getText().toString();
         yt_phone=y_phone.getText().toString();
         yt_email=y_email.getText().toString();
         yt_address=y_address.getText().toString();
         yt_relation=y_relation.getText().toString();
         searchPeopleBean=new SearchPeopleBean(mt_name,mt_sex,mt_borndate,mtheight,mt_missdate,isBlood,isReport,mt_native,mt_missadd,mt_fearture,mt_process,mt_family,yt_name,yt_phone,yt_email,yt_address,yt_relation);
    }

    //验证手机号是否合法
    public void isPhone(){
        TextView phonetip=findViewById(R.id.phonetip);
        String phoneNumber=y_phone.getText().toString();
        String tag="((^(13|15|18)[0-9]{9}$)|(^0[1,2]{1}\\d{1}-?\\d{8}$)|(^0[3-9] {1}\\d{2}-?\\d{7,8}$)|(^0[1,2]{1}\\d{1}-?\\d{8}-(\\d{1,4})$)|(^0[3-9]{1}\\d{2}-?\\d{7,8}-(\\d{1,4})$))";
        Pattern pattern = Pattern.compile(tag);
        Matcher matcher = pattern.matcher(phoneNumber);
        if(!matcher.matches()){
            phonetip.setText("!输入的手机号不合法");
        }
        else{
            phonetip.setText("格式正确!");
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
                        Toast.makeText(SearchPeople.this,"最多只能添加5张照片",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        addcount++;
                        ActivityCompat.requestPermissions(SearchPeople.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);
                    }
                    break;
                case R.id.img_view2:
                    //删除图片
                    if(imgpaths.size()!=0){
                        removeImg(imageViews.get(--addimgId));
                    }
                    else{
                        Toast.makeText(SearchPeople.this,"请先添加图片！",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.btn_submit:
                    getInformation();
                    //如果没有空字段
                    if(!mt_name.equals("")&&!mt_sex.equals("")&&!mtheight.equals("")&&!isBlood.equals("")
                        &&!isReport.equals("")&&!mt_native.equals("")&&!mt_missadd.equals("")&&!mt_fearture.equals("")
                            &&!mt_process.equals("")&&!mt_family.equals("")&&!yt_name.equals("")&&!yt_phone.equals("")
                            &&!yt_email.equals("")&&!yt_address.equals("")&&!yt_relation.equals("")){
                        //显示弹窗
                        showPopupWindow(v);
                    }
                    else{
                        new AlertDialog.Builder(SearchPeople.this)
                                .setTitle("提示！")
                                .setMessage("输入的信息中包含空字段，请重新输入。")
                                .setPositiveButton("确定",null)
                                .show();
                    }

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
        ArrayAdapter<Integer>arrayAdapter=new ArrayAdapter<Integer>(SearchPeople.this, R.layout.support_simple_spinner_dropdown_item,years);
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
                String mnumber= SearchPeople.this.getResources().getStringArray(R.array.month)[position];
                Log.e("month",mnumber);
                bornmouth=Integer.parseInt(mnumber);
                String ynumber= syear.getSelectedItem().toString();
                bornyear=Integer.parseInt(ynumber);
                if(bornmouth==1||bornmouth==3||bornmouth==5||bornmouth==7||bornmouth==8||bornmouth==10||bornmouth==12){
                    ArrayAdapter<Integer>arrayAdapter=new ArrayAdapter<Integer>(SearchPeople.this, R.layout.support_simple_spinner_dropdown_item,days1);
                    sday.setAdapter(arrayAdapter);
                }
                else if(bornmouth==2){
                    if((bornyear%4==0&&bornyear%100!=0)||bornyear%400==0){
                        //今年是闰年
                        ArrayAdapter<Integer>arrayAdapter=new ArrayAdapter<Integer>(SearchPeople.this, R.layout.support_simple_spinner_dropdown_item,days3);
                        sday.setAdapter(arrayAdapter);
                    }
                    else{
                        //今年是平年
                        ArrayAdapter<Integer>arrayAdapter=new ArrayAdapter<Integer>(SearchPeople.this, R.layout.support_simple_spinner_dropdown_item,days4);
                        sday.setAdapter(arrayAdapter);
                    }
                }
                else{
                    ArrayAdapter<Integer>arrayAdapter=new ArrayAdapter<Integer>(SearchPeople.this, R.layout.support_simple_spinner_dropdown_item,days2);
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
                String mnumber= SearchPeople.this.getResources().getStringArray(R.array.month)[position];
                Log.e("month",mnumber);
                missmouth=Integer.parseInt(mnumber);
                String ynumber= lyear.getSelectedItem().toString();
                missyear=Integer.parseInt(ynumber);
                if(missmouth==1||missmouth==3||missmouth==5||missmouth==7||missmouth==8||missmouth==10||missmouth==12){
                    ArrayAdapter<Integer>arrayAdapter=new ArrayAdapter<Integer>(SearchPeople.this, R.layout.support_simple_spinner_dropdown_item,days1);
                    lday.setAdapter(arrayAdapter);
                }
                else if(missmouth==2){
                    if((missyear%4==0&&missyear%100!=0)||missyear%400==0){
                        //今年是闰年
                        ArrayAdapter<Integer>arrayAdapter=new ArrayAdapter<Integer>(SearchPeople.this, R.layout.support_simple_spinner_dropdown_item,days3);
                        lday.setAdapter(arrayAdapter);
                    }
                    else{
                        //今年是平年
                        ArrayAdapter<Integer>arrayAdapter=new ArrayAdapter<Integer>(SearchPeople.this, R.layout.support_simple_spinner_dropdown_item,days4);
                        lday.setAdapter(arrayAdapter);
                    }
                }
                else{
                    ArrayAdapter<Integer>arrayAdapter=new ArrayAdapter<Integer>(SearchPeople.this, R.layout.support_simple_spinner_dropdown_item,days2);
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
    //删除照片
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
        //上传家寻亲人信息
        Gson gson=new Gson();
        String jsonStr=gson.toJson(bytes);
        String jsontextStr=gson.toJson(searchPeopleBean);
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"),
                jsonStr);
        //创建FormBody对象
        FormBody formBody=new FormBody.Builder()
                .add("image",jsonStr)
                .add("infor",jsontextStr)
                .build();
        Request request=new Request.Builder()
                .url(Constant.BASE_URL+"/AddSearchPeopleServlet")
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
        View parentView = LayoutInflater.from(SearchPeople.this).inflate(R.layout.popwindow_view, null);
        //相对于父控件的位置（例如正中央Gravity.CENTER，下方Gravity.BOTTOM等），可以设置偏移或无偏移
        popupWindow.showAtLocation(parentView, Gravity.CENTER, 0, 0);
        //点击返回修改按钮
        Button btn_change=popupWindowView.findViewById( R.id.btn_turntochange);
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SearchPeople.this,"返回修改",Toast.LENGTH_SHORT).show();
                popupWindow.dismiss();

            }
        });
        //点击确认修改按钮，上传信息至服务器并跳转寻人大厅页面
        Button btn_suresubmit=popupWindowView.findViewById(R.id.btn_suresubmit);
        btn_suresubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SearchPeople.this,"确认修改",Toast.LENGTH_SHORT).show();
                //将信息上传至服务器
                //获取信息
                getInformation();
                //将信息和图片上传至服务器
                okHttpClient=new OkHttpClient();
                uploadInformation();
                //跳转寻人大厅
                Intent intent=new Intent(SearchPeople.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }


}
