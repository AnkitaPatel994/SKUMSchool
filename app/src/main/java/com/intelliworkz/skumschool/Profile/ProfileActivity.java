package com.intelliworkz.skumschool.Profile;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Html;
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
import android.widget.TextView;

import com.intelliworkz.skumschool.Attendence.AttendenceActivity;
import com.intelliworkz.skumschool.Calender.CalenderActivity;
import com.intelliworkz.skumschool.ChangePassword.ChangePasswordActivity;

import com.intelliworkz.skumschool.Education.EducationActivity;
import com.intelliworkz.skumschool.Emotional_Evaluation.Emotional_EvaluationActivity;
import com.intelliworkz.skumschool.Environment.EnvironmentActivity;
import com.intelliworkz.skumschool.Evaluation.EvaluationActivity;
import com.intelliworkz.skumschool.Home.HomeActivity;
import com.intelliworkz.skumschool.Login.LoginActivity;
import com.intelliworkz.skumschool.NoticeBoard.NoticeBoardActivity;
import com.intelliworkz.skumschool.ProgressReport.ProgressReportActivity;
import com.intelliworkz.skumschool.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    CircleImageView profile_image;
    TextView txtStudentName,txtClass,txtRollNo,txtMedium,txtAddress,txtBOD,txtAddmissionDate,txtFatherName,txtOccupation,txtMobile,txtEmail,txtGRNo,txtVanNo,txtMotherName,txtMOccupation,txtMMobile,txtMEmail;
    String StudentName,Class,Rollno,Medium,Address,BOD,GRNo,VanNo,AddmissionDate,FatherName,Occupation,Mobile,Email,MotherName,MOccupation,MMobile,MEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.app_title);


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

        /*-----------------Start Profile Activity Page--------------------*/

        profile_image = (CircleImageView) findViewById(R.id.profile_image);
        txtStudentName = (TextView) findViewById(R.id.txtStudentName);
        txtClass = (TextView) findViewById(R.id.txtClass);
        txtRollNo = (TextView) findViewById(R.id.txtRollNo);
        txtMedium = (TextView) findViewById(R.id.txtMedium);
        txtAddress = (TextView) findViewById(R.id.txtAddress);
        txtBOD = (TextView) findViewById(R.id.txtBOD);
        txtAddmissionDate = (TextView) findViewById(R.id.txtAddmissionDate);
        txtGRNo = (TextView) findViewById(R.id.txtGRNo);
        txtVanNo = (TextView) findViewById(R.id.txtVanNo);
        txtFatherName = (TextView) findViewById(R.id.txtFatherName);
        txtOccupation = (TextView) findViewById(R.id.txtOccupation);
        txtMobile = (TextView) findViewById(R.id.txtMobile);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtMotherName = (TextView) findViewById(R.id.txtMotherName);
        txtMOccupation = (TextView) findViewById(R.id.txtMOccupation);
        txtMMobile = (TextView) findViewById(R.id.txtMMobile);
        txtMEmail = (TextView) findViewById(R.id.txtMEmail);

        StudentName = "Ankita Patel";
        Class ="7D";
        Rollno = "75";
        Medium = "ENG";
        Address = "Plot no-721, Ekata Colony, Sector-27, Gandhinagar, Gujarat - 282027";
        BOD = "15 March 1994";
        GRNo = "G. J. 18";
        VanNo = "155036";
        AddmissionDate = "26 June 2017";
        FatherName = "Pravinbhai Dahyabhai Patel";
        Occupation = "Business";
        Mobile = "9925740359";
        Email = "patelankita994@gmail.com";
        MotherName = "Patel Parvatiben Pravinbhai";
        MOccupation = "Business";
        MMobile = "9925740359";
        MEmail = "patelankita994@gmail.com";

        txtStudentName.setText(StudentName);
        txtClass.setText(Class);
        txtRollNo.setText(Rollno);
        txtMedium.setText(Medium);
        txtAddress.setText(Address);
        txtBOD.setText(BOD);
        txtGRNo.setText(GRNo);
        txtVanNo.setText(VanNo);
        txtAddmissionDate.setText(AddmissionDate);
        txtFatherName.setText(FatherName);
        txtOccupation.setText(Occupation);
        txtMobile.setText(Mobile);
        txtEmail.setText(Email);
        txtMotherName.setText(MotherName);
        txtMOccupation.setText(MOccupation);
        txtMMobile.setText(MMobile);
        txtMEmail.setText(MEmail);

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
        else if (id == R.id.nav_chgpass)
        {
            Intent i = new Intent(this, ChangePasswordActivity.class);
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
}
