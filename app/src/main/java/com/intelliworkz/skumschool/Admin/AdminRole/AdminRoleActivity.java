package com.intelliworkz.skumschool.Admin.AdminRole;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.intelliworkz.skumschool.Admin.AdminAttendence.AdminAttendenceActivity;
import com.intelliworkz.skumschool.Admin.AdminAttendence.AttendenceStandardActivity;
import com.intelliworkz.skumschool.Admin.AdminEvent.AdminEventActivity;
import com.intelliworkz.skumschool.Admin.AdminHome.AdminHomeActivity;
import com.intelliworkz.skumschool.Admin.AdminNoticeBoard.AdminNoticeBoardActivity;
import com.intelliworkz.skumschool.Admin.AdminStudent.AddStudentActivity;
import com.intelliworkz.skumschool.Admin.AdminUser.AddUserActivity;
import com.intelliworkz.skumschool.HttpHandler;
import com.intelliworkz.skumschool.Login.LoginActivity;
import com.intelliworkz.skumschool.R;
import com.intelliworkz.skumschool.SplashScreen.MainActivity;
import com.intelliworkz.skumschool.Student.Calender.CalenderActivity;
import com.intelliworkz.skumschool.Student.Education.EducationActivity;
import com.intelliworkz.skumschool.Student.Emotional_Evaluation.Emotional_EvaluationActivity;
import com.intelliworkz.skumschool.Student.Environment.EnvironmentActivity;
import com.intelliworkz.skumschool.Student.Evaluation.EvaluationActivity;
import com.intelliworkz.skumschool.Student.Home.HomeActivity;
import com.intelliworkz.skumschool.Student.NoticeBoard.NoticeBoardActivity;
import com.intelliworkz.skumschool.Student.Profile.ProfileActivity;
import com.intelliworkz.skumschool.Student.ProgressReport.ProgressReportActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AdminRoleActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TabLayout tlRole;
    ViewPager vpRole;
    ArrayList<String> roleArrListName=new ArrayList<>();
    public static ArrayList<String> roleArrListId=new ArrayList<>();
    PagerRole adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_role);
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
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);


        vpRole = (ViewPager)findViewById(R.id.vpRole);

        tlRole =(TabLayout)findViewById(R.id.tlRole);
        tlRole.setupWithViewPager(vpRole);

        GetTabRole getTabRole = new GetTabRole();
        getTabRole.execute();
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

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.admin_role, menu);
        return true;
    }

    @Override

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Admin_home)
        {
            Intent i = new Intent(this, AdminHomeActivity.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_Admin_user)
        {
            Intent i = new Intent(this, AddUserActivity.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_Admin_student)
        {
            Intent i = new Intent(this, AddStudentActivity.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_Admin_attendence)
        {
            Intent i=new Intent(getApplicationContext(), AttendenceStandardActivity.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_Admin_progessreport)
        {
            Intent i = new Intent(this,ProgressReportActivity.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_Admin_noticeboard)
        {
            Intent i = new Intent(this, AdminNoticeBoardActivity.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_Admin_evaluation)
        {
            Intent i = new Intent(this, EvaluationActivity.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_Admin_education)
        {
            Intent i = new Intent(this, EducationActivity.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_Admin_environment)
        {
            Intent i = new Intent(this, EnvironmentActivity.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_Admin_emotional)
        {
            Intent i = new Intent(this, Emotional_EvaluationActivity.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_Admin_activity)
        {
            Intent i = new Intent(this, AdminEventActivity.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_Admin_logout)
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
	private class GetTabRole extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... params) {

            String response;
            HttpHandler h=new HttpHandler();
            response= h.serverConnection(MainActivity.mainUrl+"roleView");
            if(response!=null)
            {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray roleArr=jsonObject.getJSONArray("role");
                    for (int i=0;i<roleArr.length();i++)
                    {
                        JSONObject j=roleArr.getJSONObject(i);

                        String id=j.getString("id");
                        String name=j.getString("name");

                        roleArrListId.add(id);
                        roleArrListName.add(name);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            adapter = new PagerRole(getSupportFragmentManager());
            for (int i = 0; i < roleArrListName.size(); i++) {
                adapter.addFrag(new RoleFragment(), roleArrListName.get(i).trim());
            }
            vpRole.setAdapter(adapter);
        }
    }
}
