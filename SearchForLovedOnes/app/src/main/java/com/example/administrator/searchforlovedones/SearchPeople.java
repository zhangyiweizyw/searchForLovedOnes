package cn.edu.hebtu.software.xunqin;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SearchPeople extends AppCompatActivity {

    private Spinner syear=null;
    private Spinner smouth=null;
    private Spinner sday=null;
    private Spinner lyear=null;
    private Spinner lmouth=null;
    private Spinner lday=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchpeople);

        syear=findViewById(R.id.year);
        smouth=findViewById(R.id.mouth);
        sday=findViewById(R.id.day);
        lyear=findViewById(R.id.loseyear);
        lmouth=findViewById(R.id.losemouth);
        lday=findViewById(R.id.loseday);
        setSyear();
        setSday();

    }
    //获取下拉框年份数据
    private void setSyear(){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR) ;
        List<Integer> list=new ArrayList<Integer>();
        for(int i=1940;i<=year;i++){
            list.add(i);
        }
        Integer[] years=new Integer[list.size()];

        for(int j=0;j<list.size();j++){
           years[j]=list.get(j);
        }
        ArrayAdapter<Integer>arrayAdapter=new ArrayAdapter<Integer>(SearchPeople.this,R.layout.support_simple_spinner_dropdown_item,years);
        syear.setAdapter(arrayAdapter);
        lyear.setAdapter(arrayAdapter);
    }
    //获取下拉框月份日期数据
   /* private void setSmouth(){
        Integer[]mouths=new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12};
        ArrayAdapter<Integer>arrayAdapter=new ArrayAdapter<Integer>(SearchPeople.this,R.layout.support_simple_spinner_dropdown_item,mouths);
        smouth.setAdapter(arrayAdapter);
    }*/
    //获取下拉框日期数据
    private void setSday(){
        final Integer[]days1=new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
        final Integer[]days2=new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
        final Integer[]days3=new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29};
        final Integer[]days4=new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28};
        //获取选中的月份
        smouth.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String mnumber= SearchPeople.this.getResources().getStringArray(R.array.month)[position];
                Log.e("month",mnumber);
                int mouth=Integer.parseInt(mnumber);
                String ynumber= syear.getSelectedItem().toString();
                int year=Integer.parseInt(ynumber);

                if(mouth==1||mouth==3||mouth==5||mouth==7||mouth==8||mouth==10||mouth==12){
                    ArrayAdapter<Integer>arrayAdapter=new ArrayAdapter<Integer>(SearchPeople.this,R.layout.support_simple_spinner_dropdown_item,days1);
                    sday.setAdapter(arrayAdapter);
                }
                else if(mouth==2){
                    if((year%4==0&&year%100!=0)||year%400==0){
                        //今年是闰年
                        ArrayAdapter<Integer>arrayAdapter=new ArrayAdapter<Integer>(SearchPeople.this,R.layout.support_simple_spinner_dropdown_item,days3);
                        sday.setAdapter(arrayAdapter);
                    }
                    else{
                        //今年是平年
                        ArrayAdapter<Integer>arrayAdapter=new ArrayAdapter<Integer>(SearchPeople.this,R.layout.support_simple_spinner_dropdown_item,days4);
                        sday.setAdapter(arrayAdapter);
                    }
                }
                else{
                    ArrayAdapter<Integer>arrayAdapter=new ArrayAdapter<Integer>(SearchPeople.this,R.layout.support_simple_spinner_dropdown_item,days2);
                    sday.setAdapter(arrayAdapter);
                }
            }
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        //获取失踪月份
        lmouth.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String mnumber= SearchPeople.this.getResources().getStringArray(R.array.month)[position];
                Log.e("month",mnumber);
                int mouth=Integer.parseInt(mnumber);
                String ynumber= lyear.getSelectedItem().toString();
                int year=Integer.parseInt(ynumber);

                if(mouth==1||mouth==3||mouth==5||mouth==7||mouth==8||mouth==10||mouth==12){
                    ArrayAdapter<Integer>arrayAdapter=new ArrayAdapter<Integer>(SearchPeople.this,R.layout.support_simple_spinner_dropdown_item,days1);
                    lday.setAdapter(arrayAdapter);
                }
                else if(mouth==2){
                    if((year%4==0&&year%100!=0)||year%400==0){
                        //今年是闰年
                        ArrayAdapter<Integer>arrayAdapter=new ArrayAdapter<Integer>(SearchPeople.this,R.layout.support_simple_spinner_dropdown_item,days3);
                        lday.setAdapter(arrayAdapter);
                    }
                    else{
                        //今年是平年
                        ArrayAdapter<Integer>arrayAdapter=new ArrayAdapter<Integer>(SearchPeople.this,R.layout.support_simple_spinner_dropdown_item,days4);
                        lday.setAdapter(arrayAdapter);
                    }
                }
                else{
                    ArrayAdapter<Integer>arrayAdapter=new ArrayAdapter<Integer>(SearchPeople.this,R.layout.support_simple_spinner_dropdown_item,days2);
                    lday.setAdapter(arrayAdapter);
                }
            }
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });


    }


}
