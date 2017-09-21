package com.intelliworkz.skumschool.Admin.AdminAddStudent;

import android.app.DatePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.intelliworkz.skumschool.HttpHandler;
import com.intelliworkz.skumschool.Student.Calender.CalenderActivity;
import com.intelliworkz.skumschool.ChangePassword.ChangePasswordActivity;
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
import com.intelliworkz.skumschool.SplashScreen.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class AddStudentActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Spinner spnStd,spnMedium;
    ArrayList<String> stdArrList=new ArrayList<>();
    ArrayList<String> stdArrList1=new ArrayList<>();
    String mainurl= MainActivity.mainUrl;
    String medium[]={"Select Medium","ENG","GUJ"};

    Button btnAdd;
    EditText txtAddStdName,txtAddDOB,txtAddRNo,txtAddAddress,txtAddPcode,txtAddAddmissionDate,txtAddGRNo,txtAddVanNo,
             txtAddRPhone,txtAddOPhone,txtAddFathername,txtAddFOccup,txtAddFMono,txtAddFemail,txtAddMothername,txtAddMOccup,txtAddMMono,txtAddMEmail;
    TextInputLayout input_layout_name,input_layout_RNo,input_layout_Address,input_layout_PCode,input_layout_DOB,input_layout_AddmissionDate,input_layout_GRNo,
            input_layout_VanNo,input_layout_RPhone,input_layout_OPhone,input_layout_Fathername,input_layout_FOccup,input_layout_FMoNo,
            input_layout_Femail,input_layout_Mothername,input_layout_MOccup,input_layout_MMoNo,input_layout_MEmail;
    //Date Picker
    Context context = this;
    Calendar myCalendar = Calendar.getInstance();
    String dateFormat = "dd MMMM yyyy";
    DatePickerDialog.OnDateSetListener date;
    DatePickerDialog.OnDateSetListener dateAddmission;
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
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

        spnStd=(Spinner)findViewById(R.id.spnStd);
        spnMedium=(Spinner)findViewById(R.id.spnMedium);
        btnAdd=(Button)findViewById(R.id.btnAdd);

        spnStd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int pos= Integer.parseInt(String.valueOf(spnStd.getItemIdAtPosition(position)));
                if(pos==0)
                {
                    Toast.makeText(getApplicationContext(),"Select",Toast.LENGTH_SHORT).show();
                }
                else
                {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        input_layout_name=(TextInputLayout)findViewById(R.id.input_layout_name);
        input_layout_RNo=(TextInputLayout)findViewById(R.id.input_layout_RNo);
        input_layout_Address=(TextInputLayout)findViewById(R.id.input_layout_Address);
        input_layout_PCode=(TextInputLayout)findViewById(R.id.input_layout_PCode);
        input_layout_DOB=(TextInputLayout)findViewById(R.id.input_layout_DOB);
        input_layout_AddmissionDate=(TextInputLayout)findViewById(R.id.input_layout_AddmissionDate);
        input_layout_GRNo=(TextInputLayout)findViewById(R.id.input_layout_GRNo);
        input_layout_VanNo=(TextInputLayout)findViewById(R.id.input_layout_VanNo);
        input_layout_RPhone=(TextInputLayout)findViewById(R.id.input_layout_RPhone);
        input_layout_OPhone=(TextInputLayout)findViewById(R.id.input_layout_OPhone);
        input_layout_Fathername=(TextInputLayout)findViewById(R.id.input_layout_FOccup);
        input_layout_FOccup=(TextInputLayout)findViewById(R.id.input_layout_FOccup);
        input_layout_FMoNo=(TextInputLayout)findViewById(R.id.input_layout_FMoNo);
        input_layout_Femail=(TextInputLayout)findViewById(R.id.input_layout_Femail);
        input_layout_Mothername=(TextInputLayout)findViewById(R.id.input_layout_Mothername);
        input_layout_MOccup=(TextInputLayout)findViewById(R.id.input_layout_MOccup);
        input_layout_MMoNo=(TextInputLayout)findViewById(R.id.input_layout_MMoNo);
        input_layout_MEmail=(TextInputLayout)findViewById(R.id.input_layout_MEmail);


        txtAddStdName=(EditText)findViewById(R.id.txtAddStdName);
        txtAddRNo=(EditText)findViewById(R.id.txtAddRNo);
        txtAddAddress=(EditText)findViewById(R.id.txtAddAddress);
        txtAddPcode=(EditText)findViewById(R.id.txtAddPcode);
        txtAddDOB=(EditText)findViewById(R.id.txtAddDOB);
        txtAddAddmissionDate=(EditText)findViewById(R.id.txtAddAddmissionDate);
        txtAddGRNo=(EditText)findViewById(R.id.txtAddGRNo);
        txtAddVanNo=(EditText)findViewById(R.id.txtAddVanNo);
        txtAddRPhone=(EditText)findViewById(R.id.txtAddRPhone);
        txtAddOPhone=(EditText)findViewById(R.id.txtAddOPhone);
        txtAddFathername=(EditText)findViewById(R.id.txtAddFathername);
        txtAddFOccup=(EditText)findViewById(R.id.txtAddFOccup);
        txtAddFMono=(EditText)findViewById(R.id.txtAddFMono);
        txtAddFemail=(EditText)findViewById(R.id.txtAddFemail);
        txtAddMothername=(EditText)findViewById(R.id.txtAddMothername);
        txtAddMOccup=(EditText)findViewById(R.id.txtAddMOccup);
        txtAddMMono=(EditText)findViewById(R.id.txtAddMMono);
        txtAddMEmail=(EditText)findViewById(R.id.txtAddMEmail);



        txtAddStdName.addTextChangedListener(new MyTextWatcher(txtAddStdName));
        txtAddRNo.addTextChangedListener(new MyTextWatcher(txtAddRNo));
        txtAddAddress.addTextChangedListener(new MyTextWatcher(txtAddAddress));
        txtAddPcode.addTextChangedListener(new MyTextWatcher(txtAddPcode));
        txtAddDOB.addTextChangedListener(new MyTextWatcher(txtAddDOB));
        txtAddAddmissionDate.addTextChangedListener(new MyTextWatcher(txtAddAddmissionDate));
        txtAddGRNo.addTextChangedListener(new MyTextWatcher(txtAddGRNo));
        txtAddVanNo.addTextChangedListener(new MyTextWatcher(txtAddVanNo));
        txtAddFathername.addTextChangedListener(new MyTextWatcher(txtAddFathername));
        txtAddFOccup.addTextChangedListener(new MyTextWatcher(txtAddFOccup));
        txtAddFMono.addTextChangedListener(new MyTextWatcher(txtAddFMono));
        txtAddFemail.addTextChangedListener(new MyTextWatcher(txtAddFemail));
        txtAddMothername.addTextChangedListener(new MyTextWatcher(txtAddMothername));
        txtAddMMono.addTextChangedListener(new MyTextWatcher(txtAddMMono));
        txtAddMOccup.addTextChangedListener(new MyTextWatcher(txtAddMOccup));

        ArrayAdapter<String> adM=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,medium);
        spnMedium.setAdapter(adM);

        GetStandard getStandard=new GetStandard();
        getStandard.execute();

        //Date Picker Start
        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDate();
            }
            private void updateDate()
            {
                txtAddDOB.setText(sdf.format(myCalendar.getTime()));
            }

        };
        txtAddDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(context,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        dateAddmission = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDateAdmission();
            }
            private void updateDateAdmission() {
                txtAddAddmissionDate.setText(sdf.format(myCalendar.getTime()));
            }
        };
        txtAddAddmissionDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(context,dateAddmission,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        //Date Picker Code End
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=txtAddStdName.getText().toString();
                String rno=txtAddRNo.getText().toString();
                String add=txtAddAddress.getText().toString();
                String pincode=txtAddPcode.getText().toString();
                String DOB=txtAddDOB.getText().toString();
                String addmissionDate=txtAddAddmissionDate.getText().toString();
                String grno=txtAddGRNo.getText().toString();
                String vanno=txtAddVanNo.getText().toString();
                String fname=txtAddFathername.getText().toString();
                String foccup=txtAddFOccup.getText().toString();
                String fmno=txtAddFMono.getText().toString();
                String femail=txtAddFemail.getText().toString();
                String mname=txtAddMothername.getText().toString();
                String moccup=txtAddMOccup.getText().toString();
                String mmno=txtAddMMono.getText().toString();


                if(name.equals(""))
                {
                    validateStdName();
                }
                else if(rno.equals(""))
                {
                    validateRollNo();
                }
                else if(add.equals(""))
                {
                    validateAddress();
                }
                else if(pincode.equals(""))
                {
                    validatePincode();
                }
                else if(DOB.equals(""))
                {
                    validateDOB();
                }
                else if(addmissionDate.equals(""))
                {
                    validateAddmissionDate();
                }
                else if(grno.equals(""))
                {
                    validateGrNo();
                }
                else if(vanno.equals(""))
                {
                    validateVanNo();
                }
                else if(fname.equals(""))
                {
                    validateFatherName();
                }
                else if(foccup.equals(""))
                {
                    validateFoccup();
                }
                else if(fmno.equals(""))
                {
                    validateFmono();
                }
                else if(femail.equals(""))
                {
                    validateFemail();
                }
                else if(mname.equals(""))
                {
                    validateMname();
                }
                else if(moccup.equals(""))
                {
                    validateMoccup();
                }
                else if(mmno.equals(""))
                {
                    validateMmno();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Hi..",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private boolean validateStdName() {

        if (txtAddStdName.getText().toString().trim().isEmpty())
        {
            input_layout_name.setError(getString(R.string.err_msg_stdname));
            requestFocus(txtAddStdName);
            return false;
        }
        else
        {
            input_layout_name.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateRollNo() {
        if (txtAddRNo.getText().toString().trim().isEmpty())
        {
            input_layout_RNo.setError(getString(R.string.err_msg_rno));
            requestFocus(txtAddRNo);
            return false;
        }
        else
        {
            input_layout_RNo.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateAddress () {
        if (txtAddAddress.getText().toString().trim().isEmpty())
        {
            input_layout_Address.setError(getString(R.string.err_msg_address));
            requestFocus(txtAddAddress);
            return false;
        }
        else
        {
            input_layout_Address.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePincode () {
        String MobilePattern = "[0-9]{6}";
        if(!txtAddPcode.getText().toString().matches(MobilePattern))
        {
            input_layout_PCode.setError(getString(R.string.err_msg_validpno));
            requestFocus(txtAddPcode);
            return false;
        }
        else
        {
            input_layout_PCode.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateDOB () {
        if (txtAddDOB.getText().toString().trim().isEmpty())
        {
            input_layout_DOB.setError(getString(R.string.err_msg_dob));
            requestFocus(txtAddDOB);
            return false;
        }
        else
        {
            input_layout_DOB.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateAddmissionDate () {
        if (txtAddAddmissionDate.getText().toString().trim().isEmpty())
        {
            input_layout_AddmissionDate.setError(getString(R.string.err_msg_admissiondate));
            requestFocus(txtAddAddmissionDate);
            return false;
        }
        else
        {
            input_layout_AddmissionDate.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateGrNo () {
        if (txtAddGRNo.getText().toString().trim().isEmpty())
        {
            input_layout_GRNo.setError(getString(R.string.err_msg_grno));
            requestFocus(txtAddGRNo);
            return false;
        }
        else
        {
            input_layout_GRNo.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateVanNo() {
        if (txtAddVanNo.getText().toString().trim().isEmpty())
        {
            input_layout_VanNo.setError(getString(R.string.err_msg_vanno));
            requestFocus(txtAddVanNo);
            return false;
        }
        else
        {
            input_layout_VanNo.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateFatherName() {
        if (txtAddFathername.getText().toString().trim().isEmpty())
        {
            input_layout_Fathername.setError(getString(R.string.err_msg_fname));
            requestFocus(txtAddFathername);
            return false;
        }
        else
        {
            input_layout_Fathername.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateFoccup() {
        if (txtAddFOccup.getText().toString().trim().isEmpty())
        {
            input_layout_FOccup.setError(getString(R.string.err_msg_foccup));
            requestFocus(txtAddFOccup);
            return false;
        }
        else
        {
            input_layout_FOccup.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateFmono() {
       /* if (txtAddFMono.getText().toString().trim().isEmpty())
        {
            input_layout_FMoNo.setError(getString(R.string.err_msg_mono));
            requestFocus(txtAddFMono);
            return false;
        }
        else
        {
            input_layout_FMoNo.setErrorEnabled(false);
        }
        return true;*/
        String MobilePattern = "[0-9]{10}";
        if(!txtAddFMono.getText().toString().matches(MobilePattern))
        {
            input_layout_FMoNo.setError(getString(R.string.err_msg_validmono));
            requestFocus(txtAddFMono);
            return false;
        }
        else
        {
            input_layout_FMoNo.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateFemail() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (!txtAddFemail.getText().toString().matches(emailPattern))
        {
            input_layout_Femail.setError(getString(R.string.err_msg_email));
            requestFocus(txtAddFemail);
            return false;
        }
        else
        {
            input_layout_Femail.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateMname() {
        if (txtAddMothername.getText().toString().trim().isEmpty())
        {
            input_layout_Mothername.setError(getString(R.string.err_msg_mname));
            requestFocus(txtAddMothername);
            return false;
        }
        else
        {
            input_layout_Mothername.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateMoccup() {
        if (txtAddMOccup.getText().toString().trim().isEmpty())
        {
            input_layout_MOccup.setError(getString(R.string.err_msg_moccup));
            requestFocus(txtAddMOccup);
            return false;
        }
        else
        {
            input_layout_MOccup.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateMmno() {

        String MobilePattern = "[0-9]{10}";
        if(!txtAddMMono.getText().toString().matches(MobilePattern))
        {
            input_layout_MMoNo.setError(getString(R.string.err_msg_validmono));
            requestFocus(txtAddMMono);
            return false;
        }
        else
        {
            input_layout_MMoNo.setErrorEnabled(false);
        }
        return true;
    }
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public class MyTextWatcher implements TextWatcher {

        private View view;

        public MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {

                case R.id.txtAddStdName:
                    validateStdName();
                    break;
                case R.id.txtAddRNo:
                    validateRollNo();
                    break;
                case R.id.txtAddAddress:
                    validateAddress();
                    break;
                case R.id.txtAddPcode:
                    validatePincode();
                    break;
                case R.id.txtAddDOB:
                    validateDOB();
                    break;
                case R.id.txtAddAddmissionDate:
                    validateAddmissionDate();
                    break;
                case R.id.txtAddGRNo:
                    validateGrNo();
                    break;
                case R.id.txtAddVanNo:
                    validateVanNo();
                    break;
                case R.id.txtAddFathername:
                    validateFatherName();
                    break;
                case R.id.txtAddFOccup:
                    validateFoccup();
                    break;
                case R.id.txtAddFMono:
                    validateFmono();
                    break;
                case R.id.txtAddFemail:
                    validateFemail();
                    break;
                case R.id.txtAddMothername:
                    validateMname();
                    break;
                case R.id.txtAddMOccup:
                    validateMoccup();
                    break;
                case R.id.txtAddMMono:
                    validateMmno();
                    break;

            }
        }
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

    private class GetStandard extends AsyncTask<String,Void,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

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

                        String id=j.getString("id");
                        String std=j.getString("std");
                        String div=j.getString("div");
                        String stdDiv=std+"-"+div;

                        //tabTitlesId.add(catId);

                        stdArrList.add(stdDiv);
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

            ArrayAdapter<String> ad = new ArrayAdapter<String>(AddStudentActivity.this, android.R.layout.simple_spinner_item, stdArrList);
            spnStd.setAdapter(ad);
        }
    }



}
