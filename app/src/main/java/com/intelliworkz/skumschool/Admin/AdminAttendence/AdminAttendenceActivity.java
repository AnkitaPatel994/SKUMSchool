package com.intelliworkz.skumschool.Admin.AdminAttendence;

import android.app.ProgressDialog;
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
import android.widget.Button;
import android.widget.Toast;

import com.intelliworkz.skumschool.Admin.AdminRole.AdminRoleActivity;
import com.intelliworkz.skumschool.Login.LoginActivity;
import com.intelliworkz.skumschool.Postdata;
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
import java.util.HashMap;

public class AdminAttendenceActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView rlAttendStudList;
    Button btnAddAttend;
    ArrayList<HashMap<String,String>> stuArrList=new ArrayList<>();
    String pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_attendence);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        rlAttendStudList=(RecyclerView)findViewById(R.id.rlAttendStudList);
        rlAttendStudList.setHasFixedSize(true);

        RecyclerView.LayoutManager rvLayoutManager=new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);
        rlAttendStudList.setLayoutManager(rvLayoutManager);

        GetAttendStudentList getAttendStudentList=new GetAttendStudentList();
        getAttendStudentList.execute();

        pos=getIntent().getExtras().getString("pos");
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
        else if (id == R.id.nav_role)
        {
            Intent i = new Intent(this, AdminRoleActivity.class);
            startActivity(i);
            finish();
        }
        else if (id == R.id.nav_attendence)
        {
            Intent i = new Intent(this, AdminAttendenceActivity.class);
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
    private class GetAttendStudentList extends AsyncTask<String,Void,String> {
        String status,message;
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(AdminAttendenceActivity.this);
            dialog.setMessage("Loading...");
            dialog.setCancelable(true);
            dialog.show();

        }

        @Override
        protected String doInBackground(String... params) {
            JSONObject studList=new JSONObject();
            try {
                studList.put("class",pos);
                Postdata postdata=new Postdata();
                String stuPd=postdata.post(MainActivity.mainUrl+"studentFatch",studList.toString());
                JSONObject j=new JSONObject(stuPd);
                status=j.getString("status");
                if(status.equals("1"))
                {
                    Log.d("Like","Successfully");
                    message = j.getString("message");
                    JSONArray jarr=j.getJSONArray("Student");
                    for(int i=0;i<jarr.length();i++)
                    {
                        HashMap<String,String > stud = new HashMap<>();
                        JSONObject jobj=jarr.getJSONObject(i);

                        String id= String.valueOf(jobj.get("id"));
                        String name = jobj.getString("name");
                        String rollno = jobj.getString("rollno");
                        String classStud = jobj.getString("class");

                        stud.put("id",id);
                        stud.put("name",name);
                        stud.put("rollno",rollno);
                        stud.put("class",classStud);

                        stuArrList.add(stud);
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
            dialog.dismiss();
            if(status.equals("1"))
            {
                RecyclerView.Adapter rvNewsAdapter=new ViewAttendStudAdapter(getApplicationContext(),stuArrList);
                rlAttendStudList.setAdapter(rvNewsAdapter);
            }
        }
    }


}
