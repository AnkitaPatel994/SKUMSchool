package com.intelliworkz.skumschool.Student.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.intelliworkz.skumschool.Student.Attendence.AttendenceActivity;
import com.intelliworkz.skumschool.Student.Calender.CalenderActivity;
import com.intelliworkz.skumschool.ChangePassword.ChangePasswordActivity;
import com.intelliworkz.skumschool.Student.Education.EducationActivity;
import com.intelliworkz.skumschool.Student.Emotional_Evaluation.Emotional_EvaluationActivity;
import com.intelliworkz.skumschool.Student.Environment.EnvironmentActivity;
import com.intelliworkz.skumschool.Student.Evaluation.EvaluationActivity;
import com.intelliworkz.skumschool.Login.LoginActivity;
import com.intelliworkz.skumschool.Student.NoticeBoard.NoticeBoardActivity;
import com.intelliworkz.skumschool.Student.Profile.ProfileActivity;
import com.intelliworkz.skumschool.Student.ProgressReport.ProgressReportActivity;
import com.intelliworkz.skumschool.R;

public class HomeActivity extends AppCompatActivity {
 

    LinearLayout layAttendence,layProfile,layProgressReport,layNoticeBoard,layEvaluation,layEducation,layEnvironment,layEmotional,layActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.app_title);
        setContentView(R.layout.activity_home);

        layAttendence = (LinearLayout)findViewById(R.id.layAttendence);
        layProfile = (LinearLayout)findViewById(R.id.layProfile);
        layProgressReport = (LinearLayout)findViewById(R.id.layProgressReport);
        layNoticeBoard = (LinearLayout)findViewById(R.id.layNoticeBoard);
        layEvaluation = (LinearLayout)findViewById(R.id.layEvaluation);
        layEducation = (LinearLayout)findViewById(R.id.layEducation);
        layEnvironment = (LinearLayout)findViewById(R.id.layEnvironment);
        layEmotional = (LinearLayout)findViewById(R.id.layEmotional);
        layActivity = (LinearLayout)findViewById(R.id.layActivity);

        layAttendence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(HomeActivity.this,AttendenceActivity.class);
                startActivity(i);
            }
        });

        layProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,ProfileActivity.class);
                startActivity(i);
            }
        });

        layProgressReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(HomeActivity.this,ProgressReportActivity.class);
                startActivity(i);
            }
        });

        layNoticeBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,NoticeBoardActivity.class);
                startActivity(i);
            }
        });

        layEvaluation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,EvaluationActivity.class);
                startActivity(i);
            }
        });

        layEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,EducationActivity.class);
                startActivity(i);
            }
        });

        layEnvironment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,EnvironmentActivity.class);
                startActivity(i);
            }
        });

        layEmotional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,Emotional_EvaluationActivity.class);
                startActivity(i);
            }
        });

        layActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this,CalenderActivity.class);
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
