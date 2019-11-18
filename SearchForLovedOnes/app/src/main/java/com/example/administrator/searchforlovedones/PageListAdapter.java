package com.example.administrator.searchforlovedones;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class PageListAdapter extends BaseAdapter {
    private List<PageText> texts = null;
    private Context context;
    private int item_id;

    public PageListAdapter(List<PageText> texts, Context context, int item_id) {
        this.texts = texts;
        this.context = context;
        this.item_id = item_id;
    }

    @Override
    public int getCount() {
        if(texts !=null){
            return texts.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return texts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView==null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(item_id,null);
            holder.iv = convertView.findViewById(R.id.page_img);
            holder.tv_title = convertView.findViewById(R.id.page_title);
            holder.tv_cont = convertView.findViewById(R.id.page_content);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        holder.iv.setImageDrawable(context.getResources().getDrawable(R.drawable.center_select));
        holder.tv_title.setText("EditText字体颜色");
        holder.tv_cont.setText("我在写一个记事本，想实现编辑EditText中文字的大小，颜色等等属性后，保存，然后显示的TextView也可以显示edittext中的变化。求大神指导，谢谢");

        return convertView;
    }

    static class ViewHolder{
        ImageView iv;
        TextView tv_title;
        TextView tv_cont;
    }
}
