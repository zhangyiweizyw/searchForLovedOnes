package com.example.administrator.searchforlovedones;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.searchforlovedones.PullScrollView;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;


public class IndivdualCenter extends Fragment implements PullScrollView.OnTurnListener{
    private Gson gson;
    private TextView username1;
    private TextView username;
    private TextView usertype;
    private TextView tel;
    private TextView email;
    private TextView email1;
    private ImageView release;
    private ImageView cpwd;
    private User user = new User();
    private String type;//type为1 是修改用户名、2是修改手机、3是修改邮箱
    private View page;

    private TextView bt_login;

    private PullScrollView mScrollView;
    private ImageView mHeadImg;
    private ImageView user_avatar;
    private OkHttpClient okHttpClient;
//头像
    private PhotoPopupWindow mPhotoPopupWindow;
    private static final int REQUEST_IMAGE_GET = 0;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_SMALL_IMAGE_CUTTING = 2;
    private static final int REQUEST_BIG_IMAGE_CUTTING = 3;
    private static final String IMAGE_FILE_NAME = "icon.jpg";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        page = inflater.inflate(R.layout.fragment_page4, container, false);
        page.setBackgroundColor(Color.WHITE);
        gson = new Gson();
        //1.创建OkHttpClient对象
        okHttpClient = new OkHttpClient();
        username = page.findViewById(R.id.idv_name);
        tel = page.findViewById(R.id.idv_tel);
        email = page.findViewById(R.id.idv_email);
        release = page.findViewById(R.id.idv_release);
        cpwd = page.findViewById(R.id.idv_cpwd);

        username1=page.findViewById(R.id.idv_name1);
        email1=page.findViewById(R.id.idv_email1);
        usertype=page.findViewById(R.id.idv_usertype);

        bt_login=page.findViewById(R.id.idv_login);
        mScrollView = (PullScrollView)page. findViewById(R.id.scroll_view);
        mHeadImg = (ImageView) page.findViewById(R.id.background_img);
        mScrollView.setHeader(mHeadImg);
        mScrollView.setOnTurnListener(this);

        user_avatar=page.findViewById(R.id.user_avatar);

