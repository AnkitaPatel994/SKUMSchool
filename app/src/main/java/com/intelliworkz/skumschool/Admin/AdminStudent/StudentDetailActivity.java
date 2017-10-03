package com.intelliworkz.skumschool.Admin.AdminStudent;

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

import org.json.JSONException;
import org.json.JSONObject;

public class StudentDetailActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView txtViewStudentName,txtViewMoEmail,txtViewClass,txtViewRollNo,txtViewMedium,txtViewAddress,txtViewBOD,txtViewAddmissionDate,txtViewFatherName,txtViewOccupation,txtViewMobile,txtViewEmail,txtViewGRNo,txtViewVanNo,txtViewMotherName,txtViewMOccupation,txtViewMMobile,txtViewMEmail;
    String sid,status,message,StudentName,pic,Class,Rollno,Medium,Address,Pincode,BOD,AddmissionDate,GRNo,VanNo,RMono,OMono,FatherName,Occupation,Mobile,Email,MotherName,MOccupation,MMobile,MEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        txtViewMoEmail = (TextView) findViewById(R.id.txtViewMoEmail);

        txtViewStudentName = (TextView) findViewById(R.id.txtViewStudentName);
        txtViewClass = (TextView) findViewById(R.id.txtViewClass);
        txtViewRollNo = (TextView) findViewById(R.id.txtViewRollNo);
        txtViewMedium = (TextView) findViewById(R.id.txtViewMedium);
        txtViewAddress = (TextView) findViewById(R.id.txtViewAddress);
        txtViewBOD = (TextView) findViewById(R.id.txtViewBOD);
        txtViewAddmissionDate = (TextView) findViewById(R.id.txtViewAddmissionDate);
        txtViewGRNo = (TextView) findViewById(R.id.txtViewGRNo);
        txtViewVanNo = (TextView) findViewById(R.id.txtViewVanNo);
        txtViewFatherName = (TextView) findViewById(R.id.txtViewFatherName);
        txtViewOccupation = (TextView) findViewById(R.id.txtViewOccupation);
        txtViewMobile = (TextView) findViewById(R.id.txtViewMobile);
        txtViewEmail = (TextView) findViewById(R.id.txtViewEmail);
        txtViewMotherName = (TextView) findViewById(R.id.txtViewMotherName);
        txtViewMOccupation = (TextView) findViewById(R.id.txtViewMOccupation);
        txtViewMMobile = (TextView) findViewById(R.id.txtViewMMobile);
        txtViewMEmail = (TextView) findViewById(R.id.txtViewMEmail);

        String id=getIntent().getExtras().getString("id");

        GetStudentDetail getStudentDetail = new GetStudentDetail(id);
        getStudentDetail.execute();

        //Toast.makeText(getApplicationContext(),"id"+id,Toast.LENGTH_SHORT).show();
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
        getMenuInflater().inflate(R.menu.edit_stud,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = new Intent(getApplicationContext(),EditStudentActivity.class);
        i.putExtra("sid",sid);
        i.putExtra("Medium",Medium);
        i.putExtra("Class",Class);
        startActivity(i);
      /*  i.putExtra("StudentName",StudentName);
        i.putExtra("Medium",Medium);
        i.putExtra("Class",Class);
        i.putExtra("Rollno",Rollno);
        i.putExtra("Address",Address);
        i.putExtra("Pincode",Pincode);
        i.putExtra("BOD",BOD);
        i.putExtra("AddmissionDate",AddmissionDate);
        i.putExtra("GRNo",GRNo);
        i.putExtra("VanNo",VanNo);
        i.putExtra("FatherName",FatherName);
        i.putExtra("Occupation",Occupation);
        i.putExtra("Email",Email);
        i.putExtra("MotherName",MotherName);
        i.putExtra("MOccupation",MOccupation);
        i.putExtra("MMobile",MMobile);
        i.putExtra("MEmail",MEmail);
        startActivity(i);*//*
        Toast.makeText(getApplicationContext(),sid,Toast.LENGTH_SHORT).show();*/
        return true;
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

    private class GetStudentDetail extends AsyncTask<String,Void,String> {

        //String id,status,message,StudentName,pic,Class,Rollno,Medium,Address,BOD,GRNo,VanNo,AddmissionDate,FatherName,Occupation,Mobile,Email,MotherName,MOccupation,MMobile,MEmail;
        String id;
        public GetStudentDetail(String id) {
            this.id = id;
        }

        @Override
        protected String doInBackground(String... params) {
            JSONObject stuList=new JSONObject();
            try {
                stuList.put("profile_id",id);
                Postdata postdata=new Postdata();
                String stuPd=postdata.post(MainActivity.mainUrl+"profile",stuList.toString());
                JSONObject j=new JSONObject(stuPd);
                status=j.getString("status");
                if(status.equals("1"))
                {
                    Log.d("Like","Successfully");
                    message = j.getString("message");
                    JSONObject jo=j.getJSONObject("profile");

                    sid=jo.getString("id");
                    StudentName = jo.getString("name");
                    //pic =  jo.getString("pic");
                    Class = jo.getString("class");
                    Rollno = jo.getString("rollno");
                    Medium = jo.getString("medium");
                    Address = jo.getString("address");
                    Pincode=jo.getString("pincode");
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

            if(status.equals("1")) {
                if (MEmail.equals("")) {
                    txtViewMEmail.setVisibility(View.GONE);
                    txtViewMoEmail.setVisibility(View.GONE);
                } else {
                    txtViewMEmail.setText(MEmail);
                }
                txtViewMEmail.setText(MEmail);
                txtViewStudentName.setText(StudentName);
                txtViewClass.setText(Class);
                txtViewRollNo.setText(Rollno);
                txtViewMedium.setText(Medium);
                txtViewAddress.setText(Address+Pincode);
                txtViewBOD.setText(BOD);
                txtViewGRNo.setText(GRNo);
                txtViewVanNo.setText(VanNo);
                txtViewAddmissionDate.setText(AddmissionDate);
                txtViewFatherName.setText(FatherName);
                txtViewOccupation.setText(Occupation);
                txtViewMobile.setText(Mobile);
                txtViewEmail.setText(Email);
                txtViewMotherName.setText(MotherName);
                txtViewMOccupation.setText(MOccupation);
                txtViewMMobile.setText(MMobile);
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Not record",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
