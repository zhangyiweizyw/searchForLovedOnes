package com.example.sunyidong.qinfeng;

        import android.content.ContentValues;
        import android.content.Intent;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Adapter;
        import android.widget.Button;
        import android.widget.ListView;

        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.Statement;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Date;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

public class TrueFeelingsMessage extends AppCompatActivity {
    private ListView listView;
    private List<Map<String, Object>> dataSource = null;
    private MyAdapter myAdapter;
    private Connection conn;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.truefeelingsmessage);
        listView = findViewById(R.id.lv_data);
        //留言数据库
        Newbase newbase=new Newbase(this,"TrueFellingsMessage.db",1);
        database = newbase.getWritableDatabase();
        //初始化数据
        conn=DbUtil.getCon();
        System.out.println(conn);
        String content="宝宝还不识字，但我会为宝宝画一幅可爱的画。作为留言给他，宝宝往往会因为这出乎意料的留言感到惊喜。" +
                "我会把这样的纸条放在不同的地方，有时会放在宝宝的衣服口袋里，有时会放在他吃饭的餐盘下，有时会放在他喜欢背的书包里，" +
                "他每次都可以找到小小的惊喜。宝宝你在哪？";
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time=dateFormat.format(date);
        insertdata("王小明","155****5911","123456789","123456789@qq.com",content,time);
        insertdata("王大明","155****5911","123456789","123456789@qq.com",content,time);
        chaxun();
        setAdapters();

        DbUtil.close(conn);
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
    public void insertdata(String name,String phone,String qq,String email,String content,String time){
//        String sql="INSERT INTO comment(name,tel,email,content,wechat,time) values(?,?,?,?,?,?)";
//        try {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setString(1,name);
//            ps.setString(2,phone);
//            ps.setString(3,qq);
//            ps.setString(4,email);
//            ps.setString(5,content);
//            ps.setString(6,time);
//            ps.executeUpdate();
//            ps.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        ContentValues emplo = new ContentValues();
        emplo.put("name",name);
        emplo.put("phone",phone);
        emplo.put("qq",qq);
        emplo.put("email",email);
        emplo.put("content",content);
        emplo.put("time",time);
        database.insert("liuyantable",null,emplo);
    }
    private void setAdapters() {
        myAdapter = new MyAdapter(this,dataSource,R.layout.listview_item);
        listView.setAdapter(myAdapter);
    }
    private void chaxun() {
//        try {
//            PreparedStatement psta=conn.prepareStatement("select * from comment");
//            ResultSet rs=psta.executeQuery();
//            dataSource = new ArrayList<>();
//            while(rs.next()){
//                Map<String, Object> map = new HashMap<>();
//                String name = rs.getString("name");
//                String phone = rs.getString("tel");
//                String  qq= rs.getString("wechat");
//                String email=rs.getString("email");
//                String content=rs.getString("content");
//                String time=rs.getString("time");
//                map.put("name",name);
//                map.put("phone",phone);
//                map.put("qq",qq);
//                map.put("email",email);
//                map.put("content",content);
//                map.put("time",time);
//                dataSource.add(map);
//            }
//
//            rs.close();
//            psta.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        Cursor cursor=database.query("liuyantable",null,null,null,null,null,null);
        int i=0;
        dataSource = new ArrayList<>();
        for( i=0;i<cursor.getCount()&&cursor!=null;i++) {
            Map<String, Object> map = new HashMap<>();
            if (cursor.moveToFirst()) {
                cursor.move(i);
                String name = cursor.getString(0);
                String phone = cursor.getString(1);
                String  qq= cursor.getString(2);
                String email=cursor.getString(3);
                String content=cursor.getString(4);
                String time=cursor.getString(5);
                    map.put("name",name);
                    map.put("phone",phone);
                    map.put("qq",qq);
                    map.put("email",email);
                    map.put("content",content);
                    map.put("time",time);
                    dataSource.add(map);
            }
        }
    }
}
