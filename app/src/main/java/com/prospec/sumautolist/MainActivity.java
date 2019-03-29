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

public class MainActivity extends AppCompatActivity {


    public static TextView txtTotalPrice;
    RecyclerView tableList;
    ArrayList<ModelMain> modelMains;
    private AdapterMain adapterMain;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Create An Initial Invoice");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tableList = (RecyclerView) findViewById(R.id.tableList);
        tableList.setLayoutManager(new LinearLayoutManager(this));

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
    }

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
}