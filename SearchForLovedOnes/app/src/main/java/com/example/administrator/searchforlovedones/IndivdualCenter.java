package com.example.administrator.searchforlovedones;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Base64;
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
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.OkHttpClient;


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

    private String pa;
    private Uri uri;
    private PullScrollView mScrollView;
    private ImageView mHeadImg;
    public  ImageView user_avatar;
    private OkHttpClient okHttpClient;
//头像
    private Bitmap bitmap;
    private String user_uri;
    private PhotoPopupWindow mPhotoPopupWindow;
    private static final int REQUEST_IMAGE_GET = 0;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_SMALL_IMAGE_CUTTING = 2;
    private static final int REQUEST_BIG_IMAGE_CUTTING = 3;
    private static final String IMAGE_FILE_NAME = "icon.jpg";
    private    Uri imageUri = null;
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
                    intent_1.putExtra("user_id", user.getId());
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
                            if (ContextCompat.checkSelfPermission(getActivity(),
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                    != PackageManager.PERMISSION_GRANTED) {
                                // 权限还没有授予，进行申请
                                ActivityCompat.requestPermissions(getActivity(),
                                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200); // 申请的 requestCode 为 200
                            } else {
                                // 如果权限已经申请过，直接进行图片选择
                                mPhotoPopupWindow.dismiss();
                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(intent, REQUEST_IMAGE_GET);
                            }
                        }
                    }, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (ContextCompat.checkSelfPermission(getActivity(),
                                    Manifest.permission.CAMERA)
                                    != PackageManager.PERMISSION_GRANTED   || ContextCompat.checkSelfPermission(getActivity(),
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                    != PackageManager.PERMISSION_GRANTED) {
                                // 权限还没有授予，进行申请
                                ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},300);
                            } else {
                                mPhotoPopupWindow.dismiss();
                                imageCapture();
                            }


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

    private void imageCapture() {
        Intent intent;
        Uri pictureUri;
        File pictureFile = new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME);
        // 判断当前系统
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            pictureUri = FileProvider.getUriForFile(getActivity(),
                    "com.example.administrator.searchforlovedones.provider", pictureFile);
        } else {
            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            pictureUri = Uri.fromFile(pictureFile);
        }
        // 去拍照
        intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);

        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 200:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    mPhotoPopupWindow.dismiss();
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    // 判断系统中是否有处理该 Intent 的 Activity
                        startActivityForResult(intent, REQUEST_IMAGE_GET);
                } else {
                    mPhotoPopupWindow.dismiss();
                }
                break;
            case 300:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mPhotoPopupWindow.dismiss();
                    imageCapture();
                } else {
                    mPhotoPopupWindow.dismiss();
                }
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);;
        if (resultCode == RESULT_OK) {
            Log.e("requestCode",String.valueOf(requestCode));
            switch (requestCode) {

                // 小图切割
                case REQUEST_SMALL_IMAGE_CUTTING://

                    if (data != null) {
                        try {
                            bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(imageUri));
                            Log.e("image",imageUri.getPath());
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
                                user_uri=file.getPath();
                                FileOutputStream outputStream = null;
                                try {
                                    outputStream = new FileOutputStream(file);
                                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                                    outputStream.flush();
                                    outputStream.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            uploadPicture();
                            user_avatar.setImageBitmap(bitmap);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    break;

                // 相册选取
                case REQUEST_IMAGE_GET:
                    try {
                        startSmallPhotoZoom(data.getData());
                        Log.e("123",data.getData().getPath());
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                    break;
                case REQUEST_IMAGE_CAPTURE:
                    File temp = new File(Environment.getExternalStorageDirectory() + "/" + IMAGE_FILE_NAME);
                    Uri uri=FileProvider.getUriForFile(getContext(), getContext().getApplicationContext().getPackageName() + ".provider",temp);
                        startSmallPhotoZoom(uri);
                    break;
                //......
            }
        }
    }

    private void uploadPicture(){
        PageTextTask3 pageTextTask3 = new PageTextTask3();
        pageTextTask3.execute("http://" + Constant.IP + ":8080/searchfor_prj/IdvPicture");
    }
    private  void startSmallPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1); // 裁剪框比例
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300); // 输出图片大小
        intent.putExtra("outputY", 300);
        intent.putExtra("scale", true);
        imageUri = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/" + "small.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        startActivityForResult(intent, REQUEST_SMALL_IMAGE_CUTTING);
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
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//                Log.e("sdf",user.getUser_uri());
                pa = "file://" + user.getUser_uri();
                uri = Uri.parse(pa);
                try {
                    Log.e("uri", uri.getPath());
                    user_avatar.setImageBitmap(BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(uri)));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
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
    private class PageTextTask3 extends AsyncTask {

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
                String str=user_uri+"|"+user.getUserTel();
                outputStream.write(str.getBytes());
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
}
