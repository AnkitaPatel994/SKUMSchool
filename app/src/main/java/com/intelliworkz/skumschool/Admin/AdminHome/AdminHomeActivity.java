package com.intelliworkz.skumschool.Admin.AdminHome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.intelliworkz.skumschool.Admin.AdminAddStudent.AddStudentActivity;
import com.intelliworkz.skumschool.Admin.AdminAttendence.AdminAttendenceActivity;
import com.intelliworkz.skumschool.Admin.AdminAttendence.AttendenceStandardActivity;
import com.intelliworkz.skumschool.Admin.AdminEvent.AdminEventActivity;
import com.intelliworkz.skumschool.Admin.AdminNoticeBoard.AdminNoticeBoardActivity;
import com.intelliworkz.skumschool.Admin.AdminRole.AdminRoleActivity;
import com.intelliworkz.skumschool.Admin.AdminUser.AddUserActivity;
import com.intelliworkz.skumschool.ChangePassword.ChangePasswordActivity;
import com.intelliworkz.skumschool.Login.LoginActivity;
import com.intelliworkz.skumschool.R;

public class AdminHomeActivity extends AppCompatActivity {
    LinearLayout layAdminUser,layAdminRole,layAdmidStd,layAdminAttendence,layAdminProgressReport,layAdminNoticeBoard,layAdminEvaluation,layAdminEducation,layAdminEnvironment,layAdminEmoEva,layAdminActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.title_activity_adminHome);
        setContentView(R.layout.activity_admin_home);

        layAdminUser=(LinearLayout)findViewById(R.id.layAdminUser);
        layAdminRole=(LinearLayout)findViewById(R.id.layAdminRole);
        layAdmidStd=(LinearLayout)findViewById(R.id.layAdmidStd);
        layAdminAttendence=(LinearLayout)findViewById(R.id.layAdminAttendence);
        layAdminProgressReport=(LinearLayout)findViewById(R.id.layAdminProgressReport);
        layAdminNoticeBoard=(LinearLayout)findViewById(R.id.layAdminNoticeBoard);
        layAdminEvaluation=(LinearLayout)findViewById(R.id.layAdminEvaluation);
        layAdminEducation=(LinearLayout)findViewById(R.id.layAdminEducation);
        layAdminEnvironment=(LinearLayout)findViewById(R.id.layAdminEnvironment);
        layAdminEmoEva=(LinearLayout)findViewById(R.id.layAdminEmoEva);
        layAdminActivity=(LinearLayout)findViewById(R.id.layAdminActivity);

        layAdminUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),AddUserActivity.class);
                startActivity(i);
            }
        });

        layAdminRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),AdminRoleActivity.class);
                startActivity(i);
            }
        });

        layAdmidStd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),AddStudentActivity.class);
                startActivity(i);
            }
        });

        layAdminAttendence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), AttendenceStandardActivity.class);
                startActivity(i);
            }
        });

        layAdminProgressReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent i=new Intent(getApplicationContext(),AdminAttendenceActivity.class);
                startActivity(i);*/
            }
        });

        layAdminNoticeBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),AdminNoticeBoardActivity.class);
                startActivity(i);
            }
        });

        layAdminEvaluation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent i=new Intent(getApplicationContext(),AdminAttendenceActivity.class);
                startActivity(i);*/
            }
        });

        layAdminEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent i=new Intent(getApplicationContext(),AdminAttendenceActivity.class);
                startActivity(i);*/
            }
        });

        layAdminEnvironment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent i=new Intent(getApplicationContext(),AdminAttendenceActivity.class);
                startActivity(i);*/
            }
        });

        layAdminEmoEva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent i=new Intent(getApplicationContext(),AdminAttendenceActivity.class);
                startActivity(i);*/
            }
        });

        layAdminActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),AdminEventActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.home_cp) {
            Intent i = new Intent(getApplicationContext(),ChangePasswordActivity.class);
            startActivity(i);
        }
        else if (id == R.id.home_logout)
        {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
