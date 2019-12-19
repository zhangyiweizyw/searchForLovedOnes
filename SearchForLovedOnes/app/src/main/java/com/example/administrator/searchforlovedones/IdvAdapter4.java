package com.example.administrator.searchforlovedones;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class IdvAdapter4 extends BaseAdapter {
    private Context context;

    private List<OtherSearchBean> dataSource=null;
    private int itemLayoutId;//item对应的布局文件的资源id
    public IdvAdapter4(Context context, List<OtherSearchBean> dataSource, int itemLayoutId) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (null == convertView){
            //加载item对应的布局文件
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(itemLayoutId,null);
        }

        //获得每个item的TextView对象和Button对象，并给每个item的Button添加点击事件监听器
        TextView textView1 = convertView.findViewById(R.id.idvlv_name);
        TextView textView2 = convertView.findViewById(R.id.idvlv_sex);
        TextView textView3 = convertView.findViewById(R.id.idvlv_type);
        RelativeLayout bt1=convertView.findViewById(R.id.idvlv_item);

       OtherSearchBean sp=dataSource.get(position);
        textView1.setText("失踪人:"+sp.getS_name());
        textView2.setText("性别;"+sp.getS_sex());
        textView3.setText("类型:其他寻人");

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Idv_OtherSearch.class);
                intent.putExtra("4",dataSource.get(position).toString());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

}