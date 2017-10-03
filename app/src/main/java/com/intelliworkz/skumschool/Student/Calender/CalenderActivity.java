package com.intelliworkz.skumschool.Student.Calender;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
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

import com.intelliworkz.skumschool.SplashScreen.MainActivity;
import com.intelliworkz.skumschool.Student.Attendence.AttendenceActivity;
import com.intelliworkz.skumschool.ChangePassword.ChangePasswordActivity;
import com.intelliworkz.skumschool.Student.Education.EducationActivity;
import com.intelliworkz.skumschool.Student.Emotional_Evaluation.Emotional_EvaluationActivity;
import com.intelliworkz.skumschool.Student.Environment.EnvironmentActivity;
import com.intelliworkz.skumschool.Student.Evaluation.EvaluationActivity;
import com.intelliworkz.skumschool.Student.Home.HomeActivity;
import com.intelliworkz.skumschool.Login.LoginActivity;
import com.intelliworkz.skumschool.Student.NoticeBoard.NoticeBoardActivity;
import com.intelliworkz.skumschool.Postdata;
import com.intelliworkz.skumschool.Student.Profile.ProfileActivity;
import com.intelliworkz.skumschool.Student.ProgressReport.ProgressReportActivity;
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

        Menu menu = navigationView.getMenu();
        MenuItem menuItem = menu.getItem(9);
        menuItem.setChecked(true);

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

        if (id == R.id.nav_home)
        {
            Intent i = new Intent(this, HomeActivity.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_attendence)
        {
            Intent i = new Intent(this, AttendenceActivity.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_profile)
        {
            Intent i = new Intent(this, ProfileActivity.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_progessreport)
        {
            Intent i = new Intent(this,ProgressReportActivity.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_noticeboard)
        {
            Intent i = new Intent(this, NoticeBoardActivity.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_evaluation)
        {
            Intent i = new Intent(this, EvaluationActivity.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_education)
        {
            Intent i = new Intent(this, EducationActivity.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_environment)
        {
            Intent i = new Intent(this, EnvironmentActivity.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_emotional)
        {
            Intent i = new Intent(this, Emotional_EvaluationActivity.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_share)
        {
            Intent i=new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            String body="https://play.google.com/store/apps/details?id=com.intelliworkz.skumschool";
            i.putExtra(Intent.EXTRA_SUBJECT,body);
            i.putExtra(Intent.EXTRA_TEXT,body);
            startActivity(Intent.createChooser(i,"Share using"));
            finish();
        }
        else if (id == R.id.nav_rate)
        {
            Intent i=new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.intelliworkz.skumschool"));
            if(!MyStartActivity(i))
            {
                i.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.intelliworkz.skumschool"));
                if(!MyStartActivity(i))
                {
                    Log.d("Like","Could not open browser");
                }
            }
        }
        else if (id == R.id.nav_logout)
        {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private boolean MyStartActivity(Intent i) {

        try
        {
            startActivity(i);
            return true;
        }
        catch (ActivityNotFoundException e)
        {
            return false;
        }
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
                String adPd=postdata.post(MainActivity.mainUrl+"activity",activityList.toString());
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
