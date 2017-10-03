package com.intelliworkz.skumschool.Admin.AdminAddStudent;

import android.app.DatePickerDialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.intelliworkz.skumschool.HttpHandler;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class EditStudentActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    EditText edtxtAddStdName,edtxtAddDOB,edtxtAddRNo,edtxtAddAddress,edtxtAddPcode,edtxtAddAddmissionDate,edtxtAddGRNo,edtxtAddVanNo,
            edtxtAddRPhone,edtxtAddOPhone,edtxtAddFathername,edtxtAddFOccup,edtxtAddFMono,edtxtAddFemail,edtxtAddMothername,edtxtAddMOccup,edtxtAddMMono,edtxtAddMEmail;

    Button btned;
    Spinner edspnStd,edspnMedium;
    String sid,StudentName,Classstd,Rollno,Medium,Address,Pincode,BOD,GRNo,VanNo,Rmno,Omno,AddmissionDate,FatherName,Occupation,Mobile,Email,MotherName,MOccupation,MMobile,MEmail;
    ArrayList<String> stdArrList=new ArrayList<>();
    String mainurl= MainActivity.mainUrl;
    ArrayList<String> mediumArr=new ArrayList<>();
    ArrayList<String> mediumArr1=new ArrayList<>();
    String classStud,medium;

    //Date Picker
    Calendar myCalendar = Calendar.getInstance();
    String dateFormat = "dd MMMM yyyy";
    DatePickerDialog.OnDateSetListener date;
    DatePickerDialog.OnDateSetListener dateAddmission;
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        btned=(Button)findViewById(R.id.btned);
        edspnStd=(Spinner)findViewById(R.id.edspnStd);
        edspnMedium=(Spinner)findViewById(R.id.edspnMedium);

        edtxtAddStdName=(EditText)findViewById(R.id.edtxtAddStdName);
        edtxtAddRNo=(EditText)findViewById(R.id.edtxtAddRNo);
        edtxtAddAddress=(EditText)findViewById(R.id.edtxtAddAddress);
        edtxtAddPcode=(EditText)findViewById(R.id.edtxtAddPcode);
        edtxtAddDOB=(EditText)findViewById(R.id.edtxtAddDOB);
        edtxtAddAddmissionDate=(EditText)findViewById(R.id.edtxtAddAddmissionDate);
        edtxtAddGRNo=(EditText)findViewById(R.id.edtxtAddGRNo);
        edtxtAddVanNo=(EditText)findViewById(R.id.edtxtAddVanNo);
        edtxtAddRPhone=(EditText)findViewById(R.id.edtxtAddRPhone);
        edtxtAddOPhone=(EditText)findViewById(R.id.edtxtAddOPhone);
        edtxtAddFathername=(EditText)findViewById(R.id.edtxtAddFathername);
        edtxtAddFOccup=(EditText)findViewById(R.id.edtxtAddFOccup);
        edtxtAddFMono=(EditText)findViewById(R.id.edtxtAddFMono);
        edtxtAddFemail=(EditText)findViewById(R.id.edtxtAddFemail);
        edtxtAddMothername=(EditText)findViewById(R.id.edtxtAddMothername);
        edtxtAddMOccup=(EditText)findViewById(R.id.edtxtAddMOccup);
        edtxtAddMMono=(EditText)findViewById(R.id.edtxtAddMMono);
        edtxtAddMEmail=(EditText)findViewById(R.id.edtxtAddMEmail);


        String name=edtxtAddStdName.getText().toString();
        String rollno=edtxtAddRNo.getText().toString();
        String address=edtxtAddAddress.getText().toString();
        String pincode=edtxtAddPcode.getText().toString();
        String bod=edtxtAddDOB.getText().toString();
        String addmissionDate=edtxtAddAddmissionDate.getText().toString();
        String gr_no=edtxtAddGRNo.getText().toString();
        String van_no=edtxtAddVanNo.getText().toString();
        String re_mobile=edtxtAddRNo.getText().toString();
        String of_mobile=edtxtAddOPhone.getText().toString();
        String f_name=edtxtAddFathername.getText().toString();
        String f_occupation=edtxtAddFOccup.getText().toString();
        String f_mobile=edtxtAddFMono.getText().toString();
        String f_emailid=edtxtAddFemail.getText().toString();
        String m_name=edtxtAddMothername.getText().toString();
        String m_mobile=edtxtAddMMono.getText().toString();
        String m_occupation=edtxtAddMOccup.getText().toString();
        String m_emailid=edtxtAddMEmail.getText().toString();


        sid=getIntent().getExtras().getString("sid");

        GetStd  getStd=new GetStd();
        getStd.execute();

        GetStudDetail getStudDetail=new GetStudDetail();
        getStudDetail.execute();

        /*Medium DropDown Code Start*/
        mediumArr.add("ENG");
        mediumArr.add("GUJ");

        ArrayAdapter<String> ad = new ArrayAdapter<String>(EditStudentActivity.this, android.R.layout.simple_spinner_item, mediumArr);
        edspnMedium.setAdapter(ad);
        Medium=getIntent().getExtras().getString("Medium");

        for(int i=0;i<mediumArr.size();i++)
        {
            if(Medium.equals(mediumArr.get(i)))
            {
                edspnMedium.setSelection(i);
            }
        }
         /*Medium DropDown Code Stop*/
        edspnStd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                classStud= String.valueOf(edspnStd.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        edspnMedium.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                medium= String.valueOf(edspnMedium.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //Date Picker Code
        date=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDate();
            }
        };
        edtxtAddDOB.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 new DatePickerDialog(EditStudentActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
             }
         });

        dateAddmission=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateAddDate();
            }
        };
        edtxtAddAddmissionDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(EditStudentActivity.this,dateAddmission,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        btned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=edtxtAddStdName.getText().toString();
                String rollno=edtxtAddRNo.getText().toString();
                String address=edtxtAddAddress.getText().toString();
                String pincode=edtxtAddPcode.getText().toString();
                String bod=edtxtAddDOB.getText().toString();
                String addmissionDate=edtxtAddAddmissionDate.getText().toString();
                String gr_no=edtxtAddGRNo.getText().toString();
                String van_no=edtxtAddVanNo.getText().toString();
                String re_mobile=edtxtAddRNo.getText().toString();
                String of_mobile=edtxtAddOPhone.getText().toString();
                String f_name=edtxtAddFathername.getText().toString();
                String f_occupation=edtxtAddFOccup.getText().toString();
                String f_mobile=edtxtAddFMono.getText().toString();
                String f_emailid=edtxtAddFemail.getText().toString();
                String m_name=edtxtAddMothername.getText().toString();
                String m_mobile=edtxtAddMMono.getText().toString();
                String m_occupation=edtxtAddMOccup.getText().toString();
                String m_emailid=edtxtAddMEmail.getText().toString();

                EditStudent editStudent=new EditStudent(name,classStud,rollno,medium,address,pincode,bod,addmissionDate,gr_no,van_no,re_mobile,of_mobile, f_name,f_occupation,f_mobile,f_emailid,m_name,m_occupation,m_mobile,m_emailid);
                editStudent.execute();


            }
        });
    }

    private void updateAddDate() {
        edtxtAddAddmissionDate.setText(sdf.format(myCalendar.getTime()));
    }

    private void updateDate() {
        edtxtAddDOB.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.edit_student, menu);
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



    private class EditStudent extends AsyncTask<String,Void,String> {
        String status,message,id;
        String name,classStud,rollno,medium,address,pincode,bod,addmissionDate,gr_no,van_no,re_mobile,of_mobile, f_name,f_occupation,f_mobile,f_emailid,m_name,m_occupation,m_mobile,m_emailid;
        public EditStudent(String name, String classStud, String rollno, String medium, String address, String pincode, String bod, String addmissionDate,
                           String gr_no, String van_no, String re_mobile, String of_mobile, String f_name,
                           String f_occupation, String f_mobile, String f_emailid, String m_name, String m_occupation, String m_mobile, String m_emailid) {
                this.name=name;
                this.classStud=classStud;
                this.rollno=rollno;
                this.medium=medium;
                this.address=address;
                this.pincode=pincode;
                this.bod=bod;
                this.addmissionDate=addmissionDate;
                this.gr_no=gr_no;
                this.van_no=van_no;
                this.re_mobile=re_mobile;
                this.of_mobile=of_mobile;
                this.f_name=f_name;
                this.f_occupation=f_occupation;
                this.f_mobile=f_mobile;
                this.f_emailid=f_emailid;
                this.m_name=m_name;
                this.m_occupation=m_occupation;
                this.m_mobile=m_mobile;
                this.m_emailid=m_emailid;

        }

        @Override
        protected String doInBackground(String... params) {
            JSONObject stdList=new JSONObject();
            try {
                stdList.put("id",sid);
                stdList.put("name",name);
                stdList.put("class",classStud);
                stdList.put("rollno",rollno);
                stdList.put("medium",medium);
                stdList.put("address",address);
                stdList.put("pincode",pincode);
                stdList.put("bod",bod);
                stdList.put("addmissionDate",addmissionDate);
                stdList.put("gr_no",gr_no);
                stdList.put("van_no",van_no);
                stdList.put("re_mobile",re_mobile);
                stdList.put("of_mobile",of_mobile);
                stdList.put("f_name",f_name);
                stdList.put("f_occupation",f_occupation);
                stdList.put("f_mobile",f_mobile);
                stdList.put("f_emailid",f_emailid);
                stdList.put("m_name",m_name);
                stdList.put("m_mobile",m_mobile);
                stdList.put("m_occupation",m_occupation);
                stdList.put("m_emailid",m_emailid);


                Postdata postdata=new Postdata();
                String stdPd=postdata.post(MainActivity.mainUrl+"studentUpdate",stdList.toString());
                JSONObject j=new JSONObject(stdPd);
                status=j.getString("status");
                if(status.equals("1"))
                {
                    Log.d("Like","Successfully");
                    message = j.getString("message");
                }
                else
                {
                    message = j.getString("message");
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
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),status,Toast.LENGTH_SHORT).show();
            }

        }
    }

    private class GetStudDetail extends AsyncTask<String,Void,String> {
        String status,message;
        @Override
        protected String doInBackground(String... params) {
            JSONObject stuList=new JSONObject();
            try {
                stuList.put("profile_id",sid);
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
                    //pic =  jo.getString("pic");
                    Classstd = jo.getString("class");
                    Rollno = jo.getString("rollno");
                    Medium = jo.getString("medium");
                    Address = jo.getString("address");
                    Pincode=jo.getString("pincode");
                    BOD = jo.getString("bod");
                    AddmissionDate = jo.getString("addmissionDate");
                    GRNo = jo.getString("gr_no");
                    VanNo = jo.getString("van_no");
                    Rmno=jo.getString("re_mobile");
                    Omno=jo.getString("of_mobile");
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

            edtxtAddStdName.setText(StudentName);
            edtxtAddRNo.setText(Rollno);
            edtxtAddAddress.setText(Address);
            edtxtAddPcode.setText(Pincode);
            edtxtAddDOB.setText(BOD);
            edtxtAddAddmissionDate.setText(AddmissionDate);
            edtxtAddGRNo.setText(GRNo);
            edtxtAddVanNo.setText(VanNo);
            edtxtAddRPhone.setText(Rmno);
            edtxtAddOPhone.setText(Omno);
            edtxtAddFathername.setText(FatherName);
            edtxtAddFOccup.setText(Occupation);
            edtxtAddFMono.setText(Mobile);
            edtxtAddFemail.setText(Email);
            edtxtAddMothername.setText(MotherName);
            edtxtAddMOccup.setText(MOccupation);
            edtxtAddMMono.setText(MMobile);
            edtxtAddMEmail.setText(MEmail);

        }
    }

    private class GetStd extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... params) {
            stdArrList.add("Select Standard");
            String response;
            HttpHandler h=new HttpHandler();
            response= h.serverConnection(mainurl+"classdiv");
            if(response!=null)
            {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray stdArr=jsonObject.getJSONArray("class");
                    for (int i=0;i<stdArr.length();i++)
                    {
                        JSONObject j=stdArr.getJSONObject(i);

                        String classStd=j.getString("class");

                        stdArrList.add(classStd);
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
            ArrayAdapter<String> ad = new ArrayAdapter<String>(EditStudentActivity.this, android.R.layout.simple_spinner_item, stdArrList);
            edspnStd.setAdapter(ad);
            Classstd=getIntent().getExtras().getString("Class");

            for(int i=0;i<stdArrList.size();i++)
            {
                if(Classstd.equals(stdArrList.get(i)))
                {
                    edspnStd.setSelection(i);
                }
            }
        }
    }
}
