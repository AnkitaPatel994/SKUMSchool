package com.intelliworkz.skumschool.Admin.AdminStudent;

import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
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
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.intelliworkz.skumschool.Admin.AdminAttendence.AdminAttendenceActivity;
import com.intelliworkz.skumschool.Admin.AdminRole.AdminRoleActivity;
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

    ArrayList<String> mediumArr=new ArrayList<>();
    ArrayList<String> mediumArr1=new ArrayList<>();
    String classStud,medium;

    TextInputLayout edinput_layout_name,edinput_layout_class,edinput_layout_RNo,edinput_layout_medium,edinput_layout_Address,
            edinput_layout_PCode,edinput_layout_DOB,edinput_layout_AddmissionDate,edinput_layout_GRNo,
            edinput_layout_VanNo,edinput_layout_RPhone,edinput_layout_OPhone,edinput_layout_Fathername,
            edinput_layout_FOccup,edinput_layout_FMoNo, edinput_layout_Femail,edinput_layout_Mothername,
            edinput_layout_MOccup,edinput_layout_MMoNo,edinput_layout_MEmail;

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

        edinput_layout_name=(TextInputLayout)findViewById(R.id.edinput_layout_name);
        edinput_layout_class=(TextInputLayout)findViewById(R.id.edinput_layout_class);
        edinput_layout_RNo=(TextInputLayout)findViewById(R.id.edinput_layout_RNo);
        edinput_layout_medium=(TextInputLayout)findViewById(R.id.edinput_layout_medium);
        edinput_layout_Address=(TextInputLayout)findViewById(R.id.edinput_layout_Address);
        edinput_layout_PCode=(TextInputLayout)findViewById(R.id.edinput_layout_PCode);
        edinput_layout_DOB=(TextInputLayout)findViewById(R.id.edinput_layout_DOB);
        edinput_layout_AddmissionDate=(TextInputLayout)findViewById(R.id.edinput_layout_AddmissionDate);
        edinput_layout_GRNo=(TextInputLayout)findViewById(R.id.edinput_layout_GRNo);
        edinput_layout_VanNo=(TextInputLayout)findViewById(R.id.edinput_layout_VanNo);
        edinput_layout_RPhone=(TextInputLayout)findViewById(R.id.edinput_layout_RPhone);
        edinput_layout_OPhone=(TextInputLayout)findViewById(R.id.edinput_layout_OPhone);
        edinput_layout_Fathername=(TextInputLayout)findViewById(R.id.edinput_layout_FOccup);
        edinput_layout_FOccup=(TextInputLayout)findViewById(R.id.edinput_layout_FOccup);
        edinput_layout_FMoNo=(TextInputLayout)findViewById(R.id.edinput_layout_FMoNo);
        edinput_layout_Femail=(TextInputLayout)findViewById(R.id.edinput_layout_Femail);
        edinput_layout_Mothername=(TextInputLayout)findViewById(R.id.edinput_layout_Mothername);
        edinput_layout_MOccup=(TextInputLayout)findViewById(R.id.edinput_layout_MOccup);
        edinput_layout_MMoNo=(TextInputLayout)findViewById(R.id.edinput_layout_MMoNo);
        edinput_layout_MEmail=(TextInputLayout)findViewById(R.id.edinput_layout_MEmail);

        edtxtAddStdName.addTextChangedListener(new MyTextWatcher(edtxtAddStdName));
        edtxtAddRNo.addTextChangedListener(new MyTextWatcher(edtxtAddRNo));
        edtxtAddAddress.addTextChangedListener(new MyTextWatcher(edtxtAddAddress));
        edtxtAddPcode.addTextChangedListener(new MyTextWatcher(edtxtAddPcode));
        edtxtAddDOB.addTextChangedListener(new MyTextWatcher(edtxtAddDOB));
        edtxtAddAddmissionDate.addTextChangedListener(new MyTextWatcher(edtxtAddAddmissionDate));
        edtxtAddGRNo.addTextChangedListener(new MyTextWatcher(edtxtAddGRNo));
        edtxtAddVanNo.addTextChangedListener(new MyTextWatcher(edtxtAddVanNo));
        edtxtAddFathername.addTextChangedListener(new MyTextWatcher(edtxtAddFathername));
        edtxtAddFOccup.addTextChangedListener(new MyTextWatcher(edtxtAddFOccup));
        edtxtAddFMono.addTextChangedListener(new MyTextWatcher(edtxtAddFMono));
        edtxtAddFemail.addTextChangedListener(new MyTextWatcher(edtxtAddFemail));
        edtxtAddMothername.addTextChangedListener(new MyTextWatcher(edtxtAddMothername));
        edtxtAddMMono.addTextChangedListener(new MyTextWatcher(edtxtAddMMono));
        edtxtAddMOccup.addTextChangedListener(new MyTextWatcher(edtxtAddMOccup));



        sid=getIntent().getExtras().getString("sid");

        GetStd  getStd=new GetStd();
        getStd.execute();

        GetStudDetail getStudDetail=new GetStudDetail();
        getStudDetail.execute();

        /*Medium DropDown Code Start*/
        mediumArr.add("ENG");
        mediumArr.add("GUJ");

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(EditStudentActivity.this,
                        android.R.layout.simple_spinner_item, mediumArr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edspnMedium.setAdapter(adapter);

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

                if(name.equals(""))
                {
                    validateStdName();
                }
                else if(rollno.equals(""))
                {
                    validateRollNo();
                }
                else if(medium.equals("Select Medium"))
                {
                    validateMedium();
                }
                else if(address.equals(""))
                {
                    validateAddress();
                }
                else if(pincode.equals(""))
                {
                    validatePincode();
                }
                else if(bod.equals(""))
                {
                    validateDOB();
                }
                else if(addmissionDate.equals(""))
                {
                    validateAddmissionDate();
                }
                else if(gr_no.equals(""))
                {
                    validateGrNo();
                }
                else if(van_no.equals(""))
                {
                    validateVanNo();
                }
                else if(f_name.equals(""))
                {
                    validateFatherName();
                }
                else if(f_occupation.equals(""))
                {
                    validateFoccup();
                }
                else if(f_mobile.equals(""))
                {
                    validateFmono();
                }
                else if(f_emailid.equals(""))
                {
                    validateFemail();
                }
                else if(m_name.equals(""))
                {
                    validateMname();
                }
                else if(m_occupation.equals(""))
                {
                    validateMoccup();
                }
                else if(m_mobile.equals(""))
                {
                    validateMmno();
                }
                else
                {
                    EditStudent editStudent=new EditStudent(name,classStud,rollno,medium,address,pincode,bod,addmissionDate,gr_no,van_no,re_mobile,of_mobile, f_name,f_occupation,f_mobile,f_emailid,m_name,m_occupation,m_mobile,m_emailid);
                    editStudent.execute();
                }



            }
        });
    }

    private boolean validateStdName() {
        if (edtxtAddStdName.getText().toString().trim().isEmpty())
        {
            edinput_layout_name.setError(getString(R.string.err_msg_stdname));
            requestFocus(edtxtAddStdName);
            return false;
        }
        else
        {
            edinput_layout_name.setErrorEnabled(false);
        }
        return true;
    }
    private boolean validateClasdStud() {
        if (classStud.equals("Select Standard"))
        {
            /*input_layout_medium.setError(getString(R.string.err_msg_rno));
            requestFocus(spnMedium);*/
            Toast.makeText(getApplicationContext(),"Please select Standard",Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            edinput_layout_class.setErrorEnabled(false);
        }
        return true;
    }
    private boolean validateRollNo() {
        if (edtxtAddRNo.getText().toString().trim().isEmpty())
        {
            edinput_layout_RNo.setError(getString(R.string.err_msg_rno));
            requestFocus(edtxtAddRNo);
            return false;
        }
        else
        {
            edinput_layout_RNo.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateMedium() {

        if (medium.equals("Select Medium"))
        {
            /*input_layout_medium.setError(getString(R.string.err_msg_rno));
            requestFocus(spnMedium);*/
            Toast.makeText(getApplicationContext(),"Please select Medium",Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            edinput_layout_medium.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateAddress () {
        if (edtxtAddAddress.getText().toString().trim().isEmpty())
        {
            edinput_layout_Address.setError(getString(R.string.err_msg_address));
            requestFocus(edtxtAddAddress);
            return false;
        }
        else
        {
            edinput_layout_Address.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePincode () {
        String MobilePattern = "[0-9]{6}";
        if(!edtxtAddPcode.getText().toString().matches(MobilePattern))
        {
            edinput_layout_PCode.setError(getString(R.string.err_msg_validpno));
            requestFocus(edtxtAddPcode);
            return false;
        }
        else
        {
            edinput_layout_PCode.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateDOB () {
        if (edtxtAddDOB.getText().toString().trim().isEmpty())
        {
            edinput_layout_DOB.setError(getString(R.string.err_msg_dob));
            requestFocus(edtxtAddDOB);
            return false;
        }
        else
        {
            edinput_layout_DOB.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateAddmissionDate () {
        if (edtxtAddAddmissionDate.getText().toString().trim().isEmpty())
        {
            edinput_layout_AddmissionDate.setError(getString(R.string.err_msg_admissiondate));
            requestFocus(edtxtAddAddmissionDate);
            return false;
        }
        else
        {
            edinput_layout_AddmissionDate.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateGrNo () {
        if (edtxtAddGRNo.getText().toString().trim().isEmpty())
        {
            edinput_layout_GRNo.setError(getString(R.string.err_msg_grno));
            requestFocus(edtxtAddGRNo);
            return false;
        }
        else
        {
            edinput_layout_GRNo.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateVanNo() {
        if (edtxtAddVanNo.getText().toString().trim().isEmpty())
        {
            edinput_layout_VanNo.setError(getString(R.string.err_msg_vanno));
            requestFocus(edtxtAddVanNo);
            return false;
        }
        else
        {
            edinput_layout_VanNo.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateFatherName() {
        if (edtxtAddFathername.getText().toString().trim().isEmpty())
        {
            edinput_layout_Fathername.setError(getString(R.string.err_msg_fname));
            requestFocus(edtxtAddFathername);
            return false;
        }
        else
        {
            edinput_layout_Fathername.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateFoccup() {
        if (edtxtAddFOccup.getText().toString().trim().isEmpty())
        {
            edinput_layout_FOccup.setError(getString(R.string.err_msg_foccup));
            requestFocus(edtxtAddFOccup);
            return false;
        }
        else
        {
            edinput_layout_FOccup.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateFmono() {
        String MobilePattern = "[0-9]{10}";
        if(!edtxtAddFMono.getText().toString().matches(MobilePattern))
        {
            edinput_layout_FMoNo.setError(getString(R.string.err_msg_validmono));
            requestFocus(edtxtAddFMono);
            return false;
        }
        else
        {
            edinput_layout_FMoNo.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateFemail() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (!edtxtAddFemail.getText().toString().matches(emailPattern))
        {
            edinput_layout_Femail.setError(getString(R.string.err_msg_email));
            requestFocus(edtxtAddFemail);
            return false;
        }
        else
        {
            edinput_layout_Femail.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateMname() {
        if (edtxtAddMothername.getText().toString().trim().isEmpty())
        {
            edinput_layout_Mothername.setError(getString(R.string.err_msg_mname));
            requestFocus(edtxtAddMothername);
            return false;
        }
        else
        {
            edinput_layout_Mothername.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateMoccup() {
        if (edtxtAddMOccup.getText().toString().trim().isEmpty())
        {
            edinput_layout_MOccup.setError(getString(R.string.err_msg_moccup));
            requestFocus(edtxtAddMOccup);
            return false;
        }
        else
        {
            edinput_layout_MOccup.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateMmno() {

        String MobilePattern = "[0-9]{10}";
        if(!edtxtAddMMono.getText().toString().matches(MobilePattern))
        {
            edinput_layout_MMoNo.setError(getString(R.string.err_msg_validmono));
            requestFocus(edtxtAddMMono);
            return false;
        }
        else
        {
            edinput_layout_MMoNo.setErrorEnabled(false);
        }
        return true;
    }
    private void requestFocus(View v) {
        if (v.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
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
                Intent i = new Intent(getApplicationContext(),StudentDetailActivity.class);
                i.putExtra("id",sid);
                startActivity(i);
                finish();
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
            String response;
            HttpHandler h=new HttpHandler();
            response= h.serverConnection(MainActivity.mainUrl+"classdiv");
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
            ArrayAdapter<String> adapter =
                    new ArrayAdapter<String>(EditStudentActivity.this,
                            android.R.layout.simple_spinner_item, stdArrList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            edspnStd.setAdapter(adapter);
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

    private class MyTextWatcher implements TextWatcher {
        private View view;
        public MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (view.getId()) {
                case R.id.edtxtAddStdName:
                    validateStdName();
                    break;
                case R.id.edtxtAddRNo:
                    validateRollNo();
                    break;
                case R.id.edtxtAddAddress:
                    validateAddress();
                    break;
                case R.id.edtxtAddPcode:
                    validatePincode();
                    break;
                case R.id.edtxtAddDOB:
                    validateDOB();
                    break;
                case R.id.edtxtAddAddmissionDate:
                    validateAddmissionDate();
                    break;
                case R.id.edtxtAddGRNo:
                    validateGrNo();
                    break;
                case R.id.edtxtAddVanNo:
                    validateVanNo();
                    break;
                case R.id.edtxtAddFathername:
                    validateFatherName();
                    break;
                case R.id.edtxtAddFOccup:
                    validateFoccup();
                    break;
                case R.id.edtxtAddFMono:
                    validateFmono();
                    break;
                case R.id.edtxtAddFemail:
                    validateFemail();
                    break;
                case R.id.edtxtAddMothername:
                    validateMname();
                    break;
                case R.id.edtxtAddMOccup:
                    validateMoccup();
                    break;
                case R.id.edtxtAddMMono:
                    validateMmno();
                    break;
            }
        }
    }
}
