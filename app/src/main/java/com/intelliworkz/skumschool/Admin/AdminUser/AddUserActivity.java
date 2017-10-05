package com.intelliworkz.skumschool.Admin.AdminUser;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.intelliworkz.skumschool.Admin.AdminAttendence.AttendenceStandardActivity;
import com.intelliworkz.skumschool.Admin.AdminEvent.AdminEventActivity;
import com.intelliworkz.skumschool.Admin.AdminHome.AdminHomeActivity;
import com.intelliworkz.skumschool.Admin.AdminNoticeBoard.AdminNoticeBoardActivity;
import com.intelliworkz.skumschool.Admin.AdminRole.AdminRoleActivity;
import com.intelliworkz.skumschool.Admin.AdminStudent.AddStudentActivity;
import com.intelliworkz.skumschool.HttpHandler;
import com.intelliworkz.skumschool.Login.LoginActivity;
import com.intelliworkz.skumschool.Postdata;
import com.intelliworkz.skumschool.R;
import com.intelliworkz.skumschool.SplashScreen.MainActivity;
import com.intelliworkz.skumschool.Student.Education.EducationActivity;
import com.intelliworkz.skumschool.Student.Emotional_Evaluation.Emotional_EvaluationActivity;
import com.intelliworkz.skumschool.Student.Environment.EnvironmentActivity;
import com.intelliworkz.skumschool.Student.Evaluation.EvaluationActivity;
import com.intelliworkz.skumschool.Student.ProgressReport.ProgressReportActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AddUserActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextInputLayout TILAdminName,TILAdminUserName,TILAdminUserPassword,TILAdminRole;
    EditText txtAdminName,txtAdminUsername,txtAdminUserPassword;
    Spinner SPAdminRole;
    Button btnAdminUser;
    ArrayList<String> roleArrList=new ArrayList<>();
    int rolePos;
    String roleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
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
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        TILAdminName = (TextInputLayout) findViewById(R.id.TILAdminName);
        TILAdminUserName = (TextInputLayout) findViewById(R.id.TILAdminUserName);
        TILAdminUserPassword = (TextInputLayout) findViewById(R.id.TILAdminUserPassword);
        TILAdminRole = (TextInputLayout) findViewById(R.id.TILAdminRole);

        txtAdminName = (EditText) findViewById(R.id.txtAdminName);
        txtAdminUsername = (EditText) findViewById(R.id.txtAdminUsername);
        txtAdminUserPassword = (EditText) findViewById(R.id.txtAdminUserPassword);

        txtAdminName.addTextChangedListener(new MyTextWatcher(txtAdminName));
        txtAdminUsername.addTextChangedListener(new MyTextWatcher(txtAdminUsername));
        txtAdminUserPassword.addTextChangedListener(new MyTextWatcher(txtAdminUserPassword));

        SPAdminRole = (Spinner) findViewById(R.id.SPAdminRole);
        btnAdminUser = (Button) findViewById(R.id.btnAdminUser);

        GetRole getRole=new GetRole();
        getRole.execute();

        SPAdminRole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                rolePos= Integer.parseInt(String.valueOf(SPAdminRole.getItemIdAtPosition(position)));
                roleList= String.valueOf(SPAdminRole.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdminUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Name = txtAdminName.getText().toString();
                String Username = txtAdminUsername.getText().toString();
                String Password = txtAdminUserPassword.getText().toString();

                if(roleList.equals("Select Role"))
                {
                    validateRole();
                }
                else if(Name.equals(""))
                {
                    validateName();
                }
                else if(Username.equals(""))
                {
                    validateUsername();
                }
                else if (Password.equals(""))
                {
                    validateUserPassword();
                }
                else
                {
                    GetUserInsert  getUserInsert = new GetUserInsert(rolePos,Name,Username,Password);
                    getUserInsert.execute();
                }

            }
        });

    }

    private boolean validateRole() {

        if (roleList.equals("Select Role"))
        {
            Toast.makeText(getApplicationContext(),"Please select Role",Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            TILAdminRole.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateName() {

        if (txtAdminName.getText().toString().trim().isEmpty())
        {
            TILAdminName.setError(getString(R.string.err_msg_empty));
            requestFocus(txtAdminName);
            return false;
        }
        else
        {
            TILAdminName.setErrorEnabled(false);
        }
        return true;

    }

    private boolean validateUsername() {

        if (txtAdminUsername.getText().toString().trim().isEmpty())
        {
            TILAdminUserName.setError(getString(R.string.err_msg_uname));
            requestFocus(txtAdminUsername);
            return false;
        }
        else
        {
            TILAdminUserName.setErrorEnabled(false);
        }
        return true;

    }

    private boolean validateUserPassword() {

        if (txtAdminUserPassword.getText().toString().trim().isEmpty())
        {
            TILAdminUserPassword.setError(getString(R.string.err_msg_pass));
            requestFocus(txtAdminUserPassword);
            return false;
        }
        else
        {
            TILAdminUserPassword.setErrorEnabled(false);
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

                case R.id.txtAdminName:
                    validateName();
                    break;
                case R.id.txtAdminUsername:
                    validateUsername();
                    break;
                case R.id.txtAdminUserPassword:
                    validateUserPassword();
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

    private class GetRole extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {

            roleArrList.add("Select Role");
            String response;
            HttpHandler h=new HttpHandler();
            response= h.serverConnection(MainActivity.mainUrl+"roleView");
            if(response!=null)
            {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray roleArr=jsonObject.getJSONArray("role");
                    for (int i=0;i<roleArr.length();i++)
                    {
                        JSONObject j=roleArr.getJSONObject(i);

                        String id=j.getString("id");
                        String name=j.getString("name");

                        roleArrList.add(name);
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

            ArrayAdapter<String> ad = new ArrayAdapter<String>(AddUserActivity.this, android.R.layout.simple_spinner_item, roleArrList);
            ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            SPAdminRole.setAdapter(ad);
        }
    }

    private class GetUserInsert extends AsyncTask<String,Void,String> {

        String name,username,password,status,message;
        int rolePos;

        public GetUserInsert(int rolePos, String name, String username, String password) {

            this.rolePos = rolePos;
            this.name = name;
            this.username = username;
            this.password = password;
        }

        @Override
        protected String doInBackground(String... params) {

            JSONObject userList=new JSONObject();
            try {

                userList.put("role",rolePos);
                userList.put("name",name);
                userList.put("username",username);
                userList.put("password",password);

                Postdata postdata=new Postdata();
                String userPd=postdata.post(MainActivity.mainUrl+"userInsert",userList.toString());
                JSONObject j=new JSONObject(userPd);
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
                Toast.makeText(AddUserActivity.this,message,Toast.LENGTH_SHORT).show();
                Intent i = new Intent(AddUserActivity.this,AddUserActivity.class);
                startActivity(i);
                finish();
            }
            else
            {
                Toast.makeText(AddUserActivity.this,message,Toast.LENGTH_SHORT).show();
            }
        }
    }
}
