package com.intelliworkz.skumschool.Admin.AdminStudent;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


//import com.intelliworkz.skumschool.Admin.SearchStudent.SearchStudActivity;
import com.intelliworkz.skumschool.Admin.AdminAttendence.AdminAttendenceActivity;
import com.intelliworkz.skumschool.Admin.AdminAttendence.AttendenceStandardActivity;
import com.intelliworkz.skumschool.Admin.AdminEvent.AdminEventActivity;
import com.intelliworkz.skumschool.Admin.AdminHome.AdminHomeActivity;
import com.intelliworkz.skumschool.Admin.AdminNoticeBoard.AdminNoticeBoardActivity;
import com.intelliworkz.skumschool.Admin.AdminRole.AdminRoleActivity;
import com.intelliworkz.skumschool.Admin.AdminUser.AddUserActivity;
import com.intelliworkz.skumschool.Student.Calender.CalenderActivity;
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

public class AddStudentActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TabLayout StudTab;
    ViewPager addStud_viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
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
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        StudTab=(TabLayout)findViewById(R.id.StudTab);
        addStud_viewPager=(ViewPager)findViewById(R.id.addStud_viewPager);

        setupViewPager(addStud_viewPager);
        StudTab.setupWithViewPager(addStud_viewPager);

    }

    private void setupViewPager(ViewPager viewPager){

        StudentPager adapter = new StudentPager(getSupportFragmentManager());

        adapter.addFrag(new AddStudentFragment(),"Add Student");

        viewPager.setAdapter(adapter);
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
        getMenuInflater().inflate(R.menu.search_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.search) {
            Intent i = new Intent(getApplicationContext(),SearchStudentActivity.class);
            startActivity(i);
            //Toast.makeText(getApplicationContext(),"hi",Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
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
        else if (id == R.id.nav_Admin_role)
        {
            Intent i = new Intent(this, AdminRoleActivity.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_Admin_user)
        {
            Intent i = new Intent(this, AddUserActivity.class);
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

}
