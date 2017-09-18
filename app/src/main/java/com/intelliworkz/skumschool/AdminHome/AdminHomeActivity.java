package com.intelliworkz.skumschool.AdminHome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.intelliworkz.skumschool.AdminAddStudent.AddStudentActivity;
import com.intelliworkz.skumschool.R;

import org.w3c.dom.Text;

public class AdminHomeActivity extends AppCompatActivity {
    LinearLayout layAdminAttendence;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        layAdminAttendence=(LinearLayout)findViewById(R.id.layAdminAttendence);
        layAdminAttendence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),AddStudentActivity.class);
                startActivity(i);
            }
        });
    }
}
