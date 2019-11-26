package com.example.administrator.searchforlovedones;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class F4 extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View firstpage = inflater.inflate(R.layout.fragment_page4,container,false);
        firstpage.setBackgroundColor(Color.WHITE);

        return firstpage;
    }
}
