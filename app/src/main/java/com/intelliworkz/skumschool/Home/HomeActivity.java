package com.intelliworkz.skumschool.Home;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.intelliworkz.skumschool.Attendence.AttendenceActivity;
import com.intelliworkz.skumschool.ChangePassword.ChangePasswordActivity;
import com.intelliworkz.skumschool.Login.LoginActivity;
import com.intelliworkz.skumschool.NoticeBoard.NoticeBoardActivity;
import com.intelliworkz.skumschool.Profile.ProfileActivity;
import com.intelliworkz.skumschool.ProgressReport.ProgressReportActivity;
import com.intelliworkz.skumschool.R;

public class HomeActivity extends AppCompatActivity {
    LinearLayout layProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.app_title);
        setContentView(R.layout.activity_home);
        layProfile=(LinearLayout)findViewById(R.id.layProfile);

        layProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),ProfileActivity.class);
                startActivity(i);
            }
        });

    }
}
