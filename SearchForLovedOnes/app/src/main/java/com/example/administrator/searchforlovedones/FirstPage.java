package com.example.administrator.searchforlovedones;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

public class FirstPage extends Fragment {
    private Spinner spinner;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View firstpage = inflater.inflate(R.layout.fragment_page,container,false);
        firstpage.setBackgroundColor(Color.BLUE);

        spinner = firstpage.findViewById(R.id.first_span);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("ResourceType")
            @Override

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("select",position+"");
                MainActivity mainActivity= (MainActivity) getActivity();
                mainActivity.fragmentTabHost.setCurrentTab(position);
                spinner.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return firstpage;
    }
}