        if (MainActivity.userId == -1) {
            user.setId(MainActivity.userId);
                username1.setText("请先登录");
                bt_login.setText("登录");
                bt_login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), Load.class);
                        startActivity(intent);
                    }
                });
        } else {
            user.setId(MainActivity.userId);
            getUser();
            bt_login.setText("注销");
            bt_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final AlertDialog.Builder normalDialog =
                            new AlertDialog.Builder(v.getContext());
                    normalDialog.setTitle("注销");
                    normalDialog.setMessage("您确定要退出登录吗?");
                    normalDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            MainActivity.userId = -1;
                            getActivity().getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.idv_idv, new IndivdualCenter(), null)
                                    .commit();

                            Toast toast = Toast.makeText(getContext(), "您已退出登录", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();

                        }
                    });
                    normalDialog.setNegativeButton("关闭",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    normalDialog.show();

                }
            });


            username.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final EditText et_name = new EditText(v.getContext());
                    new AlertDialog.Builder(v.getContext()).setTitle("请输入修改的用户名")
                            .setIcon(R.mipmap.head)
                            .setView(et_name)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //按下确定键后的事件
                                    user.setUserName(et_name.getText().toString());
                                    type = "1";
                                    getValues();
                                    username.setText(user.getUserName());
                                    username1.setText(user.getUserName());
                                }
                            }).setNegativeButton("取消", null).show();
                }
            });

            //修改手机
            tel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final EditText et_name = new EditText(v.getContext());
                    new AlertDialog.Builder(v.getContext()).setTitle("请输入修改的手机号")
                            .setIcon(R.mipmap.phone)
                            .setView(et_name)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //按下确定键后的事件
                                    user.setUserTel(et_name.getText().toString());
                                    type = "2";
                                    getValues();
                                    tel.setText(user.getUserTel());
                                }
                            }).setNegativeButton("取消", null).show();
                }
            });
            //修改email
            email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final EditText et_name = new EditText(v.getContext());
                    new AlertDialog.Builder(v.getContext()).setTitle("请输入修改的邮箱")
                            .setIcon(R.mipmap.marketingemail)
                            .setView(et_name)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //按下确定键后的事件
                                    user.setUserEmail(et_name.getText().toString());
                                    type = "3";
                                    getValues();
                                    email.setText(user.getUserEmail());
                                }
                            }).setNegativeButton("取消", null).show();
                }
            });
            //我的发布
            release.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent_1 = new Intent(getActivity(), Idv_Release.class);
                    intent_1.putExtra("user_id", MainActivity.userId+"");
                    Log.e("cener",MainActivity.userId+"");
                    intent_1.putExtra("user_name", user.getUserName());
                    intent_1.putExtra("user_type",user.getUserType());
                    intent_1.putExtra("user_email", user.getUserEmail());
                    startActivity(intent_1);
                }
            });
            //修改密码
            cpwd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("source", "idvcenter");
                    intent.setClass(getActivity(), ForgetPwdActivity.class);
                    startActivity(intent);
                }
            });
            //添加头像
            user_avatar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPhotoPopupWindow = new PhotoPopupWindow(getActivity(), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // 进入相册选择
                            mPhotoPopupWindow.dismiss();
                            Intent intent = new Intent(Intent.ACTION_PICK);
                            intent.setType("image/*");
// 判断系统中是否有处理该 Intent 的 Activity
                            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                                startActivityForResult(intent, REQUEST_IMAGE_GET);
                            } else {
                                Toast.makeText(getActivity(), "未找到图片查看器", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // 拍照
                        }
                    });
                    View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.activity_main, null);
                    mPhotoPopupWindow.showAtLocation(rootView,
                            Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

                }
            });

        }

        return page;
    }


    /**
     * 小图模式切割图片
     * 此方式直接返回截图后的 bitmap，由于内存的限制，返回的图片会比较小
     */
    public void startSmallPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1); // 裁剪框比例
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300); // 输出图片大小
        intent.putExtra("outputY", 300);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, REQUEST_SMALL_IMAGE_CUTTING);
    }

    /**
     * 小图模式中，保存图片后，设置到视图中
     */
    private void setPicToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data"); // 直接获得内存中保存的 bitmap
            // 创建 smallIcon 文件夹
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                String storage = Environment.getExternalStorageDirectory().getPath();
                File dirFile = new File(storage + "/smallIcon");
                if (!dirFile.exists()) {
                    if (!dirFile.mkdirs()) {
                        Log.e("TAG", "文件夹创建失败");
                    } else {
                        Log.e("TAG", "文件夹创建成功");
                    }
                }
                File file = new File(dirFile, System.currentTimeMillis() + ".jpg");
                // 保存图片
                FileOutputStream outputStream = null;
                try {
                    outputStream = new FileOutputStream(file);
                    photo.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                    outputStream.flush();
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // 在视图中显示图片
            user_avatar.setImageBitmap(photo);
        }
    }







    private void getUser() {
        PageTextTask2 pageTextTask2 = new PageTextTask2();
        pageTextTask2.execute("http://" + Constant.IP + ":8080/searchfor_prj/IdvInitialize");
    }


    @Override
    public void onTurn() {

    }


    private class PageTextTask2 extends AsyncTask {

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            username1.setText(user.getUserName());
            username.setText(user.getUserName());
            tel.setText(user.getUserTel());
            email.setText(user.getUserEmail());
            email1.setText(user.getUserEmail());
            usertype.setText(user.getUserType());
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            String json = gson.toJson(MainActivity.userId);
            try {
                URL url = new URL((String) objects[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                OutputStream os = connection.getOutputStream();
                os.write(json.toString().getBytes());
                InputStreamReader is = new InputStreamReader(connection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(is);
                StringBuffer str = new StringBuffer();
                String line = null;
                while (null != (line = bufferedReader.readLine())) {
                    str.append(line);
                }
                is.close();

                String jsonStr = new String(str.toString().getBytes("utf-8"), "UTF-8");
                user = gson.fromJson(jsonStr, User.class);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
    }

    private void getValues() {
        PageTextTask pageTextTask = new PageTextTask();
        pageTextTask.execute("http://" + Constant.IP + ":8080/searchfor_prj/IdvCenterServlet");
    }

    private class PageTextTask extends AsyncTask {

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Object doInBackground(Object[] objects) {

            try {
                URL url = new URL((String) objects[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                OutputStream outputStream = connection.getOutputStream();
                Gson gson = new Gson();
                String jsonStr = gson.toJson(user) + type;
                Log.e("user", jsonStr);
                outputStream.write(jsonStr.getBytes());
                InputStream is = connection.getInputStream();
                outputStream.close();
                is.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100){
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent,200);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == -1){

            Uri uri = data.getData();
            Cursor cursor = getContext().getContentResolver().query(uri,null,null,
                    null,null);
            if (cursor.moveToFirst()){
                String imgPath = cursor.getString(cursor.getColumnIndex("_data"));
                RequestOptions options = new RequestOptions()
                        .circleCrop();
                Glide.with(this)
                        .load(imgPath)
                        .apply(options)
                        .into(user_avatar);
                //上传头像到服务器端
                File file = new File(imgPath);
                RequestBody body = RequestBody.create(MediaType.parse("image/*"),
                        file);
                Request request = new Request.Builder()
                        .url(Constant.BASE_URL+"UploadServlet")
                        .post(body)
                        .build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.e("上传头像",response.body().string());
                    }
                });
            }
        }
    }
}
