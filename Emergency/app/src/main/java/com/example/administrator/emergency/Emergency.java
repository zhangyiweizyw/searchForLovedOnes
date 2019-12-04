package com.example.administrator.emergency;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Emergency extends AppCompatActivity{
    private OkHttpClient okHttpClient;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency_view);
        okHttpClient = new OkHttpClient();
//        emergencySearch();

    }
    public void uploadInformation(){
//        //上传图片
//        List<File> files=new ArrayList<>();
//        List<byte[]>bytes=new ArrayList<>();
//        for(int i=0;i<imgpaths.size();i++){
//            File file=new File(imgpaths.get(0));
//            files.add(file);
//        }
//        for(int i=0;i<files.size();i++){
//            byte[]buffer=new byte[1024];
//            int len=-1;
//            try{
//                InputStream is=new FileInputStream(files.get(i));
//                ByteArrayOutputStream baos=new ByteArrayOutputStream();
//                while ((len=is.read(buffer))!=-1) {
//                    baos.write(buffer,0,len);
//                }
//                byte[]imgdata=baos.toByteArray();
//                bytes.add(imgdata);
//                baos.close();
//                is.close();
//            }catch(FileNotFoundException e){
//                e.printStackTrace();
//            }catch(IOException e){
//                e.printStackTrace();
//            }
//        }
//        for(int i=0;i<bytes.size();i++){
//            Log.e("byte"+i,bytes.get(i).toString());
//        }
        Log.e("byteslength","BL");
        Gson gson=new Gson();
        String jsonStr=gson.toJson("BL");
        Log.e("jsonStr",jsonStr);
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"),
                jsonStr);
        Request request=new Request.Builder()
                .url(Constant.ADDRESS+"AddVagrantServlet")
                .post(body)
                .build();
        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("返回信息:",response.body().string());

            }
        });
    }

    public void emergencySearch(){
        //TODO:获取用户登入的紧急寻人信息，传到服务器端，存入紧急数据表，首页置顶显示12h，平时轮着放入数据库

//        Request request = new Request.Builder()
//                .url(Constant.ADDRESS+"ReceiveData")
//                .post()
//                .build();
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//
//                String resp = response.body().string();
//                Log.e("str",resp);
//            }
//        });
    }
}
