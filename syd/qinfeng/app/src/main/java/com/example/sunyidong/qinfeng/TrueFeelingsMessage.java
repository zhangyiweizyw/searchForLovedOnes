package com.example.sunyidong.qinfeng;

        import android.content.Intent;
        import android.os.AsyncTask;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ListView;
        import com.google.gson.Gson;
        import com.google.gson.reflect.TypeToken;
        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.List;


public class TrueFeelingsMessage extends AppCompatActivity {

    private ListView listView;
    private Gson gson;
    private List<Comment> comments = new ArrayList<>();
    private CommentListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.truefeelingsmessage);
        gson = new Gson();
        listView = findViewById(R.id.lv_data);
        //插入数据
        getValues();
        //写留言按钮
        Button bt_wmessage=findViewById(R.id.bt_wmessage);
        bt_wmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TrueFeelingsMessage.this,WritingMessage.class);
                startActivity(intent);
            }
        });
    }
    private void getValues() {
        PageTextTask pageTextTask = new PageTextTask();
        pageTextTask.execute("http://10.7.88.19:8080/OkHttpServer/CommentServlet");
    }
    private void listSource() {
        adapter = new CommentListAdapter(this,comments,R.layout.listview_item);
        listView.setAdapter(adapter);
    }
//连接服务端
    private class PageTextTask extends AsyncTask {


        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            listSource();
            listView.deferNotifyDataSetChanged();
        }

        @Override
        protected Object doInBackground(Object[] objects) {

            try {
                URL url = new URL((String) objects[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                InputStreamReader is = new InputStreamReader(connection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(is);
                StringBuffer str = new StringBuffer();
                String line = null;
                while (null!=(line = bufferedReader.readLine()))  {
                    str.append(line);
                }
                is.close();
                String jsonStr =new String(str.toString().getBytes("utf-8"),"UTF-8");
                Log.e("获取到的JSON格式的用户列表", jsonStr);
                comments = gson.fromJson(String.valueOf(jsonStr), new TypeToken<List<Comment>>() {}.getType());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
    }
}
