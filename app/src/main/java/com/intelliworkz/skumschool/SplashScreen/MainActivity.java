package com.intelliworkz.skumschool.SplashScreen;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.intelliworkz.skumschool.R;
import com.intelliworkz.skumschool.Login.LoginActivity;


public class MainActivity extends AppCompatActivity {
    LinearLayout lnSnackbar;

    public static String mainUrl = "http://www.skumschool.com/webservices/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lnSnackbar=(LinearLayout)findViewById(R.id.lnSnackbar);
        getSupportActionBar().hide();

        //Snackbar Start
        ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.isConnected())
        {
            Thread background = new Thread()
            {
                public void run()
                {
                    try {
                        sleep(3*1000);
                        Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(i);
                        finish();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            background.start();

        }
        else
        {
           /*Snackbar.make(lnSnackbar, "No Connection", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();*/

        }
        //Snackbar Stop
    }
}
