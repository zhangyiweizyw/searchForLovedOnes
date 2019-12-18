package com.example.administrator.searchforlovedones;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.List;

public class CommentListAdapter extends BaseAdapter{
    private Context context;
    private List<Comment> dataSource = null;
    private int itemLayoutId;//item对应的布局文件的资源id
    public CommentListAdapter(Context context, List<Comment> dataSource, int itemLayoutId) {
        this.context = context;
        this.dataSource = dataSource;
        this.itemLayoutId = itemLayoutId;
    }
    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView){
            //加载item对应的布局文件
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(itemLayoutId,null);
        }

        //获得每个item的TextView对象和Button对象，并给每个item的Button添加点击事件监听器
        TextView textView1 = convertView.findViewById(R.id.lv_name);
        TextView textView2 = convertView.findViewById(R.id.lv_content);
        TextView textView0 = convertView.findViewById(R.id.lv_biaohao);
        RelativeLayout re=convertView.findViewById(R.id.lv_item);
        Comment map = dataSource.get(position);

        textView1.setText("发布人："+map.getName());
        textView2.setText(map.getContent());

        final String name=textView1.getText().toString();
        final String content=textView2.getText().toString();
        final String qq=map.getQq();
        final String phone=map.getTel();
        final String email=map.getEmail();
        final String time=map.getTime();
        re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog=new AlertDialog.Builder(context).setTitle("留言详情").setMessage(name+"\n联系电话："+phone+"\nQQ:"+qq+"\nemail:"+email+"\n内容："+"\n     "+content+"\n\n                   "+time)
                        .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).create();
                alertDialog.show();
            }
        });
        textView0.setText(position+1+"");
        return convertView;
    }

}
