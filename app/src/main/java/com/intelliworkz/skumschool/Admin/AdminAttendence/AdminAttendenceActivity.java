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
import android.widget.ImageButton;
import android.widget.TextView;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class AdminAttendenceActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView rlAttendStudList;
    Button btnAddAttend;
    ArrayList<HashMap<String,String>> stuArrList=new ArrayList<>();
    String pos;
    ImageButton previousButton,nextButton;
    TextView txtDate;
    String currentDate;

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

        pos=getIntent().getExtras().getString("pos");

        previousButton = (ImageButton)findViewById(R.id.previousButton);
        nextButton = (ImageButton)findViewById(R.id.nextButton);
        txtDate = (TextView)findViewById(R.id.txtDate);

        final Calendar c = Calendar.getInstance();

        final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        currentDate = dateFormat.format(c.getTime());

        final DateFormat df = new SimpleDateFormat("dd - MM - yyyy");
        txtDate.setText(df.format(c.getTime()));

        GetAttendStudentList getAttendStudentList=new GetAttendStudentList(currentDate);
        getAttendStudentList.execute();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                c.add(Calendar.DATE, 1);
                currentDate = dateFormat.format(c.getTime());

                final DateFormat df = new SimpleDateFormat("dd - MM - yyyy");
                txtDate.setText(df.format(c.getTime()));

                GetAttendStudentList getAttendStudentList=new GetAttendStudentList(currentDate);
                getAttendStudentList.execute();

                /*Toast.makeText(getApplicationContext(),currentDate,Toast.LENGTH_LONG).show();*/
            }
        });

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                c.add(Calendar.DATE, -1);
                currentDate = dateFormat.format(c.getTime());

                final DateFormat df = new SimpleDateFormat("dd - MM - yyyy");
                txtDate.setText(df.format(c.getTime()));

                GetAttendStudentList getAttendStudentList=new GetAttendStudentList(currentDate);
                getAttendStudentList.execute();

                /*Toast.makeText(getApplicationContext(),currentDate,Toast.LENGTH_LONG).show();*/
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
        String currentDate;

        public GetAttendStudentList(String currentDate) {
            this.currentDate = currentDate;
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(AdminAttendenceActivity.this);
            dialog.setMessage("Loading...");
            dialog.setCancelable(true);
            dialog.show();

        }

        @Override
        protected String doInBackground(String... params) {

            stuArrList.clear();
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
                RecyclerView.Adapter rvNewsAdapter=new ViewAttendStudAdapter(getApplicationContext(),stuArrList,currentDate);
                rlAttendStudList.setAdapter(rvNewsAdapter);
            }
        }
    }
}
