package com.example.administrator.searchforlovedones;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class F1 extends Fragment{
    private ImageView filter;
    private View firstpage;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         firstpage = inflater.inflate(R.layout.fragment_page1,container,false);
        firstpage.setBackgroundColor(Color.WHITE);
        filter=firstpage.findViewById(R.id.img_filter);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),FilterActivity.class);
                startActivity(intent);
            }
        });



        return firstpage;


    }
}
