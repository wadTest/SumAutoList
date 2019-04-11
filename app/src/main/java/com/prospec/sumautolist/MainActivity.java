package com.prospec.sumautolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

// class Main
public class MainActivity extends AppCompatActivity {

    //    ประกาศตัวแปร
    private EditText e_setname, e_road, e_soi, e_project, e_name, e_address, e_phone;
    private TextView t_number, tv_name, title;
    private AutoCompleteTextView a_province, a_area, a_district;
    private Spinner s_category;
    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5;
    private ImageView image1, image2, image3;


    public static TextView txtTotalPrice;// Text รวมผลลัพธ์ อัตโนมัติ
    RecyclerView tableList;// RecyclerView list
    ArrayList<ModelMain> modelMains;
    private AdapterMain adapterMain;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Toolbar
        toolBar();

        ggetEvent();

//        List
        tableList = (RecyclerView) findViewById(R.id.tableList);
        tableList.setLayoutManager(new LinearLayoutManager(this));

//        ช่อง Total
        txtTotalPrice = (TextView) findViewById(R.id.txtTotalPrice);
        findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            }
        });

        modelMains = new ArrayList<>();

        adapterMain = new AdapterMain(modelMains);

        tableList.setAdapter(adapterMain);

        adapterMain.addItem(new ModelMain("", 0, true), modelMains.size());
    }//Method

    private void ggetEvent() {
//        EditText
        e_setname = (EditText) findViewById(R.id.e_setname);
        e_road = (EditText) findViewById(R.id.e_road);
        e_soi = (EditText) findViewById(R.id.e_soi);
        e_project = (EditText) findViewById(R.id.e_project);
        e_name = (EditText) findViewById(R.id.e_name);
        e_address = (EditText) findViewById(R.id.e_address);
        e_phone = (EditText) findViewById(R.id.e_phone);
//        TextView
        t_number = (TextView) findViewById(R.id.t_number);
        tv_name = (TextView) findViewById(R.id.tv_name);
        title = (TextView) findViewById(R.id.title);
//        AutoCompleteTextView
        a_province = (AutoCompleteTextView) findViewById(R.id.a_province);
        a_area = (AutoCompleteTextView) findViewById(R.id.a_area);
        a_district = (AutoCompleteTextView) findViewById(R.id.a_district);
//        Spinner
        s_category = (Spinner) findViewById(R.id.s_category);
//        CheckBox
        checkBox1 = (CheckBox) findViewById(R.id.checkBox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
        checkBox4 = (CheckBox) findViewById(R.id.checkBox4);
        checkBox5 = (CheckBox) findViewById(R.id.checkBox5);
//        ImageView
        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);
        image3 = (ImageView) findViewById(R.id.image3);
    }


    private void toolBar() {
        //        ADD Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ที่ดินเปล่า");
        toolbar.setSubtitle("โปรดกรอกรายละเอียดที่ดินเปล่า");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }//end toolbar

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapterMain.setOnItemClickListener(new AdapterMain.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (modelMains.get(position).isBtnPlus()) {
                    adapterMain.addItem(new ModelMain("", 0, true), modelMains.size());
                } else {
                    adapterMain.deleteItem(position);
                }
            }
        });
    }
}//Main Class