package com.example.administrator.searchforlovedones;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

public class Idv_SerachPeople extends Activity {
    private TextView inv_m_name;
    private TextView inv_m_sex;
    private TextView inv_m_borndate;
    private TextView inv_height;
    private TextView inv_m_missdate;
    private TextView inv_isblood;
    private TextView inv_isreport;
    private TextView inv_m_native;
    private TextView inv_m_missaddr;
    private TextView inv_m_feature;
    private TextView inv_m_process;
    private TextView inv_m_family;
    private TextView inv_y_name;
    private TextView inv_y_phone;
    private TextView inv_y_email;
    private TextView inv_y_address;
    private TextView inv_y_relation;
    private SearchPeopleBean spb;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.idv_serachpeople);

        inv_m_name=findViewById(R.id.inv_m_name);
        inv_m_sex=findViewById(R.id.inv_m_sex);
        inv_m_borndate=findViewById(R.id.inv_m_borndate);
        inv_height=findViewById(R.id.inv_height);
        inv_m_missdate=findViewById(R.id.inv_m_missdate);
        inv_isblood=findViewById(R.id.inv_isblood);
        inv_isreport=findViewById(R.id.inv_isreport);
        inv_m_native=findViewById(R.id.inv_m_native);
        inv_m_missaddr=findViewById(R.id.inv_m_missaddr);
        inv_m_feature=findViewById(R.id.inv_m_feature);
        inv_m_process=findViewById(R.id.inv_m_process);
        inv_m_family=findViewById(R.id.inv_m_family);
        inv_y_name=findViewById(R.id.inv_y_name);
        inv_y_phone=findViewById(R.id.inv_y_phone);
        inv_y_email=findViewById(R.id.inv_y_email);
        inv_y_address=findViewById(R.id. inv_y_address);
        inv_y_relation=findViewById(R.id.inv_y_relation);

       spb=(SearchPeopleBean) getIntent().getSerializableExtra("1");
        inv_m_name.setText(spb.getM_name());
        inv_m_sex.setText(spb.getM_sex());
        inv_m_borndate.setText(spb.getM_borndate());
        inv_height.setText(spb.getHeight());
        inv_m_missdate.setText(spb.getM_missdate());
        inv_isblood.setText(spb.getIsBlood());
        inv_isreport.setText(spb.getIsReport());
        inv_m_native.setText(spb.getM_native());
        inv_m_missaddr.setText(spb.getM_missadd());
        inv_m_feature.setText(spb.getM_fearture());
        inv_m_process.setText(spb.getM_process());
        inv_m_family.setText(spb.getM_family());
        inv_y_name.setText(spb.getY_name());
        inv_y_phone.setText(spb.getY_phone());
        inv_y_email.setText(spb.getY_email());
        inv_y_address.setText(spb.getY_address());
        inv_y_relation.setText(spb.getY_relation());
    }
}

