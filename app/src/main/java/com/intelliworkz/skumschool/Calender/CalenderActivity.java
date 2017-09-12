package com.intelliworkz.skumschool.Calender;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.intelliworkz.skumschool.Postdata;
import com.intelliworkz.skumschool.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class CalenderActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView ivEvent;
    RecyclerView.LayoutManager rvEventManager;
    RecyclerView.Adapter rvEventAdapter;
    ImageView previousButton,forwardButton;
    TextView txtMonthName,txtNotDataFound;
    private int currentMonthIndex = 0;
    String url = "http://skumschool.com/webservices/activity/";
    ArrayList<HashMap<String,String>> activityListArray=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*----------------------- Calender page ----------------------*/

        ivEvent = (RecyclerView)findViewById(R.id.ivEvent);
        ivEvent.setHasFixedSize(true);

        rvEventManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        ivEvent.setLayoutManager(rvEventManager);

        txtMonthName = (TextView)findViewById(R.id.txtMonthName);

        previousButton = (ImageView)findViewById(R.id.previousButton);
        forwardButton = (ImageView)findViewById(R.id.forwardButton);

        txtNotDataFound = (TextView)findViewById(R.id.txtNotDataFound);

        DateFormat dateFormat = new SimpleDateFormat("MMMM");
        Date date = new Date();

        String monthName = dateFormat.format(date);
        txtMonthName.setText(monthName);

        GetActivityList activityList = new GetActivityList(monthName);
        activityList.execute();

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentMonthIndex--;
                Calendar cal =  Calendar.getInstance();
                cal.add(Calendar.MONTH ,currentMonthIndex);

                String monthName  = new SimpleDateFormat("MMMM").format(cal.getTime());

                txtMonthName.setText(monthName);

                GetActivityList activityList = new GetActivityList(monthName);
                activityList.execute();
            }
        });

        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentMonthIndex++;
                Calendar cal =  Calendar.getInstance();
                cal.add(Calendar.MONTH ,currentMonthIndex);

                String monthName  = new SimpleDateFormat("MMMM").format(cal.getTime());

                txtMonthName.setText(monthName);

                GetActivityList activityList = new GetActivityList(monthName);
                activityList.execute();

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        /*if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class GetActivityList extends AsyncTask<String,Void,String> {

        String monthName,status;

        public GetActivityList(String monthName) {
            this.monthName = monthName;
        }

        @Override
        protected String doInBackground(String... strings) {

            activityListArray.clear();

            JSONObject activityList=new JSONObject();
            try {
                activityList.put("month",monthName);
                Postdata postdata=new Postdata();
                String adPd=postdata.post(url,activityList.toString());
                JSONObject j=new JSONObject(adPd);
                status=j.getString("status");
                if(status.equals("1"))
                {
                    Log.d("Like","Successfully");
                    JSONArray JsArry=j.getJSONArray("activity_list");

                    for (int i=0;i<JsArry.length();i++)
                    {
                        HashMap<String,String > hashMap = new HashMap<>();
                        JSONObject jo=JsArry.getJSONObject(i);

                        String id=jo.getString("id");
                        String title=jo.getString("title");
                        String description=jo.getString("description");
                        String activity_date=jo.getString("activity_date");
                        String activity_day=jo.getString("activity_day");

                        hashMap.put("id",id);
                        hashMap.put("title",title);
                        hashMap.put("description",description);
                        hashMap.put("activity_date",activity_date);
                        hashMap.put("activity_day",activity_day);

                        activityListArray.add(hashMap);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(status.equals("1"))
            {
                ivEvent.setVisibility(View.VISIBLE);
                txtNotDataFound.setVisibility(View.GONE);
                rvEventAdapter=new EventAdapter(CalenderActivity.this,activityListArray);
                ivEvent.setAdapter(rvEventAdapter);
            }
            else
            {
                ivEvent.setVisibility(View.GONE);
                txtNotDataFound.setVisibility(View.VISIBLE);
            }
        }
    }
}
