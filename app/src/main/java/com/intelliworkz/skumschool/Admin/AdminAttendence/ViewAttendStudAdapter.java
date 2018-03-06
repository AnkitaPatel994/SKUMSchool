package com.intelliworkz.skumschool.Admin.AdminAttendence;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.intelliworkz.skumschool.Postdata;
import com.intelliworkz.skumschool.R;
import com.intelliworkz.skumschool.SplashScreen.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by pc-6 on 9/27/2017.
 */

class ViewAttendStudAdapter extends RecyclerView.Adapter<ViewAttendStudAdapter.ViewHolder> {

    public static String stdDiv,stdRollno;
    Context context;
    ArrayList<HashMap<String, String>> stuArrList;
    String currentDate;

    public ViewAttendStudAdapter(Context context, ArrayList<HashMap<String, String>> stuArrList, String currentDate) {
        this.context=context;
        this.stuArrList=stuArrList;
        this.currentDate=currentDate;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.stuattend_view,parent,false);
        ViewHolder viewHolder=new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        String name=stuArrList.get(position).get("name");
        String rollno=stuArrList.get(position).get("rollno");
        String classStud=stuArrList.get(position).get("class");
        holder.stuAttendViewName.setText(name);
        holder.stuAttendViewRollno.setText(classStud+rollno);

        ViewAttendance viewAttendance=new ViewAttendance(classStud,rollno,holder.layAttendViewstd);
        viewAttendance.execute();

    }

    @Override
    public int getItemCount() {
        return stuArrList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView stuAttendViewName,stuAttendViewRollno;
        LinearLayout layAttendViewstd;
        public ViewHolder(View v) {
            super(v);
            stuAttendViewName=(TextView)v.findViewById(R.id.stuAttendViewName);
            stuAttendViewRollno=(TextView)v.findViewById(R.id.stuAttendViewRollno);
            layAttendViewstd=(LinearLayout)v.findViewById(R.id.layAttendViewstd);
        }
    }

    private class InsertAttenedance extends AsyncTask<String,Void,String>{

        String status,message,s_stddiv,s_rollno;

        public InsertAttenedance(String classStud, String rollno) {

            s_stddiv = classStud;
            s_rollno = rollno;
        }


        @Override
        protected String doInBackground(String... params) {
            JSONObject AttendList=new JSONObject();
            try {

                AttendList.put("s_stddiv",s_stddiv);
                AttendList.put("s_rollno",s_rollno);
                AttendList.put("date",currentDate);

                Postdata postdata=new Postdata();
                String stdPd=postdata.post(MainActivity.mainUrl+"attendenceInsert",AttendList.toString());
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
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class DeleteAttendance extends AsyncTask<String,Void,String>{

        String status,message,s_stddiv,s_rollno;

        public DeleteAttendance(String classStud, String rollno) {
            s_stddiv=classStud;
            s_rollno=rollno;
        }

        @Override
        protected String doInBackground(String... params) {
            JSONObject AttendList=new JSONObject();
            try {

                AttendList.put("s_stddiv",s_stddiv);
                AttendList.put("s_rollno",s_rollno);
                AttendList.put("date",currentDate);

                Postdata postdata=new Postdata();
                String stdPd=postdata.post(MainActivity.mainUrl+"attendenceDelete",AttendList.toString());
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
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class ViewAttendance extends AsyncTask<String,Void,String> {

        String status,message,s_stddiv,s_rollno;
        LinearLayout layAttendViewstd;

        public ViewAttendance(String classStud, String rollno, LinearLayout layAttendViewstd) {
            this.s_stddiv = classStud;
            this.layAttendViewstd = layAttendViewstd;
            this.s_rollno = rollno;
        }

        @Override
        protected String doInBackground(String... strings) {

            JSONObject absentAttendList=new JSONObject();

            try {

                absentAttendList.put("s_stddiv",s_stddiv);
                absentAttendList.put("s_rollno",s_rollno);
                absentAttendList.put("date",currentDate);

                Postdata postdata=new Postdata();
                String stdPd=postdata.post(MainActivity.mainUrl+"attendenceView",absentAttendList.toString());
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
                layAttendViewstd.setBackgroundResource(R.drawable.card_red);
                layAttendViewstd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        layAttendViewstd.setBackgroundResource(R.drawable.card_green);
                        DeleteAttendance deleteAttendance=new DeleteAttendance(s_stddiv,s_rollno);
                        deleteAttendance.execute();
                    }
                });
            }
            else
            {
                layAttendViewstd.setBackgroundResource(R.drawable.card_green);
                layAttendViewstd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        layAttendViewstd.setBackgroundResource(R.drawable.card_red);
                        InsertAttenedance insertAttenedance=new InsertAttenedance(s_stddiv,s_rollno);
                        insertAttenedance.execute();
                    }
                });
            }
        }
    }
}
