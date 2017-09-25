package com.intelliworkz.skumschool.Admin.SearchStd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.intelliworkz.skumschool.R;

public class SearchStdActivity extends AppCompatActivity {

    RecyclerView rvStdList;
    RecyclerView.LayoutManager rvStdListManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_std);

        if(getSupportActionBar()!= null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        rvStdList = (RecyclerView)findViewById(R.id.rvStdList);
        rvStdList.setHasFixedSize(true);

        rvStdListManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        rvStdList.setLayoutManager(rvStdListManager);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
