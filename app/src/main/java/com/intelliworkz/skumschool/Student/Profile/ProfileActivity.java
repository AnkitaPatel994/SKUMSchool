package com.intelliworkz.skumschool.Student.Profile;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
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
import android.widget.Toast;

import com.intelliworkz.skumschool.Admin.AdminAttendence.AdminAttendenceActivity;
import com.intelliworkz.skumschool.Admin.AdminRole.AdminRoleActivity;
import com.intelliworkz.skumschool.Student.Attendence.AttendenceActivity;
import com.intelliworkz.skumschool.Student.Calender.CalenderActivity;
import com.intelliworkz.skumschool.ChangePassword.ChangePasswordActivity;

import com.intelliworkz.skumschool.Student.Education.EducationActivity;
import com.intelliworkz.skumschool.Student.Emotional_Evaluation.Emotional_EvaluationActivity;
import com.intelliworkz.skumschool.Student.Environment.EnvironmentActivity;
import com.intelliworkz.skumschool.Student.Evaluation.EvaluationActivity;
import com.intelliworkz.skumschool.Student.Home.HomeActivity;
import com.intelliworkz.skumschool.Login.LoginActivity;
import com.intelliworkz.skumschool.Student.NoticeBoard.NoticeBoardActivity;
import com.intelliworkz.skumschool.Postdata;
import com.intelliworkz.skumschool.Student.ProgressReport.ProgressReportActivity;
import com.intelliworkz.skumschool.R;
import com.intelliworkz.skumschool.SplashScreen.MainActivity;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    CircleImageView profile_image;
    TextView txtMoEmail,txtStudentName,txtClass,txtRollNo,txtMedium,txtAddress,txtBOD,txtAddmissionDate,txtFatherName,txtOccupation,txtMobile,txtEmail,txtGRNo,txtVanNo,txtMotherName,txtMOccupation,txtMMobile,txtMEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
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

        /*-----------------Start Profile Activity Page--------------------*/

        txtMoEmail = (TextView) findViewById(R.id.txtMoEmail);

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

        GetProfileList profileList = new GetProfileList();
        profileList.execute();

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

    private class GetProfileList extends AsyncTask<String,Void,String> {
        String status,message;
        String StudentName,pic,Class,Rollno,Medium,Address,BOD,GRNo,VanNo,AddmissionDate,FatherName,Occupation,Mobile,Email,MotherName,MOccupation,MMobile,MEmail;
        @Override
        protected String doInBackground(String... params) {

            JSONObject stuList=new JSONObject();
            try {
                stuList.put("profile_id","1");
                Postdata postdata=new Postdata();
                String stuPd=postdata.post(MainActivity.mainUrl+"profile",stuList.toString());
                JSONObject j=new JSONObject(stuPd);
                status=j.getString("status");
                if(status.equals("1"))
                {
                    Log.d("Like","Successfully");
                    message = j.getString("message");
                    JSONObject jo=j.getJSONObject("profile");

                    StudentName = jo.getString("name");
                    pic =  jo.getString("pic");
                    Class = jo.getString("class");
                    Rollno = jo.getString("rollno");
                    Medium = jo.getString("medium");
                    Address = jo.getString("address");
                    BOD = jo.getString("bod");
                    AddmissionDate = jo.getString("addmissionDate");
                    GRNo = jo.getString("gr_no");
                    VanNo = jo.getString("van_no");
                    FatherName = jo.getString("f_name");
                    Occupation = jo.getString("f_occupation");
                    Mobile = jo.getString("f_mobile");
                    Email = jo.getString("f_emailid");
                    MotherName = jo.getString("m_name");
                    MOccupation = jo.getString("m_occupation");
                    MMobile = jo.getString("m_mobile");
                    MEmail = jo.getString("m_emailid");

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
                if(MEmail.equals(""))
                {
                    txtMEmail.setVisibility(View.GONE);
                    txtMoEmail.setVisibility(View.GONE);
                }
                else
                {
                    txtMEmail.setText(MEmail);
                }
                txtMEmail.setText(MEmail);
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

                DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                        .cacheOnDisc(true).cacheInMemory(true)
                        .imageScaleType(ImageScaleType.EXACTLY)
                        .displayer(new FadeInBitmapDisplayer(300)).build();
                final ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(ProfileActivity.this)
                        .defaultDisplayImageOptions(defaultOptions)
                        .memoryCache(new WeakMemoryCache())
                        .discCacheSize(100 * 1024 * 1024).build();

                ImageLoader.getInstance().init(config);

                ImageLoader imageLoader = ImageLoader.getInstance();
                int fallback = 0;
                DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                        .cacheOnDisc(true).resetViewBeforeLoading(true)
                        .showImageForEmptyUri(fallback)
                        .showImageOnFail(fallback)
                        .showImageOnLoading(fallback).build();

                imageLoader.displayImage(pic,profile_image, options);
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Not record",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
