package com.intelliworkz.skumschool.Admin.AdminEvent;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
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

import com.intelliworkz.skumschool.Admin.AdminAttendence.AdminAttendenceActivity;
import com.intelliworkz.skumschool.Admin.AdminAttendence.AttendenceStandardActivity;
import com.intelliworkz.skumschool.Admin.AdminHome.AdminHomeActivity;
import com.intelliworkz.skumschool.Admin.AdminNoticeBoard.AdminNoticeBoardActivity;
import com.intelliworkz.skumschool.Admin.AdminNoticeBoard.Pager;
import com.intelliworkz.skumschool.Admin.AdminRole.AdminRoleActivity;
import com.intelliworkz.skumschool.Admin.AdminStudent.AddStudentActivity;
import com.intelliworkz.skumschool.Admin.AdminUser.AddUserActivity;
import com.intelliworkz.skumschool.Login.LoginActivity;
import com.intelliworkz.skumschool.R;
import com.intelliworkz.skumschool.Student.Calender.CalenderActivity;
import com.intelliworkz.skumschool.Student.Education.EducationActivity;
import com.intelliworkz.skumschool.Student.Emotional_Evaluation.Emotional_EvaluationActivity;
import com.intelliworkz.skumschool.Student.Environment.EnvironmentActivity;
import com.intelliworkz.skumschool.Student.Evaluation.EvaluationActivity;
import com.intelliworkz.skumschool.Student.Home.HomeActivity;
import com.intelliworkz.skumschool.Student.NoticeBoard.NoticeBoardActivity;
import com.intelliworkz.skumschool.Student.Profile.ProfileActivity;
import com.intelliworkz.skumschool.Student.ProgressReport.ProgressReportActivity;

public class    AdminEventActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TabLayout tabAdminEventLayout;
    ViewPager vpAdminEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_event);
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
        MenuItem menuItem = menu.getItem(11);
        menuItem.setChecked(true);


        vpAdminEvent = (ViewPager)findViewById(R.id.vpAdminEvent);
        setupViewPager(vpAdminEvent);

        tabAdminEventLayout =(TabLayout)findViewById(R.id.tabAdminEventLayout);
        tabAdminEventLayout.setupWithViewPager(vpAdminEvent);
    }

    private void setupViewPager(ViewPager viewPager){

        Pager adapter = new Pager(getSupportFragmentManager());

        adapter.addFrag(new AddEventFragment(),"Add Activity");
        adapter.addFrag(new ViewEventFragment(),"View Activity");

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
}
