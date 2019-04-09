package com.prospec.sumautolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

// class Main
public class MainActivity extends AppCompatActivity {

    //    ประกาศตัวแปร
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