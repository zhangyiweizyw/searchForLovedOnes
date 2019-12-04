package com.example.administrator.searchforlovedones;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
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

        holder.iv.setImageDrawable(context.getResources().getDrawable(R.drawable.guai));
        holder.tv_title.setText(texts.get(position).getTitle());
        holder.tv_cont.setText(texts.get(position).getContent());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,PageDetail.class);
                intent.putExtra("title",texts.get(position).getTitle());
                intent.putExtra("content",texts.get(position).getContent());
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    static class ViewHolder{
        ImageView iv;
        TextView tv_title;
        TextView tv_cont;
    }
}
