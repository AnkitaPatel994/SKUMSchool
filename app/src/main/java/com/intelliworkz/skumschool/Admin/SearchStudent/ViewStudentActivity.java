package com.intelliworkz.skumschool.Admin.SearchStudent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.intelliworkz.skumschool.Postdata;
import com.intelliworkz.skumschool.R;
import com.intelliworkz.skumschool.SplashScreen.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewStudentActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
   /* ListView lstStud;
    String data[]={"Mca","bca","Mca","bca","Mca","bca","Mca","bca","Mca","bca"};
*/
    RecyclerView rlStudList;
    String getPos;
    ArrayList<HashMap<String,String>> stuArrList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*lstStud=(ListView)findViewById(R.id.lstStud);

        ArrayAdapter<String> ad=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,data);
        lstStud.setAdapter(ad);*/
        rlStudList=(RecyclerView)findViewById(R.id.rlStudList);
        rlStudList.setHasFixedSize(true);

        RecyclerView.LayoutManager rvLayoutManager=new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);
        rlStudList.setLayoutManager(rvLayoutManager);

        getPos=getIntent().getExtras().getString("pos");


        GetStudentList getStudentList=new GetStudentList();
        getStudentList.execute();
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
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.view_student, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

       /* if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class GetStudentList extends AsyncTask<String,Void,String> {
        String status,message;
        @Override
        protected String doInBackground(String... params) {
            JSONObject studList=new JSONObject();
            try {
                studList.put("class",getPos);
                Postdata postdata=new Postdata();
                String stuPd=postdata.post(MainActivity.mainUrl+"studentFatch",studList.toString());
                JSONObject j=new JSONObject(stuPd);
                status=j.getString("status");
                if(status.equals("1"))
                {
                    Log.d("Like","Successfully");
                    message = j.getString("message");
                    JSONArray jarr=j.getJSONArray("Student");
                    for(int i=0;i<jarr.length();i++)
                    {
                        HashMap<String,String > stud = new HashMap<>();
                        JSONObject jobj=jarr.getJSONObject(i);

                        String name = jobj.getString("name");
                        String rollno = jobj.getString("rollno");
                        String classStud = jobj.getString("class");

                        stud.put("name",name);
                        stud.put("rollno",rollno);
                        stud.put("class",classStud);

                        stuArrList.add(stud);
                    }
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
                RecyclerView.Adapter rvNewsAdapter=new ViewStudAdapter(getApplicationContext(),stuArrList);
                rlStudList.setAdapter(rvNewsAdapter);
            }
        }
    }
}
