package com.intelliworkz.skumschool.Student.Attendence;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.intelliworkz.skumschool.Postdata;
import com.intelliworkz.skumschool.SplashScreen.MainActivity;
import com.intelliworkz.skumschool.Student.Calender.CalenderActivity;
import com.intelliworkz.skumschool.ChangePassword.ChangePasswordActivity;
import com.intelliworkz.skumschool.Student.Education.EducationActivity;
import com.intelliworkz.skumschool.Student.Emotional_Evaluation.Emotional_EvaluationActivity;
import com.intelliworkz.skumschool.Student.Environment.EnvironmentActivity;
import com.intelliworkz.skumschool.Student.Evaluation.EvaluationActivity;
import com.intelliworkz.skumschool.Student.Home.HomeActivity;
import com.intelliworkz.skumschool.Login.LoginActivity;
import com.intelliworkz.skumschool.Student.NoticeBoard.NoticeBoardActivity;
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
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

public class AttendenceActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageButton previousButton,forwardButton;
    TextView txtMonthName;
    private int currentMonthIndex = 0;
    GridView gvCalDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence);
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
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        txtMonthName = (TextView)findViewById(R.id.txtMonthName);
        previousButton = (ImageButton)findViewById(R.id.previousButton);
        forwardButton = (ImageButton)findViewById(R.id.forwardButton);
        gvCalDay = (GridView)findViewById(R.id.gvCalDay);

        DateFormat dateFormat = new SimpleDateFormat("MMMM yyyy");
        Date date = new Date();

        String monthName = dateFormat.format(date);

        txtMonthName.setText(monthName);

        Calendar cal =  Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int month_M = cal.get(Calendar.MONTH)+1;

        GetRandomNo randomNo = new GetRandomNo(month,month_M, year);
        randomNo.execute();

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentMonthIndex--;
                Calendar cal =  Calendar.getInstance();
                cal.add(Calendar.MONTH ,currentMonthIndex);

                String monthName  = new SimpleDateFormat("MMMM yyyy").format(cal.getTime());

                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int month_M = cal.get(Calendar.MONTH)+1;

                txtMonthName.setText(monthName);

                GetRandomNo randomNo = new GetRandomNo(month,month_M, year);
                randomNo.execute();

            }
        });

        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentMonthIndex++;
                Calendar cal =  Calendar.getInstance();
                cal.add(Calendar.MONTH ,currentMonthIndex);

                String monthName  = new SimpleDateFormat("MMMM yyyy").format(cal.getTime());

                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int month_M = cal.get(Calendar.MONTH)+1;

                txtMonthName.setText(monthName);

                GetRandomNo randomNo = new GetRandomNo(month,month_M, year);
                randomNo.execute();

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
        else if (id == R.id.nav_activity)
        {
            Intent i = new Intent(this, CalenderActivity.class);
            startActivity(i);
            finish();
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

    private String[] getMonth(int month, int year) {

        int gridSizeX = 7, gridSizeY = 6;
        int gridsize = gridSizeX * gridSizeY;
        String[] myStringArray = new String[gridSizeX*gridSizeY];

        GregorianCalendar gregorianCalendar = new GregorianCalendar(year, month, 1);
        //this gets the day of week range 1-7, Monday - Sunday
        int dayOfWeek = gregorianCalendar.get(Calendar.DAY_OF_WEEK);
        //backtracks to the beginning of current week (Monday)
        gregorianCalendar.add(Calendar.DAY_OF_YEAR, Calendar.SUNDAY - dayOfWeek);

        for (int i = 0; i < gridsize; i++)
        {
            myStringArray[i] = String.valueOf(gregorianCalendar.get(Calendar.DAY_OF_MONTH));
            gregorianCalendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        return myStringArray;
    }

    private List<String> getSunday(int month, int year) {

        GregorianCalendar gregorianCalendar = new GregorianCalendar(year, month, 1);
        List<String> myStringSunArray = new ArrayList<>();
        SimpleDateFormat fmt = new SimpleDateFormat("d/M/yyyy");
        do {
            int dayOfWeek = gregorianCalendar.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek == Calendar.SUNDAY)
                myStringSunArray.add(fmt.format(gregorianCalendar.getTime()));
            gregorianCalendar.add(Calendar.DAY_OF_MONTH, 1);
        } while (gregorianCalendar.get(Calendar.MONTH) == month);

        return myStringSunArray;
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

    private class GetRandomNo extends AsyncTask<String,Void,String> {

        int month, month_m, year;
        String status,message;
        List<String> absentList = new ArrayList<>();

        public GetRandomNo(int month, int month_m, int year) {

            this.month = month;
            this.month_m = month_m;
            this.year = year;

        }

        @Override
        protected String doInBackground(String... strings) {

            JSONObject attList=new JSONObject();
            try {

                attList.put("s_stddiv","5A");
                attList.put("s_rollno","10");
                Postdata postdata=new Postdata();
                String attPd=postdata.post(MainActivity.mainUrl+"studentAttendence",attList.toString());
                JSONObject j=new JSONObject(attPd);
                status=j.getString("status");
                if(status.equals("1"))
                {
                    Log.d("Like","Successfully");
                    message=j.getString("message");
                    JSONArray JsArry=j.getJSONArray("AbsentStudent");

                    for (int i=0;i<JsArry.length();i++)
                    {
                        JSONObject jo=JsArry.getJSONObject(i);

                        String date=jo.getString("DATE_FORMAT(date, '%d/%m/%Y')");

                        absentList.add(date);
                    }
                }
                else
                {
                    message=j.getString("message");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            gvCalDay.setAdapter(new DayGridAdapter(AttendenceActivity.this, getMonth(month,year), getSunday(month,year), month_m, year, absentList));
        }
    }
}
